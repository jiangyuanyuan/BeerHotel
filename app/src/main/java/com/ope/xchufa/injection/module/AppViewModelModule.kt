package com.ope.xchufa.injection.module

import android.arch.lifecycle.ViewModel
import com.ope.base.injection.ViewModelKey
import com.ope.xchufa.data.vm.TradeViewModel
import com.ope.xchufa.data.vm.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(userViewModel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TradeViewModel::class)
    abstract fun bindTradeViewModel(tradeViewModel: TradeViewModel): ViewModel

}