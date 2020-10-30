package em.kh.ua.roomcrud.ui.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import em.kh.ua.roomcrud.R
import em.kh.ua.roomcrud.data.model.Note
import em.kh.ua.roomcrud.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var itemId: Int? = null
    private var detailName: TextView? = null
    private var detailContent: TextView? = null
    private var detailDate: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupToolbar()
        initViews()
        handleParcel()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.toolbar.toolbar)
    }

    private fun initViews(){
        detailName = binding.detailName
        detailContent = binding.detailContent
        detailDate = binding.detailDate
    }

    private fun handleParcel(){
        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            val note: Note? = bundle.getParcelable("note_detail")
            if (note != null) {
                detailName?.text = note.noteName
                detailContent?.text = note.noteContent
                detailDate?.text = note.noteDate
                itemId = note.noteId
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_edit -> {
                val intent = Intent(
                    this@DetailActivity,
                    UpdateActivity::class.java
                )
                val strId = Integer.valueOf(itemId!!).toString()
                val strName = detailName!!.text.toString()
                val strContent = detailContent!!.text.toString()
                val bundle = Bundle()
                bundle.putString("note_id", strId)
                bundle.putString("note_name", strName)
                bundle.putString("note_content", strContent)
                intent.putExtras(bundle)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
