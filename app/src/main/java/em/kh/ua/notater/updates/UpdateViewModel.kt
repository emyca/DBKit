package em.kh.ua.notater.updates

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import em.kh.ua.notater.database.Note
import em.kh.ua.notater.database.NoteDatabase
import em.kh.ua.notater.repository.NoteRepository
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