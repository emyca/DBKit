package em.kh.ua.roomcrud.utils

import java.text.SimpleDateFormat
import java.util.*

object FormatUtil {

    private const val dateFormat = "yyyy-MM-dd"

    fun toYearMonthDay(data: Long): String {
        val date = Date(data)
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()
        return simpleDateFormat.format(date)
    }

    fun dateToString(date: Date): String {
        val simpleDateFormat = SimpleDateFormat(dateFormat, Locale.getDefault())
        simpleDateFormat.timeZone = TimeZone.getDefault()
        return simpleDateFormat.format(date)
    }
}