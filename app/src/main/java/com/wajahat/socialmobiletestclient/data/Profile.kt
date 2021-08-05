package com.wajahat.socialmobiletestclient.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
data class Profile(val firstname: String, val lastname: String, val age: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(firstname)
        p0?.writeString(lastname)
        p0?.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<Profile> {
        override fun createFromParcel(parcel: Parcel): Profile {
            return Profile(parcel)
        }

        override fun newArray(size: Int): Array<Profile?> {
            return arrayOfNulls(size)
        }
    }
}