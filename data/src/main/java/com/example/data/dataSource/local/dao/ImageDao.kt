package com.example.data.dataSource.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.dataSource.local.entity.ImageEntity

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(images: List<ImageEntity>)

    @Query("SELECT * FROM image_table WHERE searchString LIKE :query")
    fun queryImages(query: String): PagingSource<Int, ImageEntity>

    @Query("SELECT * FROM image_table WHERE id =:id LIMIT 1")
    suspend fun getImagesById(id: String): ImageEntity

    @Query("DELETE FROM image_table")
    suspend fun clearAll()
}