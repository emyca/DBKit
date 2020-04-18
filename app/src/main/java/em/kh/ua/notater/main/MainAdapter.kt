package em.kh.ua.notater.main

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import em.kh.ua.notater.main.MainAdapter.MainViewHolder
import em.kh.ua.notater.database.Note
import em.kh.ua.notater.databinding.ItemMainBinding


class MainAdapter(
    private var noteList: List<Note>,
    private val longClickListener: OnLongClickListener,
    private val clickListener: View.OnClickListener
) : RecyclerView.Adapter<MainViewHolder>() {

//    override fun onCreateViewHolder(viewGroup: ViewGroup,
//        position: Int): MainViewHolder {
//        return MainViewHolder(LayoutInflater.from(viewGroup.context)
//                .inflate(R.layout.item_main, viewGroup, false))
//    }

    override fun onCreateViewHolder(viewGroup: ViewGroup,
        position: Int): MainViewHolder {
        val itemBinding = ItemMainBinding.inflate(
            LayoutInflater.from(viewGroup.context),viewGroup, false)
        return MainViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MainViewHolder,position: Int){
        val note = noteList[position]
        holder.itemNote.text = note.noteName
        holder.itemView.tag = note
        holder.itemView.setOnLongClickListener(longClickListener)
        holder.itemView.setOnClickListener(clickListener)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

//    inner class MainViewHolder(itemView: View) : ViewHolder(itemView) {
//        var itemNote: TextView = itemView.findViewById(R.id.item_note)
//    }

    inner class MainViewHolder(binding: ItemMainBinding) :
        RecyclerView.ViewHolder(binding.root){
         var itemNote: TextView = binding.itemNote
    }

    fun addListNotes(listNotes: List<Note>) {
        noteList = listNotes
        notifyDataSetChanged()
    }

}