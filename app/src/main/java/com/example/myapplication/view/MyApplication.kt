package com.example.myapplication.view

import android.app.Application
import com.example.myapplication.di.modules.coreModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
   override fun onCreate(){
        super.onCreate();
       startKoin {
           androidContext(this@MyApplication)
           modules(coreModules)
       }

    }
}