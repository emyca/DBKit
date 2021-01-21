package ua.kh.em.dbkit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ua.kh.em.dbkit.data.model.Note


// Based on:
// https://www.raywenderlich.com/12678525-testing-android-architecture-components
@RunWith(AndroidJUnit4::class)
open class NoteDaoTest: DBTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertNoteTest() = runBlocking {
        val note = Note(noteId = 1, noteName = "January", noteContent = "cool and snow",
            noteDate = null)
        db.noteDao().insertNote(note)
        val noteSize = db.noteDao().readAllNotes().getValueBlocking()?.size
        assertEquals(noteSize, 1)
    }



}


