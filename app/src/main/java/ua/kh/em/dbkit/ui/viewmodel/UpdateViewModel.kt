package ua.kh.em.dbkit.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import ua.kh.em.dbkit.data.model.Note
import ua.kh.em.dbkit.data.database.NoteDatabase
import ua.kh.em.dbkit.data.repository.NoteRepository
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository

    init {
        val notesDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(notesDao)
    }

//    replaced with updateData()
//    fun updateNote(note: Note) = viewModelScope.launch {
//        repository.updateNote(note)
//    }

    fun updateData(name: String, content: String, id: Int) = viewModelScope.launch {
        repository.updateData(name, content, id)
    }
}