package em.kh.ua.notater.details

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import em.kh.ua.notater.R
import em.kh.ua.notater.database.Note
import em.kh.ua.notater.databinding.ActivityDetailBinding
import em.kh.ua.notater.updates.UpdateActivity

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var itemId: Int? = null
    private var detailName: TextView? = null
    private var detailContent: TextView? = null

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
    }

    private fun handleParcel(){
        val intent = intent
        val bundle = intent.extras
        if (bundle != null) {
            val note: Note? = bundle.getParcelable("note_detail")
            if (note != null) {
                detailName?.text = note.noteName
                detailContent?.text = note.noteContent
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
