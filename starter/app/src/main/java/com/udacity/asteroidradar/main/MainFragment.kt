package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.AsteroidListAdapter
import com.udacity.asteroidradar.AsteroidListener
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }
    lateinit var adapter: AsteroidListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

      adapter = AsteroidListAdapter(AsteroidListener{asteroid->
            viewModel.onAsteroidClicked(asteroid)

        })
        viewModel.navigateToDetails.observe(viewLifecycleOwner, Observer {asteroid->
            asteroid?.let {
                this.findNavController().navigate(MainFragmentDirections.actionShowDetail(asteroid))
                viewModel.doneClick()
            } })

        binding.asteroidRecycler.adapter = adapter

        return binding.root
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.show_all_menu -> viewModel.toSavedAsteroids()
            R.id.show_rent_menu -> viewModel.todayAsteroids()
            R.id.show_buy_menu -> viewModel.toWeekAsteroids()
        }
        setupObserver()
        return true
    }

    private fun setupObserver() {
        viewModel.response.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it) })

    }
}
