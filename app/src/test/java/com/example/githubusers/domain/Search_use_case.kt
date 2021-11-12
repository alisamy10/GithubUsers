package com.example.githubusers.domain

import com.example.githubusers.data.model.UsersResponseItem
import org.hamcrest.core.Is.`is`
import org.junit.Assert.*
import org.junit.Test


class SearchUseCase {
    private val fakeList= mutableListOf<UsersResponseItem>().apply {
        add(UsersResponseItem(
            login="Ali",
            id=1
        ))

        add(UsersResponseItem(
            login="ahmed",
            id=2
        ))

    }

    @Test
    fun searchQuery_addFakeList_returnTheSizeOfNewListWithMatchedTitle() {
        val result = searchQuery("a", fakeList)
        assertThat(result.size, `is`(2))
    }

    @Test
    fun searchQuery_addNullList_returnZeroSize() {
        val result = searchQuery("o", null)
        assertThat(result.size, `is`(0))
    }

    @Test
    fun searchQuery_addEmptyList_returnZeroSize() {
        val result = searchQuery("o", emptyList())
        assertThat(result.size, `is`(0))
    }
}