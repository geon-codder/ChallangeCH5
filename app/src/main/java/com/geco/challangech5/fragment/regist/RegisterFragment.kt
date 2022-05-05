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
import com.geco.challangech5.database.User
import com.geco.challangech5.database.UserDao
import com.geco.challangech5.database.UserDatabase
import com.geco.challangech5.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private var userDao: UserDao? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences : SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        userDao = Room.databaseBuilder(requireContext(), UserDatabase::class.java, "User.db")
            .allowMainThreadQueries()
            .build().userDao()

        binding.btnRegist.setOnClickListener {
            val username: String = binding.etUsernameRegist.text.toString()
            val pass: String = binding.etPassRegist.text.toString()
            val repass: String = binding.etRePassRegist.text.toString()

            if (username.isNullOrEmpty() || pass.isNullOrEmpty() || repass.isNullOrEmpty()){
                Toast.makeText(activity,"Isi data yang masih kosong", Toast.LENGTH_SHORT).show()
            }else{
//                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                if(pass == repass){
//                    editor.putString("user", username)
//                    editor.putString("pass", pass)
//                    editor.apply()
                    val user = User(
                        null,
                        "$username",
                        "$pass",
                        "",
                        "",
                        ""
                    )
                    userDao!!.insert(user)
                    Toast.makeText(activity,"Registrasi Berhasil", Toast.LENGTH_SHORT).show()
                    it.findNavController().navigateUp()
                }else{
                    Toast.makeText(activity,"Password tidak cocok", Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }

}
