/*
 * Copyright 2021 Ona Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.smartregister.fhircore.engine.auth

import android.accounts.AbstractAccountAuthenticator
import android.accounts.Account
import android.accounts.AccountAuthenticatorResponse
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.core.os.bundleOf
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.UnknownHostException
import java.util.Locale
import javax.inject.Inject
import org.smartregister.fhircore.engine.data.remote.shared.TokenAuthenticator
import org.smartregister.fhircore.engine.data.remote.shared.TokenAuthenticator.Companion.AUTH_TOKEN_TYPE
import org.smartregister.fhircore.engine.ui.login.LoginActivity
import org.smartregister.fhircore.engine.util.SecureSharedPreference
import retrofit2.HttpException
import timber.log.Timber

class AccountAuthenticator
@Inject
constructor(
  @ApplicationContext val context: Context,
  val accountManager: AccountManager,
  val tokenAuthenticator: TokenAuthenticator,
  val secureSharedPreference: SecureSharedPreference
) : AbstractAccountAuthenticator(context) {

  override fun editProperties(
    response: AccountAuthenticatorResponse?,
    accountType: String?
  ): Bundle = bundleOf()

  override fun addAccount(
    response: AccountAuthenticatorResponse?,
    accountType: String?,
    authTokenType: String?,
    requiredFeatures: Array<out String>?,
    options: Bundle?
  ): Bundle {
    val intent = loginIntent(accountType, authTokenType, response)
    return bundleOf(AccountManager.KEY_INTENT to intent)
  }

  override fun confirmCredentials(
    response: AccountAuthenticatorResponse?,
    account: Account?,
    options: Bundle?
  ): Bundle {
    return bundleOf()
  }

  override fun getAuthToken(
    response: AccountAuthenticatorResponse?,
    account: Account,
    authTokenType: String?,
    options: Bundle?
  ): Bundle {
    var authToken = accountManager.peekAuthToken(account, authTokenType)

    // If token is null or empty or expired attempt to refresh the token
    if (authToken.isNullOrEmpty()) {
      val refreshToken = accountManager.getPassword(account)
      if (!refreshToken.isNullOrEmpty()) {
        authToken =
          try {
            tokenAuthenticator.refreshToken(refreshToken)
          } catch (ex: Exception) {
            Timber.e(ex)
            when (ex) {
              is HttpException, is UnknownHostException -> ""
              else -> throw ex
            }
          }
      }
    }

    // Auth token exists so return it
    if (!authToken.isNullOrEmpty()) {
      return bundleOf(
        AccountManager.KEY_ACCOUNT_NAME to account.name,
        AccountManager.KEY_ACCOUNT_TYPE to account.type,
        AccountManager.KEY_AUTHTOKEN to authToken
      )
    }

    // Auth token does not exist beyond this point so redirect user to login
    val intent = loginIntent(account.type, authTokenType, response)
    return Bundle().apply { putParcelable(AccountManager.KEY_INTENT, intent) }
  }

  private fun loginIntent(
    accountType: String?,
    authTokenType: String?,
    response: AccountAuthenticatorResponse?
  ): Intent {
    return Intent(context, LoginActivity::class.java).apply {
      putExtra(ACCOUNT_TYPE, accountType)
      putExtra(AUTH_TOKEN_TYPE, authTokenType)
      putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response)
      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
      addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
      addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
      addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
    }
  }

  override fun getAuthTokenLabel(authTokenType: String): String =
    authTokenType.uppercase(Locale.getDefault())

  override fun updateCredentials(
    response: AccountAuthenticatorResponse?,
    account: Account?,
    authTokenType: String?,
    options: Bundle?
  ): Bundle = bundleOf()

  override fun hasFeatures(
    response: AccountAuthenticatorResponse?,
    account: Account?,
    features: Array<out String>?
  ): Bundle = bundleOf()

  fun logout(onLogout: () -> Unit) {
    tokenAuthenticator.logout().onSuccess { loggedOut -> if (loggedOut) onLogout() }.onFailure {
      onLogout()
    }
  }

  fun logoutLocal(): Boolean {
    val account = tokenAuthenticator.findAccount()
    if (account != null) {
      accountManager.invalidateAuthToken(
        account.type,
        accountManager.peekAuthToken(account, AUTH_TOKEN_TYPE)
      )
      return true
    }
    return false
  }

  fun validateLoginCredentials(username: String, password: CharArray) =
    tokenAuthenticator.validateSavedLoginCredentials(username, password)

  fun invalidateSession(onSessionInvalidated: () -> Unit) {
    tokenAuthenticator.invalidateSession(onSessionInvalidated)
  }

  fun refreshSessionAuthToken(): Bundle? {
    val account = tokenAuthenticator.findAccount()
    return if (account != null) {
      getAuthToken(null, account, AUTH_TOKEN_TYPE, null)
    } else {
      null
    }
  }

  private fun confirmAccount(
    account: Account,
    callback: AccountManagerCallback<Bundle>,
    errorHandler: Handler = Handler(Looper.getMainLooper(), DefaultErrorHandler)
  ) {
    accountManager.confirmCredentials(account, Bundle(), null, callback, errorHandler)
  }

  fun confirmActiveAccount(onResult: (Intent) -> Unit) {
    tokenAuthenticator.findAccount()?.run {
      confirmAccount(
        this,
        callback = {
          val bundle = it.result
          bundle.getParcelable<Intent>(AccountManager.KEY_INTENT)?.let { loginIntent ->
            loginIntent.flags += Intent.FLAG_ACTIVITY_SINGLE_TOP
            onResult(loginIntent)
          }
        }
      )
    }
  }

  fun loadActiveAccount(onValidTokenMissing: (Intent) -> Unit) {
    tokenAuthenticator.findAccount()?.let {
      val accountType = tokenAuthenticator.getAccountType()
      val authToken = accountManager.peekAuthToken(it, AUTH_TOKEN_TYPE)
      if (!tokenAuthenticator.isTokenActive(authToken)) {
        accountManager.invalidateAuthToken(accountType, authToken)
      }

      tokenAuthenticator.findAccount()?.let { account ->
        accountManager.getAuthToken(
          account,
          accountType,
          Bundle(),
          false,
          { accountBundleFuture ->
            val bundle = accountBundleFuture.result
            bundle.getParcelable<Intent>(AccountManager.KEY_INTENT).let { logInIntent ->
              if (logInIntent == null && bundle.containsKey(AccountManager.KEY_AUTHTOKEN)) {
                return@getAuthToken
              }

              logInIntent!!
              logInIntent.flags += Intent.FLAG_ACTIVITY_SINGLE_TOP
              onValidTokenMissing(logInIntent)
            }
          },
          Handler(Looper.getMainLooper(), DefaultErrorHandler)
        )
      }
    }
  }

  fun hasActiveSession() = secureSharedPreference.retrieveSessionPin().isNullOrEmpty()

  fun retrieveLastLoggedInUsername(): String? = secureSharedPreference.retrieveSessionUsername()

  companion object {
    const val ACCOUNT_TYPE = "ACCOUNT_TYPE"
  }
}
