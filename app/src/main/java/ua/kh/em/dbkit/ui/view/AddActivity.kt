package ua.kh.em.dbkit.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import ua.kh.em.dbkit.R
import ua.kh.em.dbkit.ui.viewmodel.AddViewModel
import ua.kh.em.dbkit.data.model.Note
import ua.kh.em.dbkit.databinding.ActivityAddBinding

import ua.kh.em.dbkit.utils.DatePicker
import java.util.*


class AddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddBinding
    private var etName: TextInputEditText? = null
    private var etContent: TextInputEditText? = null
    private var tvDate: TextView? = null
    private var strName: String? = null
    private var strContent: String? = null
    private  var strDate: String? = null
    private  var noteDate: Date? = null
    private lateinit var addViewModel: AddViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupToolbar()
        initViews()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
    }

    private fun initViews() {
        etName = binding.etName
        etContent = binding.etContent
        tvDate = binding.tvDate
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_done -> {
                handleInput()
                true
            }
            R.id.menu_date -> {
                datePickerStart()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleInput() {
        strName = etName?.text.toString().trim { it <= ' ' }
        strContent = etContent?.text.toString().trim { it <= ' ' }
        strDate = tvDate?.text.toString()

        val resDateFormat = this.resources.getString(R.string.date_format)

        if (strName.isNullOrEmpty() || strContent.isNullOrEmpty()
            || strDate.isNullOrEmpty() || strDate == resDateFormat) {
            Toast.makeText(this, R.string.toast_empty, Toast.LENGTH_SHORT).show()
        } else {
            provideViewModel()
            Toast.makeText(this, R.string.toast_add, Toast.LENGTH_SHORT).show()
            startActivity(
                Intent(this, MainActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

    private fun datePickerStart() {
        val newFragment: DialogFragment = DatePicker()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    // Fun to set Date value
    fun setDate(date: Date) {
        noteDate = date
    }

    private fun provideViewModel() {
        addViewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        addViewModel.insertNote(
            Note(noteName = strName, noteContent = strContent, noteDate = noteDate)
        )
    }
}
