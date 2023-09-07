
package com.adaptive.testmobileca.utils

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Created by Arno ABOMO on 09/06/2023
 */

/**
 * This class is used to facilitate the use of Flows to send and receive events between components
 *
 * @param T the type of the events to send and receive
 */

class EventChannel<T>(private val channel: Channel<T>, private val sendScope: CoroutineScope) {

    fun send(event: T) {
        sendScope.launch {
            channel.send(event)
        }
    }

    fun receive(lifecycleOwner: LifecycleOwner, callback: suspend (T) -> Unit) {
        val observer = FlowObserver(lifecycleOwner, channel.receiveAsFlow(), callback)
        lifecycleOwner.lifecycle.addObserver(observer)
    }
}

/**
 * This class is used to observe a Flow and collect its values when the lifecycle is in the started state.
 * It is used by the EventChannel class.
 *
 * @param lifecycleOwner the lifecycle owner of the component that will receive the events
 * @param flow the flow to observe
 * @param collector the callback that will be called when the flow emits a value
 */
class FlowObserver<T> (
    private val lifecycleOwner: LifecycleOwner,
    private val flow: Flow<T>,
    private val collector: suspend (T) -> Unit
) : DefaultLifecycleObserver {

    private var job: Job? = null

    override fun onStart(owner: LifecycleOwner) {
        if (job == null) {
            job = lifecycleOwner.lifecycleScope.launch {
                flow.collect { collector(it) }
            }
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        job?.cancel()
        job = null
    }
}

