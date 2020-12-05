package ua.kh.em.dbkit.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import ua.kh.em.dbkit.R
import ua.kh.em.dbkit.ui.view.AddActivity
import java.util.*

class DatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var calendar: Calendar = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use current date for DatePicker default values
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // New instance of DatePickerDialog
        return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)

        // Returns the number of milliseconds
        // since January 1, 1970, 00:00:00 GMT represented by this Date object.
        val date = calendar.time

        // Invoke fun of the Activity
        (activity as AddActivity?)?.setDate(date)

        // Set text of date to TextView
        val tvDate = requireActivity().findViewById<TextView>(R.id.tv_date)
        tvDate.text = FormatUtil.dateToString(date)
    }
}