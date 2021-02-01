package ua.kh.em.dbkit

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ua.kh.em.dbkit.data.model.Note
import ua.kh.em.dbkit.utils.Converters


// Based on:
// https://www.raywenderlich.com/12678525-testing-android-architecture-components
@RunWith(AndroidJUnit4::class)
open class NoteDaoTest: DBTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var converter: Converters

    @Test
    fun insertNoteTest() = runBlocking {

        // Test date: 2021-01-25
        val someDate = 1611535187169
        // Converting to Date format
        converter = Converters()
        val noteDate = converter.fromTimestamp(someDate)

        val note = Note(noteId = 1, noteName = "January", noteContent = "cool and snow",
            noteDate = noteDate)
        db.noteDao().insertNote(note)
        // Warning "Inappropriate blocking method call"
        // will disappear when removing
        // @Throws(InterruptedException::class)
        // in UtilTest.kt.
        // See also:
        // https://discuss.kotlinlang.org/t/warning-inappropriate-blocking-method-call-with-coroutines-how-to-fix/16903.
        val noteSize = db.noteDao().readAllNotes().getValueBlocking()?.size
        assertEquals(noteSize, 1)
    }

    @Test
    fun updateNoteTest() = runBlocking  {

        // Test date: 2021-01-25
        val someDate = 1611535187169
        // Converting to Date format
        converter = Converters()
        val noteDate = converter.fromTimestamp(someDate)

        val note = Note(noteId = 1, noteName = "April", noteContent = "Warm",
            noteDate = noteDate)
        db.noteDao().insertNote(note)

        val name = "April"
        val content = "Light and green"
        val id = 1

        db.noteDao().updateData(name,content, id)
        assertEquals(db.noteDao().readAllNotes().getValueBlocking()?.get(0)?.noteContent,
            "Light and green")
    }

    @Test
    fun deleteNoteTest() = runBlocking {

        // Test date: 2021-01-25
        val someDate = 1611535187169
        // Converting to Date format
        converter = Converters()
        val noteDate = converter.fromTimestamp(someDate)

        val note = Note(noteId = 1, noteName = "July", noteContent = "It's hot",
            noteDate = noteDate)
        db.noteDao().insertNote(note)
        assertEquals(db.noteDao().readAllNotes().getValueBlocking()?.size, 1)
        db.noteDao().deleteNote(note)
        assertEquals(db.noteDao().readAllNotes().getValueBlocking()?.size, 0)
    }

}


