package com.example.android.egynews.users.repo.cache

import com.example.android.egynews.users.models.User
import com.example.android.egynews.users.repo.UserRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * @author Mahmoud Gamal on 17/11/2021.
 */
class UsersCacheRepository() : UserRepository {

    private val usersDao: UsersDao = UsersDao

    override fun getUpdatedTopUsers(): Observable<List<User>> {
        throw UnsupportedOperationException("Can not get updated top users in cache layer")
    }

    override fun getUserById(id: Int): Observable<User> {
        throw UnsupportedOperationException("Can not get user by id in cache layer")
    }

    override fun saveUser(user: User): Completable {
        return usersDao.saveUser(user)
    }

    override fun saveUsers(users: List<User>): Completable {
        return usersDao.saveUsers(users)
    }

    override fun getSavedUsers(): Observable<List<User>> {
        return usersDao.getSavedUsers()
    }
}