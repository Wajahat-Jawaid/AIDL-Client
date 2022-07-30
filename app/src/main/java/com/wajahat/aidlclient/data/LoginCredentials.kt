package com.wajahat.aidlclient.data

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Wajahat Jawaid(wajahatjawaid@gmail.com)
 */
data class LoginCredentials(val email: String, val password: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun describeContents() = 0

    override fun writeToParcel(p0: Parcel?, p1: Int) {
        p0?.writeString(email)
        p0?.writeString(password)
    }

    companion object CREATOR : Parcelable.Creator<LoginCredentials> {
        override fun createFromParcel(parcel: Parcel): LoginCredentials {
            return LoginCredentials(parcel)
        }

        override fun newArray(size: Int): Array<LoginCredentials?> {
            return arrayOfNulls(size)
        }
    }
}