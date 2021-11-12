package com.example.githubusers.domain

import com.example.githubusers.data.model.UsersResponseItem
import java.util.*
import kotlin.collections.ArrayList

fun searchQuery(newText: String?, responseList: List<UsersResponseItem>?): MutableList<UsersResponseItem> {
    val responses: MutableList<UsersResponseItem> = ArrayList()
    if (responseList == null || responseList.isEmpty())
        return responses
    for (response in responseList) {
        /*
    Useful constant for the root locale. The root locale is the locale whose language, country, and variant are empty ("") strings.
    This is regarded as the base locale of all locales, and is used as the language/country neutral locale for the locale sensitive operations.
     */
        val name: String? = response.login.lowercase(Locale.ROOT)
        if (newText?.lowercase(Locale.ROOT)?.let { name?.contains(it) }!!)
            responses.add(response)
    }
    return responses
}