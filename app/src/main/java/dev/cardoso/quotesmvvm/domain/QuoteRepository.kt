package dev.cardoso.quotesmvvm.domain

import dev.cardoso.quotesmvvm.data.model.QuoteModel
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {
        suspend  fun getQuotes(): Flow<List<QuoteModel>>
}