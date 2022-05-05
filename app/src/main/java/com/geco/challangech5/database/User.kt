package com.geco.challangech5.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class User(
    @PrimaryKey(autoGenerate = true) var idUser: Int?,
    @ColumnInfo(name = "username") var userName: String?,
    @ColumnInfo(name = "password") var password: String?,
    @ColumnInfo(name = "namaLengkap") var namaLengkap: String?,
    @ColumnInfo(name = "tanggalLahir") var tanggalLahir: String?,
    @ColumnInfo(name = "alamat") var alamat: String?
) : Parcelable