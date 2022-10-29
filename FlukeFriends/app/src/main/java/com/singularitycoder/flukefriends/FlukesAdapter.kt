package com.singularitycoder.flukefriends

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.singularitycoder.flukefriends.databinding.ListItemFlukeBinding

class FlukesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var flukeList = listOf<Fluke>()
    private var itemClickListener: (fluke: Fluke, position: Int) -> Unit = { fluke, position -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemBinding = ListItemFlukeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PersonViewHolder).setData(flukeList[position])
    }

    override fun getItemCount(): Int = flukeList.size

    override fun getItemViewType(position: Int): Int = position

    fun setItemClickListener(listener: (fluke: Fluke, position: Int) -> Unit) {
        itemClickListener = listener
    }

    inner class PersonViewHolder(
        private val itemBinding: ListItemFlukeBinding,
    ) : RecyclerView.ViewHolder(itemBinding.root) {
        fun setData(fluke: Fluke) {
            itemBinding.apply {
                tvThoughtDescription.text = fluke.title
                tvExpiryTime.text = "Expires in ${fluke.expiry}"
                tvFlukeCount.text = fluke.count.toString()
                root.setOnClickListener {
                    itemClickListener.invoke(fluke, adapterPosition)
                }
            }
        }
    }
}
