package com.example.a63.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore



private val Context.dataStore by preferencesDataStore(name = "settings")


class PreferencesManager {
    companion object {
        val AUTORIZATION_TOKEN = stringPreferencesKey("f")
    }
}

