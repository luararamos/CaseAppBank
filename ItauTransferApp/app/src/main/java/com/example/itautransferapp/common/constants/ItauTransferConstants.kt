package com.example.itautransferapp.common.constants

class ItauTransferConstants private constructor(){

    object ERROR{

        const val ERROR_UNEXPECTED= "Um erro inesperado ocorreu. Tente novamente mais tarde."
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object PREFERENCES{
        const val USER_PREFS = "userPreferences"
        const val LAST_LOGGED_USER = "lastLoggedUser"
    }
}