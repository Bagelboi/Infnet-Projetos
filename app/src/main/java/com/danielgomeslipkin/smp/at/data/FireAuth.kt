package com.danielgomeslipkin.smp.at.data

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.SignInMethodQueryResult

class FireAuth(var auth:FirebaseAuth, var activity:Activity) {

        fun isSignedIn() : Boolean {
            val curuser = auth.currentUser

            if (curuser != null)
                return true
            return false
        }

        fun signIn(email:String, pass:String, doSuccess : () -> Unit, doFail : () -> Unit) {
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful)
                    doSuccess()
                else
                    doFail()
            }
        }

        fun signUp(email:String, pass:String, doSuccess : () -> Unit, doFail : () -> Unit) {
            auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful)
                    doSuccess()
                else
                    doFail()
                }
            }

        fun sendEmailVerification(email:String, doSuccess : () -> Unit, doFail : () -> Unit) {
            val curUser = auth.currentUser
            if (curUser != null) {
                curUser.sendEmailVerification().addOnCompleteListener(activity) { task ->
                    if (task.isSuccessful)
                        doSuccess()
                    else
                        doFail()
                }
            }
        }

        fun userExists(email:String, doSuccess : (task:Task<SignInMethodQueryResult>) -> Unit, doFail : () -> Unit) {
            auth.fetchSignInMethodsForEmail(email).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful)
                    doSuccess(task)
                else
                    doFail()
            }
        }

        fun sendPasswordReset(email:String, doSuccess : () -> Unit, doFail : () -> Unit) {
            auth.sendPasswordResetEmail(email).addOnCompleteListener(activity) { task ->
                if (task.isSuccessful)
                    doSuccess()
                else
                    doFail()
            }
        }

        fun signOut() {
            auth.signOut()
        }
}