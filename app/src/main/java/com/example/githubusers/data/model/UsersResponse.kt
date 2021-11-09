package com.example.githubusers.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

class UsersResponse : ArrayList<UsersResponseItem>()

@Entity
data class UsersResponseItem(
    val avatar_url: String="",
    val events_url: String="",
    val followers_url: String="",
    val following_url: String="",
    val gists_url: String="",
    val gravatar_id: String="",
    val html_url: String="",
    @PrimaryKey
    @NonNull
    val id: Int,
    val login: String="",
    val node_id: String="",
    val organizations_url: String="",
    val received_events_url: String="",
    val repos_url: String="",
    val site_admin: Boolean=false,
    val starred_url: String="",
    val subscriptions_url: String="",
    val type: String="",
    val url: String=""
)