package com.geco.challangech5.database

import androidx.room.*


@Dao
interface UserDao {
    @Query("SELECT * FROM User where userName= :mail and password= :password")
    fun getUser(mail: String?, password: String?): User?

    @Insert
    fun insert(user: User?)

    @Update
    fun update(user: User?)

    @Delete
    fun delete(user: User?)
}