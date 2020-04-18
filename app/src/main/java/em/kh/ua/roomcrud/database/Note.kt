package em.kh.ua.roomcrud.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class Note (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val noteId: Int? = null,

    @ColumnInfo(name = "name")
    val noteName: String? = null,

    @ColumnInfo(name = "content")
    val noteContent: String? = null

):Parcelable
