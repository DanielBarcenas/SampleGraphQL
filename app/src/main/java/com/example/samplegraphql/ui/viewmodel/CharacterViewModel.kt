package com.example.samplegraphql.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.exception.ApolloException
import com.example.samplegraphql.queries.CharacterQuery
import com.example.samplegraphql.queries.CharactersListQuery
import com.example.samplegraphql.data.repository.CharacterRepository
import com.example.samplegraphql.ui.state.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository,
) : ViewModel() {

    private val _charactersList by lazy { MutableLiveData<ViewState<ApolloResponse<CharactersListQuery.Data>>>() }
    val charactersList: LiveData<ViewState<ApolloResponse<CharactersListQuery.Data>>>
        get() = _charactersList

    private val _character by lazy { MutableLiveData<ViewState<ApolloResponse<CharacterQuery.Data>>>() }
    val character: LiveData<ViewState<ApolloResponse<CharacterQuery.Data>>>
        get() = _character

    fun queryCharactersList() = viewModelScope.launch {
        _charactersList.postValue(ViewState.Loading())
        try {
            val response = repository.queryCharactersList()
            _charactersList.postValue(ViewState.Success(response))
        } catch (e: ApolloException) {
            Log.d("ApolloException", "Failure", e)
            _charactersList.postValue(ViewState.Error("Error fetching characters"))
        }
    }

    fun queryCharacter(id: String) = viewModelScope.launch {
        _character.postValue(ViewState.Loading())
        try {
            val response = repository.queryCharacter(id)
            _character.postValue(ViewState.Success(response))
        } catch (ae: ApolloException) {
            Log.d("ApolloException", "Failure", ae)
            _character.postValue(ViewState.Error("Error fetching characters"))
        }
    }

}