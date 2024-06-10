package com.toren.news.common

sealed class Language(val code: String) {
    object Tr : Language("tr")
    object En : Language("en")
    object Fr : Language("fr")
    object Us : Language("us")

}