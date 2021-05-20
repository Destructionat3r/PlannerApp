package com.sid1818713.plannerapp.moduledata

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sid1818713.plannerapp.moduledata.model.Module

@Database(entities = [Module::class], version = 1, exportSchema = false)
abstract class ModuleDatabase: RoomDatabase() {
    abstract fun moduleDao(): ModuleDao

    companion object {
        @Volatile
        private var INSTANCE: ModuleDatabase? = null

        fun getDatabase(context: Context): ModuleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ModuleDatabase::class.java,
                        "module_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}