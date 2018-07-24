package com.sharonov.nikiz.nikizinstagram.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Intent
import android.os.Bundle
import com.sharonov.nikiz.nikizinstagram.HomeActivity
import com.sharonov.nikiz.nikizinstagram.R
import com.sharonov.nikiz.nikizinstagram.screen.user_settings.SignOutPresenter
import com.sharonov.nikiz.nikizinstagram.screen.user_settings.SignOutView

class SignOutDialogFragment: DialogFragment(), SignOutView {

    private lateinit var presenter: SignOutPresenter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.prompt_title_sign_out)
                .setMessage(R.string.prompt_sign_out)
                .setPositiveButton(R.string.prompt_ok, { dialog, _ ->
                    presenter.signOut()

                })
        return builder.create()
    }

    override fun closeActivity() {
        activity.finish()
    }

    override fun openHomeActivity() {
        val intent = Intent(activity, HomeActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        presenter = SignOutPresenter(this)
        presenter.setupFirebaseAuth()
        presenter.addAuthStateListener()
    }

    override fun onStop() {
        super.onStop()
        presenter.removeAuthStateListener()
    }
}