package com.geco.challangech5.fragment.home

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.geco.challangech5.API_KEY
import com.geco.challangech5.Converter
import com.geco.challangech5.databinding.FragmentUpdateProfilBinding
import com.geco.challangech5.datastore.CounterDataStoreManager
import com.geco.challangech5.datastore.User
import com.geco.challangech5.datastore.UserViewModel
import com.geco.challangech5.datastore.ViewModelFactory
import com.geco.challangech5.repository.UserRepository

class UpdateProfilFragment : Fragment() {
    private var _binding: FragmentUpdateProfilBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: CounterDataStoreManager
    private lateinit var userRepository: UserRepository
    private lateinit var converter: Converter


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
        userRepository = UserRepository(pref)
        viewModel = ViewModelProvider(this, ViewModelFactory(userRepository))[UserViewModel::class.java]



        val sharedPreferences : SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)
        val image = sharedPreferences.getString("imageBitmap", "default")

//        binding.imageView.setImageBitmap(bitmap)
//        if (image != null) {
//            val bitmap: Bitmap = converter.toBitmap(image)
//            binding.imageView.setImageBitmap(bitmap)
//        }

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
        binding.imageView.setOnClickListener{
//            val actionToImageHandle = UpdateProfilFragmentDirections.actionUpdateProfilFragmentToImageHandle()
//            view.findNavController().navigate(actionToImageHandle)
            val actionToImageHandleFragment = UpdateProfilFragmentDirections.actionUpdateProfilFragmentToImageHandleFragment()
            view.findNavController().navigate(actionToImageHandleFragment)
        }

//        setupImage(image)
//        binding.imageView.setImageBitmap(bitmap)

        binding.btnShow.setOnClickListener {

//            val image = API_KEY.image
            if (image == "default"){
                Toast.makeText(activity,"string: $image", Toast.LENGTH_SHORT).show()
            }else {
//                val bitmap = decodeBase64(image)
                converter = Converter()
                val bitmap = converter.toBitmap(image)
                binding.imageView.setImageBitmap(bitmap)
                Toast.makeText(activity,"berhasil", Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun setupImage(image: String?){
//        converter = Converter()
//        bitmap = converter.toBitmap(image)
//    }

    private fun decodeBase64(input: String?): Bitmap {
        val decodeByte = Base64.decode(input, 0)
        return BitmapFactory.decodeByteArray(decodeByte, 0, decodeByte.size)
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }

}