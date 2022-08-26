package com.udacity.asteroidradar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidItemBinding

class AsteroidListAdapter(val clickListener : AsteroidListener): ListAdapter<Asteroid, AsteroidListAdapter.AsteroidViewHolder>(AsteroidListDiffCallBack()) {


    class AsteroidViewHolder private constructor(val binding: AsteroidItemBinding): RecyclerView.ViewHolder(binding.root){
         fun bind(
            item: Asteroid,
            clickListener: AsteroidListener
        ) {
            binding.apply {
                asteroid = item
                clicklistener = clickListener
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
        val item = getItem(position)
        holder.bind(item,clickListener)



    }
}

class AsteroidListDiffCallBack : DiffUtil.ItemCallback<Asteroid>() {
    override fun areItemsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
       return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: Asteroid, newItem: Asteroid): Boolean {
       return oldItem==newItem
    }

}
class AsteroidListener(val clickListener: (asteroid: Asteroid)-> Unit){
    fun onClick(asteroid: Asteroid)= clickListener(asteroid)
}
