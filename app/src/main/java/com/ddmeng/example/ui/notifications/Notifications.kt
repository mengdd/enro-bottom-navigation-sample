package com.ddmeng.example.ui.notifications

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddmeng.example.NotificationsKey
import com.ddmeng.bottom_navigation.ui.notifications.NotificationsViewModel
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination

@Composable
@ExperimentalComposableDestination
@NavigationDestination(NotificationsKey::class)
fun NotificationsScreen() {
    val viewModel: NotificationsViewModel = viewModel()
    val text = viewModel.text.observeAsState()

    Text(text = text.value ?: "")
}