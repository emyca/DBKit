package em.kh.ua.roomcrud.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import em.kh.ua.roomcrud.data.model.Note


@Dao
interface NoteDao {

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun readAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM notes WHERE name LIKE :search")
    fun readSearch(search: String?): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)
}