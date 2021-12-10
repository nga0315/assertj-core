/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2021 the original author or authors.
 */
package org.assertj.core.api.floatarray;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.Assertions.withPrecision;
import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.error.ShouldNotBeNull.shouldNotBeNull;
import static org.assertj.core.test.FloatArrays.arrayOf;
import static org.mockito.Mockito.verify;

import org.assertj.core.api.FloatArrayAssert;
import org.assertj.core.api.FloatArrayAssertBaseTest;
import org.junit.jupiter.api.Test;

/**
 * Tests for <code>{@link FloatArrayAssert#containsExactly(Float[])}</code>.
 *
 * @author Omar Morales Ortega
 */
class FloatArrayAssert_containsExactly_with_Float_array_Test extends FloatArrayAssertBaseTest {

  @Test
  void should_fail_if_values_is_null() {
    // GIVEN
    Float[] values = null;
    // WHEN
    Throwable thrown = catchThrowable(() -> assertions.containsExactly(values));
    // THEN
    then(thrown).isInstanceOf(NullPointerException.class)
                .hasMessage(shouldNotBeNull("values").create());
  }

  @Test
  void should_pass_if_values_are_in_range_of_precision() {
    // GIVEN
    Float[] values = new Float[] { 0.9f, 2.1f, 2.9f };
    // WHEN/THEN
    assertThat(arrayOf(1.0f, 2.0f, 3.0f)).containsExactly(values, withPrecision(0.2f));
  }

  @Test
  void should_pass_if_values_are_in_range_of_precision_strict_tests() {
    assertThat(new float[]{0.2f}).containsExactly(new float[]{0.7f}, withPrecision(0.5f));
    assertThat(new float[]{0.2f, 0.5f, 0.1f, 10.9f}).containsExactly(new float[]{0.7f, 0.01f, 0.6f, 10.4f}, withPrecision(0.5f));

  }


  @Override
  protected FloatArrayAssert invoke_api_method() {
    return assertions.containsExactly(new Float[] { 1.0f, 2.0f });
  }

  @Override
  protected void verify_internal_effects() {
    verify(arrays).assertContainsExactly(getInfo(assertions), getActual(assertions), arrayOf(1.0f, 2.0f));
  }

}
