package com.example.android.egynews.users.repo.remote

import com.example.android.egynews.users.models.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * @author Mahmoud Gamal on 17/11/2021.
 */
interface UsersRetrofitService {

    @GET
    fun getUpdatedTopUsers(): Observable<List<User>>

    @GET
    fun getUserById(id: Int): Observable<User>
}