package com.gmail.uia059466.simplemath.forgit.games

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HelpGameDialog : DialogFragment() {

  companion object {
    const val TAG = "HelpGameDialog"
    private const val EXTRA_TEXT = "text"
    fun newInstance( text: String): HelpGameDialog {
      val dialog = HelpGameDialog()
      val args = Bundle().apply {
        putString(EXTRA_TEXT,
                  text)
      }
      dialog.arguments = args
      return dialog
    }
  }

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val text: String? = arguments?.getString(EXTRA_TEXT)

    val builder = MaterialAlertDialogBuilder(requireContext())
            .setMessage(text)
            .setPositiveButton(android.R.string.ok) { _, _ ->
              dismiss()
            }
    return builder.create()
  }
}