package dev.cardoso.quotesmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cardoso.quotesmvvm.model.QuoteModel
import dev.cardoso.quotesmvvm.model.QuoteProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class QuoteViewModel : ViewModel() {

    private val _quoteModel = MutableStateFlow(QuoteModel("",""))
    val quoteModel: StateFlow<QuoteModel> = _quoteModel

    //---  Load data from a suspend fun and mutate state
    fun randomQuote() {
        viewModelScope.launch {
            val result = QuoteProvider.random()
            _quoteModel.value = result
        }
    }


}