package com.beer.beerhotel.injection.module


import dagger.Module

@Module(includes = [AppViewModelModule::class])
class AppModule {

//    @Singleton
//    @Provides
//    fun provideUserService(): UserService {
//        return RetrofitFactory.instance.create(UserService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideManageService(): ManageService {
//        return RetrofitFactory.instance.create(ManageService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun providePhotoService(): PhotoService {
//        return RetrofitFactory.instance.create(PhotoService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideUploadService(): UploadService {
//        return RetrofitFactory.instance.create(UploadService::class.java)
//    }
//
//    @Singleton
//    @Provides
//    fun provideHealthService(): HealthService {
//        return RetrofitFactory.instance.create(HealthService::class.java)
//    }

}