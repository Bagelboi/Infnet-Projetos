package com.danielgomeslipkin.smp.at

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.danielgomeslipkin.smp.at.data.FireAuth
import com.danielgomeslipkin.smp.at.data.Utils
import com.danielgomeslipkin.smp.at.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.ZoneId
import java.time.chrono.ChronoPeriod
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField
import java.time.temporal.ChronoUnit

/*
SHIT TO DO
-Fazer layout dual Register e Login (mudar texts de butoes, texts, etc)
-Finalizar sistema de login
 */

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var fbAuth : FireAuth
    private var isRegistering = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        setupFirebase()
        setupListeners()
        setupRegister()
    }

    private fun setupFirebase() {
        fbAuth = FireAuth(FirebaseAuth.getInstance(), this)
    }

    private fun setupListeners() {
        binding.btnLogin.setOnClickListener {
            val email = binding.itxtEmail.text.toString()
            val pass = binding.itxtSenha.text.toString()

            if (validateEmail(email)) {
                if (validatePassword(pass))
                    fbAuth.signIn(email, pass,
                        { startMainActivity() },
                        { makeToast("Login falhou") }
                    )
                else
                    makeToast("Senha curta demais!")
            } else
                makeToast("Formato do email errado")
        }

        binding.btnReg.setOnClickListener {
            isRegistering = true
            setupRegister()
        }

        binding.btnRegConfirm.setOnClickListener {
            isRegistering = false
            setupRegister()

            val email = binding.itxtEmail.text.toString()
            val pass = binding.itxtSenha.text.toString()

            if (validateEmail(email)) {
                if (validatePassword(pass)) {
                    fbAuth.userExists(email, { task ->
                        val methods = task.getResult().signInMethods
                        if (methods != null && methods.isEmpty()) {
                            fbAuth.signUp(email, pass, {
                                fbAuth.sendEmailVerification(email, {
                                    makeToast("Confirme seu email! (Verifique Spam)") //HADOUKEN!!!!
                                }, {})
                            },
                                {})
                        } else {
                            makeToast("Ja existe uma conta com esse email!")
                        }
                    }, {})
                } else {
                    makeToast("A senha precisa ser 8-20 caracteres!")
                }
            } else {
                makeToast("Formato de email errado!")
            }
        }

        binding.btnForgetPass.setOnClickListener {
            val email = binding.itxtEmail.text.toString()

            if (validateEmail(email)) {
                fbAuth.userExists(email, { task ->
                    val methods = task.getResult().signInMethods
                    if (methods != null && methods.isNotEmpty()) {
                        fbAuth.sendPasswordReset(
                            email,
                            { makeToast("Resete sua senha pelo email! (Verifique Spam)") },
                            {})
                    }
                }, {})
            } else {
                makeToast("Formato de email errado!")
            }
        }
    }

    private fun setupListenersDebug() {
        binding.btnLogin.setOnClickListener {
            startMainActivity()
        }
    }

    private fun makeToast(msg:String) {
        Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
    }

    private fun startMainActivity() {
        val intent = Intent(this, NoteList::class.java)
        val curUser = fbAuth.auth.currentUser
        if (curUser != null && curUser.isEmailVerified ) {
            startActivity(intent)
        } else
            makeToast("Seu email ainda nao foi verificado!")

    }

    private fun setupRegister() {
        Utils.makeViewListVisibility( listOf(
            binding.btnLogin, binding.btnReg, binding.btnForgetPass
        ), !isRegistering )
        Utils.makeViewListVisibility( listOf(
            binding.btnRegConfirm
        ), isRegistering )
    }

    private fun validatePassword(pass:String) : Boolean = (pass.length >= 8 && pass.length <= 20)

    private fun validateEmail(email:String) : Boolean = (email.length >= 6 && email.contains("@") && email.contains("."))
}