package com.example.effective_testapp.data.bd

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase

@Dao
interface UserDao{
    @Query("SELECT * FROM UserData")
    suspend fun getData(): List<UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(userData: UserData)

    @Delete
    fun unAuthorize(userData: UserData)
}
@Database(entities = [UserData::class], version = 2, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun DaoUser(): UserDao

    companion object {
        @Volatile
        private var instance: UserDataBase? = null

        @Synchronized
        fun newInstance(context: Context): UserDataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                UserDataBase::class.java,
                "UserData")
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}