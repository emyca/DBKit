package em.kh.ua.roomcrud.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import em.kh.ua.roomcrud.R
import java.util.*

class DatePicker : DialogFragment(), DatePickerDialog.OnDateSetListener {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use current date for DatePicker default values
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]

        // New instance of DatePickerDialog
       return DatePickerDialog(requireActivity(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {

        // Don't figure out yet how to use ViewBinding here.
        // All experiments failed. TextView can't show date from DatePicker.
        val tvDate = requireActivity().findViewById<TextView>(R.id.tv_date)
        // Jan == 0
        val monthNumber = month + 1
        // %02d - 2 places for Int,
        // if value has 1 place, an other 1 place will be 0
        val format = "%d-%02d-%02d"
        tvDate.text = String.format(Locale.getDefault(), format, year, monthNumber, day)
    }
}