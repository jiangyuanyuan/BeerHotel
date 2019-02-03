package com.beer.baselibrary.helper

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import android.util.Log
import java.util.ArrayList
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import com.beer.baselibrary.data.dto.Contact


object ContactUtil {

    /**
     * 获取所有联系人id
     */
    fun getAllContactId(contentResolver: ContentResolver): ArrayList<String> {
        val allContactsId = ArrayList<String>()
        val cursor = contentResolver?.query(
                ContactsContract.Contacts.CONTENT_URI, arrayOf("_id"), null, null, null)
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    val contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID)//获取 id 所在列的索引
                    val contactId = cursor.getString(contactIdIndex)//联系人id
                    allContactsId.add(contactId)
                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return allContactsId
    }

    /**
     * 获取联系人的图片
     */
    private fun getPhoto(contentResolver: ContentResolver, contactId: String): Bitmap? {
        var photo: Bitmap? = null
        val dataCursor = contentResolver.query(ContactsContract.Data.CONTENT_URI,
                arrayOf("data15"),
                ContactsContract.Data.CONTACT_ID + "=?" + " AND "
                        + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'",
                arrayOf((contactId).toString()), null)
        if (dataCursor != null) {
            if (dataCursor.count > 0) {
                dataCursor.moveToFirst()
                val bytes = dataCursor.getBlob(dataCursor.getColumnIndex("data15"))
                if (bytes != null) {
                    photo = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                }
            }
            dataCursor.close()
        }
        return photo
    }

    /**
     * 获取所有联系人信息-姓名-电话-头像
     */
    fun getAllContacts(contentResolver: ContentResolver): ArrayList<Contact> {

        val contacts = ArrayList<Contact>()


        val cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null)
        while (cursor.moveToNext()) {

            //联系人id
            val contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID))
            //联系人姓名
            val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
