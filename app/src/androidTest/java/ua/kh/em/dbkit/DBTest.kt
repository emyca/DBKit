package ua.kh.em.dbkit

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import ua.kh.em.dbkit.data.database.NoteDatabase
import java.io.IOException

// Based on:
// https://www.raywenderlich.com/12678525-testing-android-architecture-components
@RunWith(AndroidJUnit4::class)
abstract class DBTest {

    protected lateinit var db: NoteDatabase

    @Before
    fun initDb() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), NoteDatabase::class.java)
            // Allowing main thread just for testing.
            .allowMainThreadQueries()
            .build()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}