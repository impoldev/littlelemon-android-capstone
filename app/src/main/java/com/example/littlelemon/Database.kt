package com.example.littlelemon

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Entity
data class MenuItem (
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val image: String,
    val category: String
)

@Dao
interface MenuDao {
    @Query("SELECT * FROM MenuItem")
    fun getAllMenuItems(): LiveData<List<MenuItem>>

    @Insert
    suspend fun saveMenuItem(menuItem: MenuItem)

    @Query("SELECT COUNT(*) FROM MenuItem")
    suspend fun getCount(): Int

    @Query("SELECT COUNT(*) == 0 FROM menuitem")
    fun isEmpty(): Boolean
}

@Database(entities = [MenuItem::class], version = 1)
abstract class MenuDatabase: RoomDatabase() {
    abstract fun menuDao(): MenuDao
}