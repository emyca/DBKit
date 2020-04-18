package em.kh.ua.notater.deletes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import em.kh.ua.notater.database.Note
import em.kh.ua.notater.database.NoteDatabase
import em.kh.ua.notater.repository.NoteRepository
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