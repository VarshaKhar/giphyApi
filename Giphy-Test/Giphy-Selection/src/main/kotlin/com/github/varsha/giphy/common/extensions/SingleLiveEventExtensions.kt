package com.github.varsha.giphy.common.extensions

import com.github.varsha.giphy.common.arch.state.SingleLiveEvent
import com.github.varsha.giphy.common.arch.state.UiState

fun SingleLiveEvent<UiState>.postEmptyState() = run { postValue(UiState.Empty) }
fun SingleLiveEvent<UiState>.postLoadingState() = run { postValue(UiState.Loading) }
fun SingleLiveEvent<UiState>.postLoadedState() = run { postValue(UiState.Loaded) }
fun SingleLiveEvent<UiState>.postErrorState() = run { postValue(UiState.Error) }