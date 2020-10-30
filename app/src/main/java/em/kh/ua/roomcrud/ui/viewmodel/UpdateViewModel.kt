package em.kh.ua.roomcrud.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import em.kh.ua.roomcrud.data.model.Note
import em.kh.ua.roomcrud.data.database.NoteDatabase
import em.kh.ua.roomcrud.data.repository.NoteRepository
import kotlinx.coroutines.launch

class UpdateViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository

    init {
        val notesDao = NoteDatabase.getDatabase(application).noteDao()
        repository = NoteRepository(notesDao)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        repository.updateNote(note)
    }
}