//            val mimetype = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE))
//            logd("mimetype:$mimetype")
//            if (mimetype == ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE) {
//                //联系人备注
//                val remark = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE))
//                logd("备注:$remark")
//            }
//            if (mimetype == ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE) {
//                //联系人邮箱
//                val email = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
//                logd("邮箱:$email")
//            }
            val temp = Contact(contactId, null, ArrayList<String>(), null)
            temp.name = name

            //获取联系人所有电话号
            val phones = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                            + contactId, null, null)
            while (phones.moveToNext()) {

                val phoneNumber = phones
                        .getString(phones
                                .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                temp.phones.add(phoneNumber)
            }
            phones.close()

            // 获取联系人头像
            temp.photo = getPhoto(contentResolver, contactId)
            contacts.add(temp)
        }
        cursor.close()
        return contacts
    }

    private fun saveContact(contentResolver: ContentResolver,
                            name: String = "", phone: String, compressPath: String = "",
                            email: String = "", address: String = "", company: String = "", post: String = "",
                            remark: String = "") {
        val values = ContentValues()
        val rawContactUri = contentResolver.insert(ContactsContract.RawContacts.CONTENT_URI, values)
        val rawContactId = ContentUris.parseId(rawContactUri)

        //添加姓名
        name.isNotBlank().apply {
            values.clear()
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            // 内容类型
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
            // 联系人名字
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name)
            // 向联系人URI添加联系人名字
            contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        }

        // todo 添加电话号码 目前只添加了一个电话,还需要加多个电话的api
        values.clear()
        values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
        values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
        // 联系人的电话号码
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
        // 电话类型
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
        // 向联系人电话号码URI添加电话号码
        contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)

        //添加头像
        compressPath.isNotBlank().apply {
            values.clear()
            val file = File(compressPath)
            if (file.exists()) {
                values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
                values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                // 联系人的电话号码
                values.put(ContactsContract.CommonDataKinds.Photo.PHOTO, readStream(file))
                contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
            }
        }

        //添加邮箱
        email.isNotBlank().apply {
            values.clear()
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
            // 联系人的Email地址
            values.put(ContactsContract.CommonDataKinds.Email.DATA, email)
            // 电子邮件的类型
            values.put(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
            // 向联系人Email URI添加Email数据
            contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        }

        //添加地址
        address.isNotBlank().apply {
            values.clear()
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/postal-address_v2")
            values.put(ContactsContract.CommonDataKinds.StructuredPostal.DATA, address)
            contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        }

        //添加单位和职务
        if (company.isNotBlank() && post.isNotBlank()) {
            values.clear()
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/organization")
            values.put(ContactsContract.CommonDataKinds.Organization.DATA, company)//单位
            values.put(ContactsContract.CommonDataKinds.Organization.TITLE, post)//职务
            contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        }

        //添加备注
        remark.isNotBlank().apply {
            values.clear()
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId)
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)
            values.put(ContactsContract.CommonDataKinds.Note.NOTE, remark)//备注
            contentResolver.insert(ContactsContract.Data.CONTENT_URI, values)
        }


    }

    @Throws(Exception::class)
    fun readStream(file: File): ByteArray {
        val input = FileInputStream(file)
        val buffer = ByteArray(1024)
        var len = -1
        val outStream = ByteArrayOutputStream()

        do {
            len = input.read(buffer)
            if (len == -1) {
                break
            }
            outStream.write(buffer, 0, len)
        } while (true)

        val data = outStream.toByteArray()
        outStream.close()
        input.close()
        return data
    }

    /**
     * 根据id删除联系人
     */
    fun deleteContactById(contentResolver: ContentResolver, contactId: String) {
        //第一步先删除Contacts表中的数据
        contentResolver.delete(ContactsContract.Contacts.CONTENT_URI, ContactsContract.Contacts._ID + " =?", arrayOf(contactId))
        //第二步再删除RawContacts表的数据
        contentResolver.delete(ContactsContract.RawContacts.CONTENT_URI, ContactsContract.RawContacts.CONTACT_ID + " =?", arrayOf(contactId))
    }

    /**
     * 根据id修改联系人
     */
    fun updateContactById(contentResolver: ContentResolver, contactId: String, name: String = "", phone: String = "", compressPath: String = "") {
        val uri = Uri.parse("content://com.android.contacts/data")
        val values = ContentValues()
        phone.isNotBlank().apply {
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone)
            contentResolver.update(uri, values, "mimetype=? and raw_contact_id=?", arrayOf(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE, contactId))
            values.clear()
        }

        name.isNotBlank().apply {
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name)
            contentResolver.update(uri, values, "mimetype=? and raw_contact_id=?", arrayOf(ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE, contactId))
            values.clear()
        }

        compressPath.isNotBlank().apply {
            val file = File(compressPath)
            if (file.exists()) {
                // 联系人的电话号码
                values.put(ContactsContract.CommonDataKinds.Photo.PHOTO, readStream(file))
                contentResolver.update(uri, values, "mimetype=? and raw_contact_id=?",
                        arrayOf(ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE, contactId))
            }
        }

    }

    fun getAll(contentResolver: ContentResolver) {
        //uri = content://com.android.contacts/contacts
        var uri = Uri.parse("content://com.android.contacts/contacts") //访问raw_contacts表
        //获得_id属性
        val cursor = contentResolver.query(uri, arrayOf(ContactsContract.Data._ID), null, null, null)
        while (cursor!!.moveToNext()) {
            val buf = StringBuilder()
            //获得id并且在data中寻找数据
            val id = cursor.getInt(0)
            buf.append("id=$id")
            uri = Uri.parse("content://com.android.contacts/contacts/$id/data")
            //data1存储各个记录的总数据，mimetype存放记录的类型，如电话、email等
            val cursor2 = contentResolver.query(uri, arrayOf(ContactsContract.Data.DATA1, ContactsContract.Data.MIMETYPE), null, null, null)
            while (cursor2!!.moveToNext()) {
                val data = cursor2.getString(cursor2.getColumnIndex("data1"))
                if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/name")) {       //如果是名字
                    buf.append(",name=$data")
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/phone_v2")) {  //如果是电话
                    buf.append(",phone=$data")
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/email_v2")) {  //如果是email
                    buf.append(",email=$data")
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/postal-address_v2")) { //如果是地址
                    buf.append(",address=$data")
                } else if (cursor2.getString(cursor2.getColumnIndex("mimetype")).equals("vnd.android.cursor.item/organization")) {  //如果是组织
                    val company = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY))
                    buf.append(",company=$company")
                    val jobTitle = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE))
                    buf.append(",jobTitle=$jobTitle")
//                    // 取出组织类型
//                    val orgType = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TYPE))
//                    // 单位
//                    if (orgType == ContactsContract.CommonDataKinds.Organization.TYPE_CUSTOM) {
//                        val company = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Organization.COMPANY))
//                        buf.append(",company=$company")
//                        val jobTitle = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE))
//                        buf.append(",jobTitle=$jobTitle")
//                        val department = cursor2.getString(cursor2.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DEPARTMENT))
//                        buf.append(",department=$department")
//                    }
                }
            }
            cursor2.close()
            val str = buf.toString()
            Log.d("Contacts", str)
        }
        cursor.close()
    }


}