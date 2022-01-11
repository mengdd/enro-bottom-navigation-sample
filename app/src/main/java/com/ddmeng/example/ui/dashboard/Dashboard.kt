package com.ddmeng.example.ui.dashboard

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddmeng.example.DashboardKey
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination

@Composable
@ExperimentalComposableDestination
@NavigationDestination(DashboardKey::class)
fun DashboardScreen() {
    val viewModel: DashboardViewModel = viewModel()
    val text = viewModel.text.observeAsState()
    Text(text = text.value ?: "")
}