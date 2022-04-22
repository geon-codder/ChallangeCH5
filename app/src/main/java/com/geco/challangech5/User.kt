package com.geco.challangech5

import android.os.Parcel
import android.os.Parcelable

data class User(
    var userName: String?,
    var namaLengkap: String?,
    var tanggalLahir: String?,
    var alamat: String?
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(userName)
        parcel.writeString(namaLengkap)
        parcel.writeString(tanggalLahir)
        parcel.writeString(alamat)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
