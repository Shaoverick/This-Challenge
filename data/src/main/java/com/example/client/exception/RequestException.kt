package com.example.client.exception

class RequestException(val code: Int, val errorMessage: String) :
        Exception("Network request failed with status $code: $errorMessage") {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RequestException

        if (this.code != other.code) return false
        if (this.errorMessage != other.errorMessage) return false

        return true
    }

    override fun hashCode(): Int {
        var result = code
        result = 31 * result + errorMessage.hashCode()
        return result
    }

}
