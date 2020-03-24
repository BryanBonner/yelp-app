package com.bryanbonner.yelpapp.app.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bryanbonner.yelpapp.app.R
import com.bryanbonner.yelpapp.app.data.Business
import com.bryanbonner.yelpapp.app.service.NetworkState

class BusinessesAdapter : PagedListAdapter<Business, BusinessesViewHolder>(businessDiffCallback) {

    //TODO: Network Handling
    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessesViewHolder {
        return BusinessesViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.list_item_business,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BusinessesViewHolder, position: Int) {
        val business = getItem(position)
        if (business != null) {
            holder.setData(business)
        } else {
            holder.clear()
        }
    }

    companion object {
        private val businessDiffCallback = object : DiffUtil.ItemCallback<Business>() {
            override fun areItemsTheSame(oldItem: Business, newItem: Business): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Business, newItem: Business): Boolean {
                return oldItem == newItem
            }
        }
    }

}
