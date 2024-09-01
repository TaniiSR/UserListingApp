package com.task.userapp.presentation.view

import com.task.userapp.domain.model.UserModel

data class MainUIState(
    var viewType: ViewType = ViewType.Loading
)

sealed interface ViewType {
    data object Loading : ViewType
    data class Success(val data: List<UserModel>) : ViewType
    data class Error(val errorMessage: String) : ViewType
}
