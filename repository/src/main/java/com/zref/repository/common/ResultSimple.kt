package com.zref.repository.common

/**
 * All of repository action should use this class as result wrapper.
 *
 * Similar class : [ResultNoData]
 */
sealed class ResultSimple<out T> {
    class Success<out T>(val `data`: T) : ResultSimple<T>()
    class Error(val message: String) : ResultSimple<Nothing>()
}