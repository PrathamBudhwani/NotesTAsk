package com.prathambudhwani.notestask.ui.theme.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate=true) val id:Int=0,
    val text:String,
    val timestamp:Long=System.currentTimeMillis()
)
