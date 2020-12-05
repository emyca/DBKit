package ua.kh.em.dbkit.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ua.kh.em.dbkit.data.model.Note
import ua.kh.em.dbkit.data.database.NoteDatabase
import ua.kh.em.dbkit.data.repository.NoteRepository
import kotlinx.coroutines.launch

class DeleteViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository

    init {
        val notesDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(notesDao)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }
}