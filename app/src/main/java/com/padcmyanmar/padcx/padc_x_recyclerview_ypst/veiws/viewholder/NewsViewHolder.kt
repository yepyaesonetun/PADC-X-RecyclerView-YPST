package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-01-18.
 */

class NewsViewHolder(itemView: View, delegate: NewsItemDelegate) :
  BaseNewsViewHolder(itemView) {

    init {
        itemView.setOnClickListener { delegate.onTapNewsItem() }
    }

}