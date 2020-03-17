package com.lambdaschool.basicandroidnetworking.model

// TODO: Define AdviceMsg and Slip classes
data class Slip(val advice: String?, val slip_id: String?)

data class AdviceMsg(val slip: Slip?){
    fun getAdvice(): String?{
        return slip?.advice
    }
}