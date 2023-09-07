package com.adaptive.testmobileca.ui

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.adaptive.testmobileca.domaine.Bank
import com.adaptive.testmobileca.domaine.BankAccount
import com.adaptive.testmobileca.repositories.BanksRepository
import com.adaptive.testmobileca.utils.EventChannel
import com.adaptive.testmobileca.utils.ResultStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Arno ABOMO on 09/06/2023
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: BanksRepository
):  AndroidViewModel(context as Application) {

    val event: EventChannel<Event> = EventChannel(Channel(Channel.BUFFERED), viewModelScope)

    private val _banks = MutableLiveData<ResultStatus<List<Bank>>>()
    val banks: LiveData<ResultStatus<List<Bank>>> = _banks

    fun getBanks() {
        viewModelScope.launch(IO) {
            withContext(Main){
                _banks.value = ResultStatus.Loading
            }
            withContext(Main){
                _banks.postValue(repository.getBanks())
            }
        }
    }

    fun goToDetails(bankAccount: BankAccount) {
        event.send(Event.ShowDetails(bankAccount))
    }

    sealed class Event {
        class ShowDetails(val bankAccount: BankAccount): Event()
    }

}