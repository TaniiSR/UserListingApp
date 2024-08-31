package com.task.userapp.presentation.ui

import com.task.userapp.domain.model.UserModel

data class UserUIState(
    var viewType: ViewType = ViewType.Loading
)

sealed interface ViewType {
    data object Loading : ViewType
    data class Success(val data: List<UserModel>) : ViewType
    data class Error(val errorMessage: String) : ViewType
}
