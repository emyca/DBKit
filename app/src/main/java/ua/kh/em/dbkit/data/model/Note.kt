package ua.kh.em.dbkit.data.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import ua.kh.em.dbkit.utils.Converters
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    //val noteId: Int? = null,
    val noteId: Long = 0,

    @ColumnInfo(name = "name")
    var noteName: String? = null,

    @ColumnInfo(name = "content")
    var noteContent: String? = null,

    @ColumnInfo(name = "date")
    @TypeConverters(Converters::class)
    val noteDate: Date? = null

):Parcelable
