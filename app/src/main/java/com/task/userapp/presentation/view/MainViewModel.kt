package com.task.userapp.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.userapp.data.remote.base.NetworkResult
import com.task.userapp.domain.usecase.GetDataUserPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getDataUserPostUseCase: GetDataUserPostUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(MainUIState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchData()
    }
    /**
     * Fetch data from the API call.
     */
    fun fetchData() {
        _uiState.value = MainUIState(viewType = ViewType.Loading)
        viewModelScope.launch(ioDispatcher) {
            runCatching {
                val result = getDataUserPostUseCase()
                if (result is NetworkResult.Success) {
                    _uiState.update { it.copy(viewType = ViewType.Success(result.data)) }
                } else {
                    _uiState.update {
                        it.copy(
                            viewType = ViewType.Error((result as NetworkResult.Error).error.message.orEmpty())
                        )
                    }
                }
            }.onFailure { exception ->
                _uiState.update {
                    it.copy(
                        viewType = ViewType.Error(exception.message.orEmpty())
                    )
                }
            }
        }
    }
}