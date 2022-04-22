package com.geco.challangech5.fragment.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.utils.widget.MockView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geco.challangech5.API_KEY.Companion.apiKey
import com.geco.challangech5.HomeAdapter
import com.geco.challangech5.R
import com.geco.challangech5.User
import com.geco.challangech5.databinding.FragmentHomeBinding
import com.geco.challangech5.databinding.FragmentRegisterBinding
import com.geco.challangech5.model.Result
import com.geco.challangech5.network.ApiClient
import com.geco.challangech5.viewmodel.MovieViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel : MovieViewModel by viewModels()
        val languange = "en-US"
        val page = 1

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

        val username = sharedPreferences.getString("user", "defaultUser")
        binding.tvWelcome.text = "Selamat Datang, $username"

        viewModel.getMovie(apiKey, languange, page).observe(viewLifecycleOwner){
            showData(it)
            binding.progressBar.visibility = View.GONE
        }
//        fetchAllData("$apiKey","$languange","$page")
        binding.btnProfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_updateProfilFragment)
        }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<User>("user")
            ?.observe(viewLifecycleOwner) { (userName) ->
                binding.tvWelcome.text = "Selamat Datang, $userName"
            }




    }
//    private fun fetchAllData(apiKey: String, language: String, pageNumber: Int){
//        ApiClient.instance.getPopularMovies(apiKey, language, pageNumber)
//                .enqueue(object: Callback<List<Result>>{
//                    override fun onResponse(
//                        call: Call<List<Result>>,
//                        response: Response<List<Result>>
//                    ) {
//                        val body = response.body()
//                        val code = response.code()
//                        if(code == 200){
//                            showData(body)
//                            binding.progressBar.visibility = View.GONE
//                        }
//                    }
//
//                    override fun onFailure(call: Call<List<Result>>, t: Throwable) {
//                        binding.progressBar.visibility = View.GONE
//                    }
//
//                })
//        }
//    }

    private fun showData(data: List<Result>?){
        val adapter = HomeAdapter(object : HomeAdapter.OnClickListener{
            override fun onClickItem(data: Result) {
                val mBundle = Bundle()
                mBundle.putString("title",data.title)
                mBundle.putString("realese",data.releaseDate)
                mBundle.putString("overview",data.overview)
                findNavController().navigate(R.id.action_homeFragment_to_itemDetailFragment)
            }
        })

        adapter.submitData(data)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.adapter = adapter
    }

}