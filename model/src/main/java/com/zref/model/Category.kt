package com.zref.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Category(
    @SerializedName("id") @PrimaryKey var id: Int = 0,
    @SerializedName("name") var name: String = ""
): RealmObject()