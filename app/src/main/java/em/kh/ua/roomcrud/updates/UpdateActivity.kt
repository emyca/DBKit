package em.kh.ua.roomcrud.updates

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import em.kh.ua.roomcrud.R
import em.kh.ua.roomcrud.database.Note
import em.kh.ua.roomcrud.databinding.ActivityUpdateBinding
import em.kh.ua.roomcrud.main.MainActivity

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private val context: Context = this
    private var etUpname: TextInputEditText? = null
    private var etUpcontent: TextInputEditText? = null
    private var itemId = 0
    private lateinit var updateViewModel: UpdateViewModel
    private var strName = ""
    private var strContent = ""
    private var idNote = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupToolbar()
        initViews()
        handleParcel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
    }

    private fun initViews() {
        etUpname = binding.etUpname
        etUpcontent = binding.etUpcontent
    }

    private fun handleParcel(){
        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            val idNote = bundle["note_id"] as String?
            val nameNote = bundle["note_name"] as String?
            val contentNote = bundle["note_content"] as String?
            if (idNote != null) {
                itemId = idNote.toInt()
            }
            etUpname?.setText(nameNote)
            etUpcontent?.setText(contentNote)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_update, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_done -> {
                handleInput()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun handleInput() {
        strName = etUpname?.text.toString().trim { it <= ' ' }
        strContent = etUpcontent?.text.toString().trim { it <= ' ' }
        idNote = itemId
        if (strName.isEmpty() || strContent.isEmpty()) {
            Toast.makeText(this, R.string.toast_empty, Toast.LENGTH_SHORT).show()
        } else {
            provideViewModel()
            Toast.makeText(this, R.string.toast_update, Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, MainActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            )
        }
    }

    private fun provideViewModel() {
        updateViewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        updateViewModel.updateNote(Note(idNote, strName, strContent))
    }

    override fun onBackPressed() {
        startActivity(Intent(context, MainActivity::class.java)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
        super.onBackPressed()
    }

}
