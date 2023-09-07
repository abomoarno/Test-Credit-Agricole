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

/**
 * ViewModel for the main screen.
 *
 * This ViewModel is responsible for fetching the list of banks and exposing it to the UI.
 *
 * @property repository the repository to fetch the list of banks.
 * @property event the event channel to send events to the UI.
 * @property banks the list of banks as a LiveData. It is observed by the UI.
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val repository: BanksRepository
):  AndroidViewModel(context as Application) {

    val event: EventChannel<Event> = EventChannel(Channel(Channel.BUFFERED), viewModelScope)

    private val _banks = MutableLiveData<ResultStatus<List<Bank>>>()
    val banks: LiveData<ResultStatus<List<Bank>>> = _banks

    // This method is by the UI to fetch the list of banks.
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

    // This method is called when the user clicks on a bank account, to display the details of this account.
    fun goToDetails(bankAccount: BankAccount) {
        event.send(Event.ShowDetails(bankAccount))
    }

    sealed class Event {
        class ShowDetails(val bankAccount: BankAccount): Event()
    }

}