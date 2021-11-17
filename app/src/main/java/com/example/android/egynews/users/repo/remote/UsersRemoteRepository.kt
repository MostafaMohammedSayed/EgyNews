package com.example.android.egynews.users.repo.remote

import com.example.android.egynews.users.models.User
import com.example.android.egynews.users.repo.UserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * @author Mahmoud Gamal on 17/11/2021.
 */
class UsersRemoteRepository() : UserRepository {

    private val userService: UsersRetrofitService = retrofit2.creat

    override fun getUpdatedTopUsers(): Observable<List<User>> {
        return userService.getUpdatedTopUsers()
    }

    override fun getUserById(id: Int): Observable<User> {
        return userService.getUserById(id)
    }

    override fun saveUser(user: User): Completable {
        throw UnsupportedOperationException("You can not save user in Remote layer")
    }

    override fun saveUsers(users: List<User>): Completable {
        throw UnsupportedOperationException("You can not save users in Remote layer")
    }

    override fun getSavedUsers(): Observable<List<User>> {
        throw UnsupportedOperationException("You can not get saved users in Remote layer")
    }

    All albums of all users

    1-  users->
    2-  Observable -> zip operator


}