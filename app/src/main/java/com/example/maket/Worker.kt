package com.example.maket

data class Worker(val id:Int, val workerName:String, val category:String, val phoneNumber:String) {

    override fun toString(): String {
        return workerName
    }
}



