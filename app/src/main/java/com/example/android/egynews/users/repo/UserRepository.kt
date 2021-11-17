package com.example.android.egynews.users.repo

import com.example.android.egynews.users.models.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * @author Mahmoud Gamal on 17/11/2021.
 */
interface UserRepository {
    fun getUpdatedTopUsers(): Observable<List<User>> // remote
    fun getUserById(id: Int): Observable<User>
    fun saveUser(user: User): Completable
    fun saveUsers(users: List<User>): Completable
    fun getSavedUsers(): Observable<List<User>>
}