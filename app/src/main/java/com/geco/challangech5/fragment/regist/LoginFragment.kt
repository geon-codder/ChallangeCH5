package com.geco.challangech5.fragment.regist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.room.Room
import com.geco.challangech5.R
import com.geco.challangech5.database.UserDao
import com.geco.challangech5.database.UserDatabase
import com.geco.challangech5.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences : SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val loginStatus = sharedPreferences.getInt("login", 1)
        if (loginStatus == 0){
            view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        val db: UserDao
        val dataBase: UserDatabase

        dataBase = Room.databaseBuilder(requireContext(), UserDatabase::class.java, "User.db")
            .allowMainThreadQueries()
            .build()
        db = dataBase.userDao()

        btnLogin.setOnClickListener {
//            val usernameLogin = binding.etUsernameLogin.text.toString()
//            val passwordLogin = binding.etPassLogin.text.toString()
            val username: String = binding.etUsernameLogin.text.toString().trim()
            val password: String = binding.etPassLogin.text.toString().trim()


            if(username.isNullOrEmpty() || password.isNullOrEmpty()){
                Toast.makeText(activity,"Mohon Masukkan Email atau Password", Toast.LENGTH_SHORT).show()
            }else{
                val user = db.getUser(username, password)
                if (user != null) {
                    val editor: SharedPreferences.Editor = sharedPreferences.edit()
                    editor.putInt("login", 0)
                    editor.apply()
                    view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }else{
                    Toast.makeText(activity, "Data tidak ditemukan",Toast.LENGTH_SHORT).show()
                }
            }

        }
        tvRegist.setOnClickListener{
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}
