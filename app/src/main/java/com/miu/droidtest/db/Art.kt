package com.miu.droidtest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "arts")
data class Art(
    var artName: String,
    var artistName: String,
    var artYear: Int,
    var imgUrl: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)