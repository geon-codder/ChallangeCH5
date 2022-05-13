package com.geco.challangech5.fragment.regist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.geco.challangech5.databinding.FragmentRegisterBinding
import com.geco.challangech5.datastore.CounterDataStoreManager
import com.geco.challangech5.datastore.UserViewModel
import com.geco.challangech5.datastore.ViewModelFactory


class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: CounterDataStoreManager


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = CounterDataStoreManager(requireContext())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]


        binding.btnRegist.setOnClickListener {
            val username: String = binding.etUsernameRegist.text.toString()
            val pass: String = binding.etPassRegist.text.toString()
            val repass: String = binding.etRePassRegist.text.toString()

            if (username.isEmpty() || pass.isEmpty() || repass.isEmpty()){
                Toast.makeText(activity,"Isi data yang masih kosong", Toast.LENGTH_SHORT).show()
            }else{

                if(pass == repass){
                    viewModel.setUsername(username)
                    viewModel.setPassword(pass)
                    val actionToLoginFragment = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
                    view.findNavController().navigate(actionToLoginFragment)
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
