package com.example.itautransferapp.common

object ValidationCpf {

    fun isValidCPF(cpf: String): Boolean {
        val cleanCpf = cpf.filter { it.isDigit() }

        if (cleanCpf.length != 11 || cleanCpf.all { it == cleanCpf[0] }) return false

        val digits = cleanCpf.map { it.toString().toInt() }

        val firstDigit = calculateDigit(digits.take(9), listOf(10, 9, 8, 7, 6, 5, 4, 3, 2))
        val secondDigit = calculateDigit(digits.take(10) + listOf(firstDigit), listOf(11, 10, 9, 8, 7, 6, 5, 4, 3, 2))

        return digits[9] == firstDigit && digits[10] == secondDigit
    }

    private fun calculateDigit(digits: List<Int>, weights: List<Int>): Int {
        val sum = digits.zip(weights) { digit, weight -> digit * weight }.sum()
        val remainder = sum % 11
        return if (remainder < 2) 0 else 11 - remainder
    }

}