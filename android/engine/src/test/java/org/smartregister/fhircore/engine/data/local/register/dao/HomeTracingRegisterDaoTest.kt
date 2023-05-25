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

package org.smartregister.fhircore.engine.data.local.register.dao

import io.mockk.mockk
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class HomeTracingRegisterDaoTest {

  private lateinit var homeTracingRegisterDao: HomeTracingRegisterDao

  @Before
  fun setUp() {
    homeTracingRegisterDao =
      HomeTracingRegisterDao(mockk(), mockk(), mockk(), mockk(), mockk(), mockk())
  }

  @Test
  fun getTracingCoding() {
    Assert.assertEquals("home-tracing", homeTracingRegisterDao.tracingCoding.code)
    Assert.assertEquals("https://d-tree.org", homeTracingRegisterDao.tracingCoding.system)
  }
}
