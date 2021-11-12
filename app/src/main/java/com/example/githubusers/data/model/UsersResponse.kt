package com.example.githubusers.data.model

import android.os.Parcel
import android.os.Parcelable
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
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<UsersResponseItem> {
        override fun createFromParcel(parcel: Parcel): UsersResponseItem {
            return UsersResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<UsersResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}