package com.juniar.googledriveauth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.drive.Drive
import kotlinx.android.synthetic.main.activity_sign_in.*
import com.google.android.gms.tasks.OnFailureListener
import android.provider.MediaStore
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task


class SignInActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_SIGN_IN = 1
        const val TAG = "iki tag"
    }

    lateinit var googleSignInClient: GoogleSignInClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        googleSignInClient = signInGoogle()
        btn_sign_in.setOnClickListener {
            startActivityForResult(googleSignInClient.signInIntent, REQUEST_CODE_SIGN_IN)
        }
    }

    private fun signInGoogle() = GoogleSignIn.getClient(this, GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestScopes(Drive.SCOPE_FILE)
            .requestScopes(Drive.SCOPE_APPFOLDER)
            .build())

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_SIGN_IN -> {
                if (resultCode == Activity.RESULT_OK) {
                    showToast("sukses")
                } else {
                    showToast("gagal")
                }
            }
        }
    }

    private fun updateViewWithGoogleSignInAccountTask(task: Task<GoogleSignInAccount>) {
        Log.i(TAG, "Update view with sign in account task")
        task.addOnSuccessListener { googleSignInAccount ->
            Log.i(TAG, "Sign in success")
//            mDriveClient = Drive.getDriveClient(applicationContext, googleSignInAccount)
//            mDriveResourceClient = Drive.getDriveResourceClient(applicationContext, googleSignInAccount)
//            startActivityForResult(
//                    Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CODE_CAPTURE_IMAGE)
        }.addOnFailureListener { e -> showToast("gagal amarga ${e.localizedMessage}") }
    }
}