package em.kh.ua.roomcrud.repository

import androidx.lifecycle.LiveData
import em.kh.ua.roomcrud.database.Note
import em.kh.ua.roomcrud.database.NoteDao


class NoteRepository (private val noteDao: NoteDao){

    private var noteList: LiveData<List<Note>>? = null

    fun readAllNotes(): LiveData<List<Note>> {
        noteList = noteDao.readAllNotes()
        return noteList as LiveData<List<Note>>
    }

    fun readSearch(search: String?): LiveData<List<Note>> {
        noteList = noteDao.readSearch(search)
        return noteList as LiveData<List<Note>>
    }

    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }


}