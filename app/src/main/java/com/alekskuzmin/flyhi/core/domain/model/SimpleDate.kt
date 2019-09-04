package com.alekskuzmin.flyhi.core.domain.model

data class SimpleDate(
    val year: Int,
    val month: Int,
    val day: Int

) {
    override fun toString(): String {
        return "$day/$month/$year"
    }
}