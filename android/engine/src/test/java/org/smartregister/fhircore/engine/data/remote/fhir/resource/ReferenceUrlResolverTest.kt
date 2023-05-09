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

package org.smartregister.fhircore.engine.data.remote.fhir.resource

import android.graphics.Bitmap
import com.google.android.fhir.FhirEngine
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import java.io.ByteArrayInputStream
import java.nio.charset.Charset
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.MediaType
import okhttp3.ResponseBody
import okio.BufferedSource
import org.hl7.fhir.r4.model.Binary
import org.hl7.fhir.r4.model.ResourceType
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.smartregister.fhircore.engine.robolectric.RobolectricTest
import org.smartregister.fhircore.engine.rule.CoroutineTestRule
import retrofit2.Call
import retrofit2.Response

class ReferenceUrlResolverTest : RobolectricTest() {
  @get:Rule val coroutineTestRule = CoroutineTestRule()
  private lateinit var referenceUrlResolver: ReferenceUrlResolver

  private val fhirEngine = mockk<FhirEngine>()

  private val fhirResourceService = mockk<FhirResourceService>()

  @Before
  fun setUp() {
    referenceUrlResolver =
      spyk(ReferenceUrlResolver(fhirEngine = fhirEngine, fhirResourceService = fhirResourceService))
  }

  @Test
  fun testResolveBinaryResourceShouldReturnBinary() {
    coroutineTestRule.runBlockingTest {
      val binary = Binary().apply { id = "bId" }
      coEvery { fhirEngine.get(ResourceType.Binary, any()) } returns binary
      Assert.assertEquals(
        binary,
        referenceUrlResolver.resolveBinaryResource(
          "https://fhir-server.org/Binary/sample-binary-image"
        )
      )
    }
  }

  @Test
  fun testResolveImageUrlWithNullBodyShouldReturnNull() {
    coroutineTestRule.runBlockingTest {
      val mockResponse = mockk<Call<ResponseBody?>>()
      every { mockResponse.execute() } returns Response.success(null)
      every { fhirResourceService.fetchImage(any()) } returns mockResponse
      Assert.assertNull(referenceUrlResolver.resolveBitmapUrl("https://image-server.com/8929839"))
    }
  }

  @Test
  fun testResolveImageUrlShouldReturnBitmap() {
    coroutineTestRule.runBlockingTest {
      val mockResponseBody: ResponseBody =
        spyk(
          object : ResponseBody() {
            override fun contentLength(): Long = 1L

            override fun contentType(): MediaType? = null

            override fun source(): BufferedSource = mockk()
          }
        )

      val mockResponse = Response.success<ResponseBody?>(mockResponseBody)

      every { mockResponseBody.byteStream() } returns
        (ByteArrayInputStream(
          "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7".toByteArray(
            Charset.forName("UTF-8")
          )
        ))
      val callResponse = mockk<Call<ResponseBody?>>()
      every { callResponse.execute() } returns mockResponse
      every { fhirResourceService.fetchImage(any()) } returns callResponse
      val bitmap = referenceUrlResolver.resolveBitmapUrl("https://image-server.com/8929839")
      Assert.assertNotNull(bitmap)
      Assert.assertTrue(bitmap is Bitmap)
    }
  }
}