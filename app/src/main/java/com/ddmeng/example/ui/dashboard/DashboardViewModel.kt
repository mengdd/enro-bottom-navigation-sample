package com.ddmeng.example.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ddmeng.example.SharedStateManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val stateManager: SharedStateManager
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text


    init {
        Log.i("viewmodel", "DashboardViewModel init ${hashCode()}")
        viewModelScope.launch {
            Log.w("viewModel", "viewmodel ${this@DashboardViewModel.hashCode()} start to collect")
            stateManager.events.collect {
                Log.i(
                    "viewModel",
                    "viewmodel ${this@DashboardViewModel.hashCode()} collect from manager: $it"
                )
            }
        }


    }

    override fun onCleared() {
        super.onCleared()
        Log.i("viewmodel", "DashboardViewModel onClear ${hashCode()}")
    }
}