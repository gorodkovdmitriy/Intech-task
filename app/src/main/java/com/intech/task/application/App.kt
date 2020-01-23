package com.intech.task.application

import android.app.Application
import com.intech.task.di.component.AppComponent
import com.intech.task.di.component.DaggerAppComponent
import com.intech.task.di.module.AppModule

class App : Application() {

    companion object {
        lateinit var dagger: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        // Генерируем граф зависимостей
        createDaggerGraph()
    }

    private fun createDaggerGraph() {
       dagger = DaggerAppComponent.builder()
           .appModule(AppModule(this))
           .build()
    }

}