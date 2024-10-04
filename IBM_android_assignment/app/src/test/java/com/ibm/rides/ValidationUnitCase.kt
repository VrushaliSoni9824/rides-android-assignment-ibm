package com.ibm.rides

import com.ibm.rides.utils.NetworkUtils
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationUnitCase {
    @Test
    fun `isInputValid returns true for valid input`() {
        val validInputs = listOf(1, 50, 100)

        validInputs.forEach { input ->
            assertTrue("Expected true for input: $input", NetworkUtils.isInputValid(input))
        }
    }

    @Test
    fun `isInputValid returns false for invalid input`() {

        val invalidInputs = listOf(0, 101, -10, null)

        invalidInputs.forEach { input ->
            assertFalse("Expected false for input: $input", NetworkUtils.isInputValid(input))
        }
    }
}