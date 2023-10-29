package com.letmelearn.architecturepractice.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.letmelearn.architecturepractice.data.repo.SearchLocationRepository
import com.letmelearn.architecturepractice.data.externalModels.SearchLocationExternalModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchLocationViewModel @Inject constructor(private val searchLocationRepository: SearchLocationRepository) :
    ViewModel() {

    private val job: CoroutineScope = viewModelScope
    private val mSearchLocationUiState: MutableStateFlow<SearchLocationUiState> =
        MutableStateFlow(SearchLocationUiState())
    val searchLocationUiState: StateFlow<SearchLocationUiState> = mSearchLocationUiState

    fun searchLocation(
        location: String
    ) {
        job.launch {
            searchLocationRepository.searchLocation(location).map {
                it.uiState()
            }.collect {
                mSearchLocationUiState.value = it
            }
        }
    }

    private fun SearchLocationExternalModel.uiState(): SearchLocationUiState {
        return SearchLocationUiState(name, tempC, tempF)
    }
}