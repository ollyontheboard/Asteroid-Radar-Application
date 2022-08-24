package com.udacity.asteroidradar.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.AsteroidListAdapter
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        setHasOptionsMenu(true)

        val adapter = AsteroidListAdapter()



      binding.asteroidRecycler.adapter = adapter
        viewModel.response.observe(viewLifecycleOwner, Observer {
            adapter.asteroidList = it
        })

        return binding.root
    }

    private fun createList(): List<Asteroid>{
        val asteroidList :List<Asteroid> =

            listOf(Asteroid(codename = "56478", closeApproachDate = "2024-08-25", isPotentiallyHazardous = false),
            Asteroid(codename = "56478", closeApproachDate = "2025-06-21", isPotentiallyHazardous = true),
            Asteroid(codename = "56478", closeApproachDate = "2024-08-25", isPotentiallyHazardous = false)
        )
        return asteroidList

    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }
}
