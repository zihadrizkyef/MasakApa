package com.zref.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Ingredient(
    @SerializedName("name") var name: String? = null,
    @SerializedName("measure") var measure: String? = null
): RealmObject()