package com.udacity.asteroidradar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidItemBinding
import com.udacity.asteroidradar.databinding.FragmentMainBinding

class AsteroidListAdapter(val asteroidList: List<Asteroid>): RecyclerView.Adapter<AsteroidListAdapter.AsteroidViewHolder>() {


    class AsteroidViewHolder(val binding: AsteroidItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        val itemView = AsteroidItemBinding.inflate(LayoutInflater.from(parent.context),parent, false
        )
        return AsteroidViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        holder.binding.apply {
            codenameTv.text = asteroidList[position].codename
            approachDateTv.text = asteroidList[position].closeApproachDate
            if(asteroidList[position].isPotentiallyHazardous){
                this.hazardImage.setImageResource(R.drawable.ic_status_potentially_hazardous)
            }
            else{
                this.hazardImage.setImageResource(R.drawable.ic_status_normal)
            }
        }


    }

    override fun getItemCount(): Int = asteroidList.size
}
