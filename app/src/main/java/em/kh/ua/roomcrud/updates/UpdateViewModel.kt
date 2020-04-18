package em.kh.ua.roomcrud.updates

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import em.kh.ua.roomcrud.database.Note
import em.kh.ua.roomcrud.database.NoteDatabase
import em.kh.ua.roomcrud.repository.NoteRepository
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