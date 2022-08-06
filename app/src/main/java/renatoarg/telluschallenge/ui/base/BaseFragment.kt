package renatoarg.telluschallenge.ui.base

import android.app.AlertDialog
import androidx.fragment.app.Fragment
import renatoarg.telluschallenge.R

open class BaseFragment: Fragment() {

    protected fun showAlertDialog(positive: () -> Unit, negative: () -> Unit) {
        requireActivity().let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setTitle(getString(R.string.ops))
                setMessage(getString(R.string.something_went_wrong))
                setPositiveButton(
                    R.string.retry
                ) { dialog, _ ->
                    dialog.dismiss()
                    positive()
                }
                setNegativeButton(
                    R.string.exit
                ) { dialog, _ ->
                    dialog.dismiss()
                    negative
                }
                create()
            }
        }.show()
    }

    protected fun finish() {
        requireActivity().finish()
    }
}