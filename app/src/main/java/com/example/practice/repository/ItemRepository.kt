package com.example.practice.repository

import com.example.practice.base.BaseRepository
import com.example.practice.network.RetrofitClient
import com.example.practice.utils.Constants.BASE_URL


class ItemRepository : BaseRepository() {

    ////API End pints
    fun getCountryList() =
        RetrofitClient.getInterfaceService(
            BASE_URL
        ).getAllCountries()

    fun getCountryDetail() = RetrofitClient.getInterfaceService(
        BASE_URL
    ).getCountryDetail()

    companion object {
        private var instance: ItemRepository? = null
        fun getInstance(): ItemRepository {
            if (instance == null)
                instance =
                    ItemRepository()
            return instance!!
        }
    }
}