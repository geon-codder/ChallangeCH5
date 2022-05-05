package com.geco.challangech5.fragment.home

import android.content.Context
import android.content.Intent.getIntent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.geco.challangech5.HomeAdapter
import com.geco.challangech5.R
import com.geco.challangech5.database.User
import com.geco.challangech5.database.UserDao
import com.geco.challangech5.database.UserDatabase
import com.geco.challangech5.databinding.FragmentHomeBinding
import com.geco.challangech5.model.Movie
import com.geco.challangech5.viewmodel.MovieViewModel


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var user: User? = null


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

        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("SP_INFO", Context.MODE_PRIVATE)

//        val username = sharedPreferences.getString("user", "defaultUser")
//        val db: UserDao
//        val dataBase: UserDatabase
//
//        dataBase = Room.databaseBuilder(requireContext(), UserDatabase::class.java, "User.db")
//            .allowMainThreadQueries()
//            .build()
//        db = dataBase.userDao()
//        user = db.getUser()
        val username = user?.userName

        binding.tvWelcome.text = "Selamat Datang, $username"


        binding.btnProfil.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_updateProfilFragment)
        }
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<User>("user")
            ?.observe(viewLifecycleOwner) { (userName) ->
                binding.tvWelcome.text = "Selamat Datang, $userName"
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

}