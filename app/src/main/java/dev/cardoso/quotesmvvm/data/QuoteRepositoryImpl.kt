package dev.cardoso.quotesmvvm.data

import android.accounts.NetworkErrorException
import android.util.Log
import dev.cardoso.quotesmvvm.data.local.daos.QuoteDAO
import dev.cardoso.quotesmvvm.data.model.QuoteModel
import dev.cardoso.quotesmvvm.domain.QuoteRepository
import kotlinx.coroutines.flow.*

class QuoteRepositoryImpl(quoteDAO: QuoteDAO): QuoteRepository {
    private val localDataSource= QuoteLocalDataSourceImpl(quoteDAO)
    private val remoteDataSource= QuoteRemoteDataSourceImpl()

    override suspend fun getQuotes(): Flow<List<QuoteModel>> {
        val localQuotes: Flow<List<QuoteModel>> =  localDataSource.getQuotes()
        val remoteQuotes =
            try {
                remoteDataSource.getQuotes()
            } catch (ex: Exception) {
                when (ex) {
                    is NetworkErrorException -> throw ex
                    else -> null
                }
            }

        if (remoteQuotes != null) {
            val quotes = ArrayList<QuoteModel>()
            remoteQuotes.collect {
                it?.forEach { quoteModel->
                    Log.e("RESP",quoteModel.toString() )
                    quotes.add(quoteModel)
                }
            }
            localDataSource.insertAll(quotes)
        }
        Log.e("REMOTE:", remoteQuotes.toString())
        return (localQuotes)
    }
}