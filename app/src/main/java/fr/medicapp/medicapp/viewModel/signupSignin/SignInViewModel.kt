package fr.medicapp.medicapp.viewModel.signupSignin

import androidx.lifecycle.ViewModel
import fr.medicapp.medicapp.entity.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class SignInViewModel: ViewModel() {
    private val _sharedState = MutableStateFlow(User(id = UUID.randomUUID(),null,null,null,null,null,null))

    val sharedState = _sharedState.asStateFlow()
}