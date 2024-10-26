package com.golvia.ng.businessLayer.core

import com.golvia.ng.common.Context
import com.golvia.ng.common.getData
import com.golvia.ng.common.putData

const val APP_DATASTORE = "com.razzaghi.shoppingbykmp"

class AppDataStoreManager(val context: Context) : AppDataStore {

    override suspend fun setValue(
        key: String,
        value: String
    ) {
        context.putData(key, value)
    }

    override suspend fun readValue(
        key: String,
    ): String? {
        return context.getData(key)
    }
}