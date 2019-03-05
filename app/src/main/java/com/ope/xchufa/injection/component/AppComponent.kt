package com.ope.xchufa.injection.component


import com.ope.xchufa.MainActivity
import com.ope.xchufa.MainFragment
import com.ope.xchufa.injection.module.AppModule
import com.ope.xchufa.my.user.AgreementFragment
import com.ope.xchufa.my.MyFragment
import com.ope.xchufa.my.digitalAssets.DigitalAssetsFragment
import com.ope.xchufa.my.login.LoginActivity
import com.ope.xchufa.my.user.RegisterFragment
import com.ope.xchufa.my.user.ResetPwdFragment
import com.ope.xchufa.my.user.RetrievePwdFragment

import javax.inject.Singleton

@Singleton
@dagger.Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: LoginActivity)
    fun inject(fragment: MyFragment)
    fun inject(fragment: MainFragment)
    fun inject(fragment: AgreementFragment)
    fun inject(fragment: RegisterFragment)
    fun inject(fragment: ResetPwdFragment)
    fun inject(fragment: RetrievePwdFragment)
    fun inject(fragment: DigitalAssetsFragment)

}