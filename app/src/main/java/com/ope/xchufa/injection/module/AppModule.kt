package com.ope.xchufa.injection.module


import com.ope.base.data.net.RetrofitFactory
import com.ope.xchufa.data.api.UserService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppViewModelModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideUserService(): UserService {
        return RetrofitFactory.instance.create(UserService::class.java)
    }


}