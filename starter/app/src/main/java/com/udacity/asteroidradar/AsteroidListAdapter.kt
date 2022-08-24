package com.udacity.asteroidradar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

class AsteroidListAdapter: RecyclerView.Adapter<AsteroidListAdapter.AsteroidViewHolder>() {
    var asteroidList = listOf<Asteroid>()

    class AsteroidViewHolder private constructor(val binding: AsteroidItemBinding): RecyclerView.ViewHolder(binding.root){
         fun bind(
            item: Asteroid
        ) {
            binding.apply {
                codenameTv.text = item.codename
                approachDateTv.text = item.closeApproachDate
                bindAsteroidStatusImage(this.hazardImage, item.isPotentiallyHazardous)
            }
        }
        //encapsulate view inflation in companion object so it can only be called with viewholder class
        companion object {
             fun from(parent: ViewGroup): AsteroidViewHolder {
                val itemView = AsteroidItemBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
                return AsteroidViewHolder(itemView)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
        return AsteroidViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
        val item = asteroidList[position]
        holder.bind(item)



    }



    override fun getItemCount(): Int = asteroidList.size


}
