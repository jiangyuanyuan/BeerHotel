package com.beer.beerhotel.data.dto

/**
 * 用户信息实体类
 */
data class UserInfo(

        val gmtCreated: String?,
        val gmtUpdated: String?,
        /**
         * 用户id
         */
        val uid: Long?,
        /**
         * 手机号码
         */
        var phone: String?,
        /**
         * 真实姓名
         */
        var name: String?,
        /**
         * 昵称
         */
        var nickName: String?,
        /**
         * 性别 0:女 1:男
         */
        var gender: Int?,
        /**
         * 生日
         */
        var birthday: String?,
//        /**
//         * 头像地址
//         */
//        var headPhoto: Upload?,
        /**
         * 用户类型 0：父母端  1：子女端
         */
        val userType: Int?,
        /**
         * 身高
         */
        var height: Int?,
        /**
         * 体重
         */
        var weight: Double?,
        /**
         * 血型
         */
        var bloodType: String?,
        /**
         * SN
         */
        var sn: String?
        /**
         * 人脸识别对应的personId
         */
//        val facePersonId: String?,
        /**
         * 指纹识别对应的personId
         */
//        val fingerPersonId: String?

)

enum class GenderType(val v: Int) {
        MEN(1),
        WOMAN(0),
        OTHER(2)
}
