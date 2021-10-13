package dev.cardoso.quotesmvvm.domain

import dev.cardoso.quotesmvvm.data.QuoteRepositoryImpl
import dev.cardoso.quotesmvvm.data.local.QuoteDB
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import kotlinx.coroutines.flow.Flow

class GetQuoteUseCase(private val quoteDAO: QuoteDAO) {

    private val quoteRepository = QuoteRepositoryImpl(quoteDAO)

    suspend fun getQuotes(): Flow<List<QuoteModel>> = quoteRepository.getQuotes()

}