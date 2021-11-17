package com.example.android.egynews.users.repo

import com.example.android.egynews.users.models.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

/**
 * @author Mahmoud Gamal on 17/11/2021.
 */
class UserRepositoryImpl() : UserRepository {
    private val cacheRepo: UserRepository
    private val remoteRepo: UserRepository

    override fun getUpdatedTopUsers(): Observable<List<User>> {
        return remoteRepo.getUpdatedTopUsers()
            .flatMap {users->
                saveUsers(users)
                return@flatMap Observable.just(users)
            }
    }

    override fun getUserById(id: Int): Observable<User> {
        return remoteRepo.getUserById(id)
    }

    override fun saveUser(user: User): Completable {
        return remoteRepo.saveUser(user)
    }

    override fun saveUsers(users: List<User>): Completable {
        return cacheRepo.saveUsers(users)
    }

    override fun getSavedUsers(): Observable<List<User>> {
        return cacheRepo.getSavedUsers()
    }
}