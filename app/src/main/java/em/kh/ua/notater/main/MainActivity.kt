package em.kh.ua.notater.main

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import em.kh.ua.notater.R
import em.kh.ua.notater.database.Note
import em.kh.ua.notater.adds.AddActivity
import em.kh.ua.notater.databinding.ActivityMainBinding
import em.kh.ua.notater.deletes.DeleteDialog
import em.kh.ua.notater.details.DetailActivity
import java.util.*


class MainActivity : AppCompatActivity(), View.OnLongClickListener, View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MainAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var viewModel: MainViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupToolbar()
        setupRecyclerView()
        provideViewModel()
    }

    private fun setupToolbar() {
        //  binding.toolbar.toolbar
        // is like a namespace
        setSupportActionBar(binding.toolbar.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.menu_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty() || TextUtils.isEmpty(newText)) {
                    provideViewModel()
                } else {
                    //val searchText = "%"+ newText.trim { it <= ' ' } + "%"
                    val searchText = newText.trim { it <= ' ' } + "%"
                    provideViewModel(searchText)
                }
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_search -> {
                provideViewModel()
                true
            }
            R.id.menu_add -> {
                startActivity(Intent(this, AddActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupRecyclerView() {
        val recyclerView = binding.listNote
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        adapter = MainAdapter(ArrayList<Note>(), this, this)
        recyclerView.adapter = adapter
        val dividerItemDecoration: RecyclerView.ItemDecoration = DividerItemDecoration(
            recyclerView.context,
            linearLayoutManager.orientation
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
        adapter.notifyDataSetChanged()
    }

    private fun provideViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel?.readAllNotes()?.observe(this, Observer {
                notes -> adapter.addListNotes(notes)
        })
    }

    private fun provideViewModel(search: String) {
        viewModel?.readSearch(search)?.observe(this, Observer {
                notes -> adapter.addListNotes(notes)
        })
    }

    override fun onLongClick(v: View): Boolean {
        val note = v.tag as Note
        val dialogDelete = DeleteDialog()
        val bundle = Bundle()
        bundle.putParcelable("note_bundle", note)
        dialogDelete.arguments = bundle
        dialogDelete.show(supportFragmentManager, "DeleteDialog")
        return true
    }

    override fun onClick(v: View?) {
        val note = v?.tag as Note
        val intent = Intent(this, DetailActivity::class.java)
        val bundle = Bundle()
        bundle.putParcelable("note_detail", note)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}

