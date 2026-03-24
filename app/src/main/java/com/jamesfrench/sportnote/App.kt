package com.jamesfrench.sportnote

import android.app.Application
import com.jamesfrench.sportnote.database.ObjectBox

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        ObjectBox.init(this)
    }
}