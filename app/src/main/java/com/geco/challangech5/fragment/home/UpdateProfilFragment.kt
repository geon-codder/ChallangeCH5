package com.geco.challangech5.fragment.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.geco.challangech5.databinding.FragmentUpdateProfilBinding
import com.geco.challangech5.datastore.CounterDataStoreManager
import com.geco.challangech5.datastore.UserViewModel
import com.geco.challangech5.datastore.ViewModelFactory

class UpdateProfilFragment : Fragment() {
    private var _binding: FragmentUpdateProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: CounterDataStoreManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateProfilBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = CounterDataStoreManager(requireActivity())
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[UserViewModel::class.java]

        val sharedPreferences : SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        binding.btnUpdate.setOnClickListener {
            val userName = binding.etUsernameUpdate.text.toString()
//            val fulname = binding.etNamaLengkapUpdate.text.toString()
//            val tglLahir = binding.etTanggalLahirUpdate.text.toString()
//            val address = binding.etAlamatUpdate.text.toString()
            viewModel.setUsername(userName)
            val actionToHomeFragment = UpdateProfilFragmentDirections.actionUpdateProfilFragmentToHomeFragment()
                view.findNavController().navigate(actionToHomeFragment)

        }

        binding.btnLogout.setOnClickListener{
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("login", 0)
            editor.apply()
            val actionToLoginFragment = UpdateProfilFragmentDirections.actionUpdateProfilFragmentToLoginFragment()
            view.findNavController().navigate(actionToLoginFragment)
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }

}