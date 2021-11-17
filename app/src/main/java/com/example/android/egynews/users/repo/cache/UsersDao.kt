package com.example.android.egynews.users.repo.cache

import androidx.room.Insert
import androidx.room.Query
import com.example.android.egynews.users.models.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * @author Mahmoud Gamal on 17/11/2021.
 */

const val USER_TABLE_NAME = "users"

interface UsersDao {

    @Insert
    fun saveUser(user: User): Completable

    @Insert
    fun saveUsers(users: List<User>): Completable

    @Query("SELECT * from $USER_TABLE_NAME")
    fun getSavedUsers(): Observable<List<User>>
}