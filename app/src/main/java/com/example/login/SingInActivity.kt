package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager.OnActivityStopListener
import android.widget.SearchView.OnCloseListener
import android.widget.Toast
import com.example.login.databinding.ActivitySingInBinding
import com.google.firebase.auth.FirebaseAuth

class SingInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySingInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSingUp.setOnClickListener {
            val email = binding.teEmail.text.toString()
            val pass = binding.tePass.text.toString()
            if (email.isEmpty() or pass.isEmpty()){
                Toast.makeText(this, "Verifica la entrada", Toast.LENGTH_SHORT).show()
            }else{
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener{
                    if (it.isSuccessful){
                        //rediregir a main activity
                        val intent = Intent(this,MainActivity::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }
        binding.tvGoSingUp.setOnClickListener {
            val intent = Intent(this,SingUpActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.signOut()
    }
}