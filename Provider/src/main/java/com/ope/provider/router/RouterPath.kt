package com.ope.provider.router

/**
 * Created by wangshufu on 2018/3/27.
 *
 * 注意:不同的模块间,前面的地址要不同
 *
 */
object RouterPath {

    class Main{
        companion object {
            const val PATH_WELCOME = "/Main/Welcome"
            const val PATH_CLAUSE = "/Main/Clause"
            const val PATH_MAIN = "/Main/Main"
            const val PATH_MAIN_FRAGMENT = "/Main/MainFragment"
        }
    }
    class Web{
        companion object {
            const val PATH_WEB = "/Web/Web"
        }
    }

    //用户模块
    class UserCenter{
        companion object {
            const val PATH_LOGIN = "/UserCenter/Login"
            const val PATH_QUICK_LOGIN = "/UserCenter/QuickLogin"
            const val PATH_REGISTER = "/UserCenter/Register"
            const val PATH_RESET = "/UserCenter/Reset"
            const val PATH_LOGOUT = "/UserCenter/Logout"
            const val PATH_USERINFO = "/UserCenter/UserInfo"
        }
    }

    //家庭成员管理模块
    class UserManageCenter{
        companion object {
            const val PATH_MANAGE = "/UserManageCenter/Manage"
            const val PATH_ADD = "/UserManageCenter/Add"
            const val PATH_DELETE = "/UserManageCenter/Delete"
        }
    }

    //健康
    class HealthCenter{
        companion object {
            const val PATH_HEALTH = "/HealthCenter/Health"
            const val PATH_MEASURE = "/HealthCenter/Measure"
        }
    }

    //相册
    class PhotoCenter{
        companion object {
            const val PATH_PHOTO = "/PhotoCenter/Photo"
            const val PATH_OTHER = "/PhotoCenter/Other"
            const val PATH_MY = "/PhotoCenter/My"
            const val PATH_UPLOAD = "/PhotoCenter/Upload"
        }
    }

    //我的
    class MyCenter{
        companion object {
            const val PATH_My = "/MyCenter/My"
            const val PATH_ABOUT = "/MyCenter/About"
            const val PATH_FEEDBACK = "/MyCenter/Feedback"
            const val PATH_UPDATE = "/MyCenter/Update"
            const val PATH_LOGIN = "/MyCenter/Login"
            const val PATH_REGISTER = "/MyCenter/Register"
            const val PATH_RETRIEVEPWD = "/MyCenter/RetrievePwd"
            const val PATH_RELOGIN = "/MyCenter/ReLogin"
            const val PATH_AGREEMENT = "/MyCenter/Agreement"
            const val PATH_DIGITALASSETS = "/MyCenter/DigitalAssets"
        }
    }

}