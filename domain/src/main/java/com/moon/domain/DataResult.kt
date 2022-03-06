package com.moon.domain

import java.lang.Exception

sealed class DataResult<out T> {
    data class Success<out T>(val data: T): DataResult<T>()
    data class Error(val exception: Exception): DataResult<Nothing>()
}