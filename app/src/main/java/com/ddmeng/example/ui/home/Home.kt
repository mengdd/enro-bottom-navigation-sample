package com.ddmeng.example.ui.home

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ddmeng.example.HomeKey
import dev.enro.annotations.ExperimentalComposableDestination
import dev.enro.annotations.NavigationDestination
import androidx.compose.runtime.livedata.observeAsState

@Composable
@ExperimentalComposableDestination
@NavigationDestination(HomeKey::class)
fun HomeScreen() {
    val viewModel: HomeViewModel = viewModel()
    val text = viewModel.text.observeAsState()
    Text(text = text.value ?: "")
}