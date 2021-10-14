package dev.cardoso.quotesmvvm.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cardoso.quotesmvvm.core.convertToList
import dev.cardoso.quotesmvvm.data.local.QuoteDB
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.data.model.QuoteProvider
import dev.cardoso.quotesmvvm.domain.GetQuoteRandomUseCase
import dev.cardoso.quotesmvvm.domain.GetQuoteUseCase
import dev.cardoso.quotesmvvm.domain.GetQuotesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class QuoteViewModel : ViewModel() {

    private val _quoteModel = MutableStateFlow(QuoteModel(0,"",""))
    val quoteModel: StateFlow<QuoteModel> = _quoteModel
    private lateinit var quoteDAO: QuoteDAO

    fun getQuotes() {
        viewModelScope.launch {
            GetQuotesUseCase(quoteDAO).getQuotes().collect {
            }
        }
    }
    //---  Load data from a suspend fun and mutate state
    fun randomQuote() {
        viewModelScope.launch {
            _quoteModel.value = GetQuoteRandomUseCase(quoteDAO).getQuoteRandom().first()
            //_quoteModel.value = GetQuoteUseCase(quoteDAO).getQuote(1).first()
        }
    }

    fun setContext(context: Context){
        this.quoteDAO = QuoteDB.getDatabase(context, viewModelScope).quoteDao()
    }


}