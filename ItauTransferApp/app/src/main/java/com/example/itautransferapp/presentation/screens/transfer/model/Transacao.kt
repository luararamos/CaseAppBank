package com.example.itautransferapp.presentation.screens.transfer.model

import java.io.Serializable

class Transacao (
    val nome: String,
    val cpf: String,
    val agencia: String,
    val conta: String,
    val data: String,
    val hora: String,
    val valor: String,
    val msg: String,
    val valor_cliente: String
): Serializable