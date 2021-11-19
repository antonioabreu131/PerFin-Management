package com.example.perfinmanagement.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.perfinmanagement.data.dao.ExpenseDao
import com.example.perfinmanagement.data.entities.Expense

@Database(entities = [Expense::class], version = 1, exportSchema = false)
abstract class PerFinDatabase: RoomDatabase() {

    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: PerFinDatabase? = null

        fun getDatabase(context: Context): PerFinDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PerFinDatabase::class.java,
                    "perfin_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}