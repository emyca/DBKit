package em.kh.ua.roomcrud.deletes

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import em.kh.ua.roomcrud.R
import em.kh.ua.roomcrud.database.Note

class DeleteDialog : DialogFragment() {

    private var note: Note? = null
    private var deleteViewModel: DeleteViewModel? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = MaterialAlertDialogBuilder(requireActivity(),R.style.DialogStyle)
        builder.setTitle(R.string.dialog_del_title)
            .setMessage(R.string.dialog_del_text)
            .setPositiveButton(R.string.dialog_ok) { _, _ ->
                handleParcel()
            }
            .setNegativeButton(R.string.dialog_cancel) { _, _ ->
                Toast.makeText(requireActivity(), R.string.toast_cancel, Toast.LENGTH_SHORT)
                    .show()
            }
        return builder.create()
    }

    private fun handleParcel() {
        val bundle = arguments
        if (bundle != null) {
            note = bundle.getParcelable("note_bundle")
            provideViewModel()
            Toast.makeText(requireActivity(), R.string.toast_delete, Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun provideViewModel() {
        deleteViewModel = ViewModelProvider(requireActivity()).get(DeleteViewModel::class.java)
        note?.let { deleteViewModel?.deleteNote(it) }
    }
}