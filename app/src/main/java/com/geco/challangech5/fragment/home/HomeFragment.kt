package com.geco.challangech5.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.geco.challangech5.repository.UserRepository
import com.geco.challangech5.viewmodel.MovieViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: UserViewModel
    private lateinit var pref: CounterDataStoreManager
    private lateinit var userRepository: UserRepository


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
        viewModel = ViewModelProvider(this, ViewModelFactory(userRepository))[UserViewModel::class.java]


        setObserve()

        binding.btnProfil.setOnClickListener {
            val actionToUpdateProfilFragment = HomeFragmentDirections.actionHomeFragmentToUpdateProfilFragment()
            view.findNavController().navigate(actionToUpdateProfilFragment)
        }

        val viewModel : MovieViewModel by viewModels()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)

        viewModel.getMovieData {
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

    private fun setObserve(){
        viewModel.apply {
            getUsername().observe(requireActivity()){
                binding.tvWelcome.text = "Selamat Datang $it"
            }
        }
    }

    override fun onDestroy(){
        super.onDestroy()
        _binding = null
    }

}