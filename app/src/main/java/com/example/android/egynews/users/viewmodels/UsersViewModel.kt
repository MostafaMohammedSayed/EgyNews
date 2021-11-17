package com.example.android.egynews.users.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.android.egynews.base.Resource
import com.example.android.egynews.users.models.User
import com.example.android.egynews.users.repo.UserRepository
import com.example.android.egynews.users.repo.UserRepositoryImpl
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

/**
 * @author Mahmoud Gamal on 17/11/2021.
 */
class UsersViewModel() : ViewModel() {
    private val userRepository: UserRepository = UserRepositoryImpl()

    private val liveData = MutableLiveData<Resource<List<User>>>()

    fun fetchData() {
        liveData.value = Resource.loading()
        userRepository
            .getSavedUsers()
            .flatMap { users ->
                liveData.postValue(Resource.success(users))
                return@flatMap userRepository.getUpdatedTopUsers()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onNext = { updatedUsers ->
                    liveData.value = Resource.success(updatedUsers)
                },
                onError = {

                }
            )
    }
}