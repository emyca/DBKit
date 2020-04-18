package em.kh.ua.roomcrud.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import em.kh.ua.roomcrud.database.Note
import em.kh.ua.roomcrud.database.NoteDatabase
import em.kh.ua.roomcrud.repository.NoteRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository
    private lateinit var allNotes: LiveData<List<Note>>


    init {
        val notesDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(notesDao)
    }

    fun readAllNotes(): LiveData<List<Note>> {
        allNotes = repository.readAllNotes()
        return allNotes
    }

    fun readSearch(search: String?): LiveData<List<Note>> {
        allNotes = repository.readSearch(search)
        return allNotes
    }


}
