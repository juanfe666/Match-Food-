package com.Unibague.mathfood.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.Unibague.mathfood.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //Creamos nuestra variable de autenticación firebase
    private lateinit var auth: FirebaseAuth



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root


        //...................................

// Initialize Firebase Auth
        auth = Firebase.auth


// -------------------------
// Lógica del Login
// -------------------------

        binding.buttonLogin.setOnClickListener {

            val nombre : String = binding.inputTextNombre.text.toString()
            val password : String = binding.inputTextPassword.text.toString()

            auth.signInWithEmailAndPassword(nombre, password).addOnCompleteListener {
                    task->
                if(task.isSuccessful)
                {
                    val toast = Toast.makeText(context, "¡Datos correctos!", Toast.LENGTH_SHORT)
                    toast.show()
                }
                else
                {
                    val toast = Toast.makeText(context, "Datos son incorrectos! $nombre - $password", Toast.LENGTH_SHORT)
                    toast.show()
                }
            }
        }
        //....................................


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}