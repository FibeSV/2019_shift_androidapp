package com.example.maket

data class Shift(
    val id: Int,
    val date: Long,
    val workerID: Int,
    val category: String,
    var status: String = "Не назначен"
) {
}