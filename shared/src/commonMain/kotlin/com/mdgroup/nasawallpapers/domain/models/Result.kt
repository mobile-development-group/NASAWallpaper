package com.mdgroup.nasawallpapers.domain.models

class Result<T> {

    var data: T? = null
    var ex: Exception? = null
    var isSuccess: Boolean = false

    constructor(data: T) {
        this.data = data
        this.isSuccess = true
    }

    constructor(ex: Exception?) {
        this.ex = ex
        this.isSuccess = false
    }

    companion object {

        fun <T> success(data: T): Result<T> {
            return Result(data)
        }

        fun <T> error(ex: Exception? = null): Result<T> {
            return Result(ex)
        }
    }
}