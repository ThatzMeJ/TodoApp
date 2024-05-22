package com.example.todoapp.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import java.util.prefs.Preferences


private val Context.themeDataStore: DataStore<Preferences> by preferencesDataStore(name = "THEME_KEYS")



class UserRepository(context: Context) {

}