package com.geco.challangech5.fragment.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import com.geco.challangech5.R
import com.geco.challangech5.database.User
import com.geco.challangech5.database.UserDao
import com.geco.challangech5.database.UserDatabase
import com.geco.challangech5.databinding.FragmentUpdateProfilBinding

class UpdateProfilFragment : Fragment() {
    private var _binding: FragmentUpdateProfilBinding? = null
    private val binding get() = _binding!!
    private var userDao: UserDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateProfilBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userDao = Room.databaseBuilder(requireContext(), UserDatabase::class.java, "User.db")
            .allowMainThreadQueries()
            .build().userDao()

        binding.btnUpdate.setOnClickListener {
            val userName = binding.etUsernameUpdate.text.toString()
            val fulname = binding.etNamaLengkapUpdate.text.toString()
            val tglLahir = binding.etTanggalLahirUpdate.text.toString()
            val address = binding.etAlamatUpdate.text.toString()
            val user = User(null,"$userName","","$fulname","$tglLahir","$address")
            userDao!!.update(user)
//            findNavController().previousBackStackEntry?.savedStateHandle?.set("user", userName)
            findNavController().navigateUp()
        }

        binding.btnLogout.setOnClickListener{
            val sharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("login", 1)
            editor.apply()
            findNavController().navigate(R.id.action_updateProfilFragment_to_loginFragment)
        }
    }

}