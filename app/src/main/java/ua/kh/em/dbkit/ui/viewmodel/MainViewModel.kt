package ua.kh.em.dbkit.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import ua.kh.em.dbkit.data.model.Note
import ua.kh.em.dbkit.data.database.NoteDatabase
import ua.kh.em.dbkit.data.repository.NoteRepository

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
