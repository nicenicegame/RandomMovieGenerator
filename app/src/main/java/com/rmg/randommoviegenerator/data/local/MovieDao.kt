package com.rmg.randommoviegenerator.data.local

import androidx.room.*
import com.rmg.randommoviegenerator.data.models.DatabaseMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: DatabaseMovie)

    @Delete
    suspend fun delete(movie: DatabaseMovie)

    @Query("SELECT * FROM movies ORDER BY created_at ASC")
    fun getMovies(): Flow<List<DatabaseMovie>>

    @Query("SELECT * FROM movies WHERE id = :movieId")
    fun getMovieById(movieId: Int): Flow<DatabaseMovie>

    @Query("SELECT EXISTS (SELECT * FROM movies WHERE id = :id LIMIT 1)")
    fun isFavourite(id: Int): Flow<Boolean>
}