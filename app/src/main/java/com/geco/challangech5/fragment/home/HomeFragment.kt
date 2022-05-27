package com.geco.challangech5.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.geco.challangech5.HomeAdapter
import com.geco.challangech5.R
import com.geco.challangech5.databinding.FragmentHomeBinding
import com.geco.challangech5.datastore.CounterDataStoreManager
import com.geco.challangech5.datastore.UserViewModel
import com.geco.challangech5.datastore.ViewModelFactory
import com.geco.challangech5.model.Movie
import com.geco.challangech5.model.MovieResponse
import com.geco.challangech5.network.ApiClient
import com.geco.challangech5.repository.UserRepository
import com.geco.challangech5.viewmodel.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Named

//@AndroidEntryPoint
class HomeFragment : Fragment() {
//    @Inject
//    @Named("ModeOld")
//    lateinit var modeOld: String

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var userViewModel: UserViewModel
    private lateinit var pref: CounterDataStoreManager
    private lateinit var userRepository: UserRepository

//    @Inject
//    @Named("ModeNew")
//    lateinit var modeNew: String

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

        pref = CounterDataStoreManager(requireActivity())
        userRepository = UserRepository(pref)
        userViewModel = ViewModelProvider(this, ViewModelFactory(userRepository))[UserViewModel::class.java]

        setObserve()
//        Toast.makeText(activity, modeOld,Toast.LENGTH_SHORT).show()


//        binding.tvSwitchMode.setOnClickListener{
//            Toast.makeText(activity, modeNew,Toast.LENGTH_SHORT).show()
//        }


        binding.btnProfil.setOnClickListener {
            val actionToUpdateProfilFragment = HomeFragmentDirections.actionHomeFragmentToUpdateProfilFragment()
            view.findNavController().navigate(actionToUpdateProfilFragment)
        }

        val viewModel : MovieViewModel by viewModels()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

//        viewModel.getMovieData {
//            binding.recyclerView.adapter = HomeAdapter(it,object: HomeAdapter.OnClickListener{
//                override fun onClickItem(data: Movie){
//                    val itemDetailFragment = ItemDetailFragment()
//                    val manager: FragmentManager? = fragmentManager
//                    val bundle = Bundle()
//                    bundle.putString("title", data.title)
//                    bundle.putString("release", data.release)
//                    bundle.putString("poster", data.poster)
//                    bundle.putString("overview", data.overview)
//                    itemDetailFragment.arguments = bundle
//
//                    manager?.beginTransaction()?.replace(R.id.navContainer, itemDetailFragment)
//                        ?.addToBackStack(null)?.commit()
//                }
//            })
//            binding.progressBar.visibility = View.GONE
//        }
        getMovieData {
            binding.recyclerView.adapter = HomeAdapter(it,object: HomeAdapter.OnClickListener{
                override fun onClickItem(data: Movie){
                    val itemDetailFragment = ItemDetailFragment()
                    val manager: FragmentManager? = fragmentManager
                    val bundle = Bundle()
                    bundle.putString("title", data.title)
                    bundle.putString("release", data.release)
                    bundle.putString("poster", data.poster)
                    bundle.putString("overview", data.overview)
                    itemDetailFragment.arguments = bundle

                    manager?.beginTransaction()?.replace(R.id.navContainer, itemDetailFragment)
                        ?.addToBackStack(null)?.commit()
                }
            })
            binding.progressBar.visibility = View.GONE
        }
    }

    fun getMovieData(callback: (List<Movie>) -> Unit) {
        ApiClient.getInstance(requireContext()).getMovie()
            .enqueue(object: Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
            }
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                return when {
                    response.isSuccessful -> {
                        callback(response.body()!!.movies)
                    }
                    else -> {}
                }

            }
        })
    }

    private fun setObserve(){
        userViewModel.apply {
            getUsername().observe(requireActivity()){
                binding.tvWelcome.text = "Selamat Datang $it"
            }
        }
//        Toast.makeText(activity, modeOld,Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }

}