package com.example.kotlin_nz.utils

sealed class ApiResponse<out T> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Failure(val message: String) : ApiResponse<Nothing>()
}