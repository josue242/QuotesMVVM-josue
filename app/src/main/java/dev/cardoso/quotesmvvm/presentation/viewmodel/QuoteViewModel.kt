package dev.cardoso.quotesmvvm.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cardoso.quotesmvvm.core.convertToList
import dev.cardoso.quotesmvvm.data.local.QuoteDB
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteProvider
import dev.cardoso.quotesmvvm.domain.GetQuoteUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch


class QuoteViewModel : ViewModel() {

    private val _quoteModel = MutableStateFlow(QuoteModel(0,"",""))
    val quoteModel: StateFlow<QuoteModel> = _quoteModel

    private lateinit var quoteDAO: QuoteDAO
    //---  Load data from a suspend fun and mutate state
    fun randomQuote() {
        viewModelScope.launch {
            val quoteFlow= GetQuoteUseCase(quoteDAO).getQuotes()
            val quotes = quoteFlow.convertToList()
            val items = quotes.size
            val quote = quotes[(1..items).random()]
            _quoteModel.value = quote
        }
    }
    fun setContext(context: Context){
        this.quoteDAO = QuoteDB.getDatabase(context, viewModelScope).quoteDao()
    }


}