package com.prathambudhwani.notestask.ui.theme.data

import android.content.Context
import androidx.room.Room
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=[Note::class],version=1)
abstract class NoteDatabase:RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object{
        @Volatile private var INSTANCE:NoteDatabase?=null

        fun getDatabase(context:Context):NoteDatabase{
            return INSTANCE?: synchronized(this){
                Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_db"
                ).build().also{ INSTANCE=it}
            }
        }
    }
}