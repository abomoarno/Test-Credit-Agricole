package com.docdoku.testmobileca.utils

/**
 * Created by Arno ABOMO on Sept/06/2023
 */

/*
 * This class is used to hold the result of a request.
 */

sealed interface ResultStatus<out T>{
    data class Success<T>(val data:T) : ResultStatus<T>
    data class Error(val message: String? = null) : ResultStatus<Nothing>
    object Loading : ResultStatus<Nothing>
}