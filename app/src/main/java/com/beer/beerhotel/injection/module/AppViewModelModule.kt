package com.beer.beerhotel.injection.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelModule {

//    @Binds
//    @IntoMap
//    @ViewModelKey(UserViewModel::class)
//    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(ManageViewModel::class)
//    abstract fun bindManageViewModel(manageViewModel: ManageViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(PhotoViewModel::class)
//    abstract fun bindPhotoViewModel(photoViewModel: PhotoViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(UploadViewModel::class)
//    abstract fun bindUploadViewModel(uploadViewModel: UploadViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(HealthViewModel::class)
//    abstract fun bindHealthViewModel(healthViewModel: HealthViewModel): ViewModel
//
//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel
//
//    @Binds
//    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}