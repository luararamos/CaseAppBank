package com.example.itautransferapp.common.constants

class ItauTransferConstants private constructor(){

    object ERROR{
        const val UNKNOWN_ERROR = "Erro desconhecido"
        const val SERVICE_ERROR= "Erro servidor"
        const val ERROR_UNEXPECTED= "Um erro inesperado ocorreu. Tente novamente mais tarde."
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object VIEW{
        const val RESTART = "Carregar Novamente"
        const val CANCEL= "Cancelar"
    }

    object PREFERENCES{
        const val USER_PREFS = "userPreferences"
        const val LAST_LOGGED_USER = "lastLoggedUser"
    }
}