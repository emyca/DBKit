package em.kh.ua.roomcrud.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import em.kh.ua.roomcrud.utils.Converters
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "notes")
data class Note(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val noteId: Int? = null,

    @ColumnInfo(name = "name")
    val noteName: String? = null,

    @ColumnInfo(name = "content")
    val noteContent: String? = null,

    @ColumnInfo(name = "date")
    @TypeConverters(Converters::class)
    val noteDate: Date? = null

):Parcelable
