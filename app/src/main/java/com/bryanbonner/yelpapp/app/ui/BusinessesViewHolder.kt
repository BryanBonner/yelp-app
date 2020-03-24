package com.bryanbonner.yelpapp.app.ui

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bryanbonner.yelpapp.app.data.Business
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.list_item_business.view.*

class BusinessesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun clear() = itemView.run {
        image.invalidate()
    }

    fun setData(business: Business) = itemView.run {
        Glide.with(image.context).load(business.imageUrl).into(image)
        title.text = business.name
        rating.text = business.rating.toString()
        if (business.reviews != null && business.reviews!!.isNotEmpty()) {
            review_1.text = business.reviews!![0].text
        }
    }
}