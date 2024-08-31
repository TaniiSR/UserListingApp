package com.task.userapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.userapp.data.remote.base.NetworkResult
import com.task.userapp.domain.usecase.GetDataUserPostUseCase
import com.task.userapp.presentation.ui.UserUIState
import com.task.userapp.presentation.ui.ViewType
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val getDataUserPostUseCase: GetDataUserPostUseCase,
    private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUIState())
    val uiState = _uiState.asStateFlow()

    /**
     * Fetch data from the API call.
     */
    fun fetchData() {
        _uiState.value = UserUIState(viewType = ViewType.Loading)
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