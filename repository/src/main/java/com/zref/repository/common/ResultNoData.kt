package com.zref.repository.common

/**
 * Similar to [ResultSimple] but without data in [Success] state.
 *
 * e.g. : Logout, Favorite an exploration item, sending error log, etc.
 *
 * Similar class : [ResultSimple]
 */
sealed class ResultNoData {
    object Success : ResultNoData()
    class Error(val message: String) : ResultNoData()
}