package com.example.itautransferapp

import com.example.itautransferapp.common.ValidationEmailAndPassword
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Test

class ValidationEmailAndPasswordTest {

    @Test
    fun `test isValidEmail with valid email`() {
        assertTrue(ValidationEmailAndPassword.isValidEmail("example@test.com"))
    }

    @Test
    fun `test isValidEmail with missing @`() {
        assertFalse(ValidationEmailAndPassword.isValidEmail("exampletest.com"))
    }

    @Test
    fun `test isValidEmail with missing domain`() {
        assertFalse(ValidationEmailAndPassword.isValidEmail("example@"))
    }

    @Test
    fun `test isValidEmail with incorrect domain`() {
        assertFalse(ValidationEmailAndPassword.isValidEmail("example@test.co"))
    }


    @Test
    fun `test isValidPassword with missing digit`() {
        assertFalse(ValidationEmailAndPassword.isValidPassword("Password"))
    }

    @Test
    fun `test isValidPassword with missing uppercase letter`() {
        assertFalse(ValidationEmailAndPassword.isValidPassword("password1"))
    }

    @Test
    fun `test isValidPassword with all lowercase and no digits`() {
        assertFalse(ValidationEmailAndPassword.isValidPassword("password"))
    }
}