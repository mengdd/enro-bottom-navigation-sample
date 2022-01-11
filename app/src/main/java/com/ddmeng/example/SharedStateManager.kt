package com.ddmeng.example

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

data class Event(val name: String)

@Singleton
class SharedStateManager @Inject constructor() {
    private val _events = MutableSharedFlow<Event>()
    val events = _events.asSharedFlow()

    init {
        CoroutineScope(Dispatchers.Default).launch {
            for (i in 0..1000) {
                delay(1000)
                Log.d("event", "emit event $i")
                _events.emit(Event("event: $i"))
            }
        }
    }
}