package ua.kh.em.dbkit.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import ua.kh.em.dbkit.data.model.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun readAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE name LIKE :search")
    fun readSearch(search: String?): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    // replaced with updateData(), because it sets date to null
    // @Update(onConflict = OnConflictStrategy.REPLACE)
    // suspend fun updateNote(note: Note)

    @Query("UPDATE notes SET name = :name, content = :content WHERE id = :id")
    suspend fun updateData(name: String, content: String, id: Int)

    @Delete
    suspend fun deleteNote(note: Note)
}