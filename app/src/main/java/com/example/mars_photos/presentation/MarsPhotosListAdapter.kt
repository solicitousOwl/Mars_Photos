package com.example.mars_photos.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.example.mars_photos.R
import com.example.mars_photos.data.MarsPhotosArrayDto
import com.example.mars_photos.databinding.MarsPhotoElementBinding

class MarsPhotosListAdapter(
    private val onClick: (MarsPhotosArrayDto) -> Unit
) : ListAdapter<MarsPhotosArrayDto, MarsPhotosHolder>(DifUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarsPhotosHolder {
        return MarsPhotosHolder(
            MarsPhotoElementBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MarsPhotosHolder, position: Int) {
        val item = getItem(position)
        with(holder.binding) {
            dateTextView.text = item?.earth_date ?: ""
            solTextView.text = item?.sol.toString()
            roverTextView.text = item?.rover?.name ?: ""
            cameraTextView.text = item?.camera?.name ?: ""
            Log.d("aslan555", "onBindViewHolder: ${item.img_src}")
            val nasaLink=if (item?.img_src!!.contains("http://mars.jpl.nasa.gov")){
                val temp=item.img_src.removePrefix("http://mars.jpl.nasa.gov")
                "https://mars.nasa.gov$temp"
            }else{
                item.img_src
            }
            Log.d("aslan555", "$nasaLink")
            item?.let {
                Glide
                    .with(imageView.context)
                    .load(nasaLink)
                    .into(imageView)
            }
        }
        holder.binding.root.setOnClickListener {
            onClick(item)
        }
    }

}

class DifUtilCallBack : DiffUtil.ItemCallback<MarsPhotosArrayDto>() {
    override fun areItemsTheSame(
        oldItem: MarsPhotosArrayDto,
        newItem: MarsPhotosArrayDto
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: MarsPhotosArrayDto,
        newItem: MarsPhotosArrayDto
    ): Boolean {
        return oldItem == newItem
    }

}