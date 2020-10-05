package em.kh.ua.roomcrud.utils

import androidx.room.TypeConverter
import java.util.*

// based on: https://developer.android.com
class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}