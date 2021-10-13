package dev.cardoso.quotesmvvm.data.local

import dev.cardoso.quotesmvvm.data.model.QuoteModel
import kotlinx.coroutines.flow.Flow

interface QuoteLocalDataSource {
    suspend fun  getQuotes(): Flow<List<QuoteModel>>
    suspend fun  getQuote(quoteId:Int): Flow<QuoteModel>
    suspend fun  insertAll(quotes : List<QuoteModel>)
    suspend fun  insert(quote : QuoteModel)
}
