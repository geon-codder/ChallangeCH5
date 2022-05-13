package com.geco.challangech5.fragment.regist

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.geco.challangech5.R
import com.geco.challangech5.databinding.FragmentLoginBinding
import com.geco.challangech5.datastore.CounterDataStoreManager
import com.geco.challangech5.datastore.UserViewModel
import com.geco.challangech5.datastore.ViewModelFactory


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: CounterDataStoreManager

    companion object{
        var prefUsername = "admin"
        var prefPass = "admin"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = CounterDataStoreManager(requireActivity())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        val sharedPreferences : SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val loginStatus = sharedPreferences.getInt("login", 0)
        if (loginStatus == 1){
            view.findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }

        binding.btnLogin.setOnClickListener{
            val username = "[${binding.etUsernameLogin.text}]"
            val password = "[${binding.etPassLogin.text}]"
            if(username.isEmpty() || password.isEmpty()){
                Toast.makeText(activity,"Mohon Masukkan Email atau Password", Toast.LENGTH_SHORT).show()
            }else{
                loginAuth(username,password)
//                setLogin()
                val editor: SharedPreferences.Editor = sharedPreferences.edit()
                editor.putInt("login", 1)
                editor.apply()
            }
        }

        binding.tvRegist.setOnClickListener{
            val actionToRegisterFragment = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            view.findNavController().navigate(actionToRegisterFragment)
        }
    }

    private fun loginAuth(username: String, password: String){
        viewModel.getUsername().observe(requireActivity()){uname ->
            prefUsername = uname.toString()
            viewModel.getPassword().observe(requireActivity()){upass->
                prefPass = upass.toString()
                if (prefUsername == username && prefPass == password){
                    Toast.makeText(activity, "Login Berhasil", Toast.LENGTH_SHORT).show()
                    val actionToHomeFragment = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
                    findNavController().navigate(actionToHomeFragment)
                }else{
                    Toast.makeText(activity, "Login Gagal, akun tidak ditemukan", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }
}
