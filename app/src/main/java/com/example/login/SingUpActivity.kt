package com.example.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.databinding.ActivitySingUpBinding
import com.google.firebase.auth.FirebaseAuth

class SingUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySingUpBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.btnSingUp.setOnClickListener {
            val email = binding.teEmail.text.toString()
            val pass = binding.tePass.text.toString()
            val pass2 = binding.tePass2.text.toString()
            if (email.isEmpty() or pass.isEmpty() or pass2.isEmpty()){
                Toast.makeText(this, "Verifica la entrada", Toast.LENGTH_SHORT).show()
            }else{
                if (pass == pass2) {
                    firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            //rediregir a main activity
                            val intent = Intent(this, SingInActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this, "Las contraseñas no coinciden ", Toast.LENGTH_SHORT).show()
                }

            }
        }


        binding.tvGoSingIn.setOnClickListener {
            val intent = Intent(this,SingInActivity::class.java)
            startActivity(intent)
        }

    }
}