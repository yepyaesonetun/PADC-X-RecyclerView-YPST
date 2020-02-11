package com.padcmyanmar.padcx.padc_x_recyclerview_ypst.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.R
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.data.vos.NewsVO
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.delegates.NewsItemDelegate
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewholder.BaseNewsViewHolder
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewholder.BaseViewHolder
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewholder.NewsSmallViewHolder
import com.padcmyanmar.padcx.padc_x_recyclerview_ypst.veiws.viewholder.NewsViewHolder

/**
 * Created by Ye Pyae Sone Tun
 * on 2020-01-18.
 */

class NewsListAdapter(delegate: NewsItemDelegate) : BaseRecyclerAdapter<BaseNewsViewHolder,NewsVO>(){

    val mDelegate: NewsItemDelegate = delegate
    val VIEW_TYPE_LARGE = 2244
    val VIEW_TYPE_SMALL = 1122

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseNewsViewHolder {

        when (viewType) {
            VIEW_TYPE_LARGE -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
                return NewsViewHolder(view, mDelegate)
            }
            VIEW_TYPE_SMALL -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_news_smalll, parent, false)
                return NewsSmallViewHolder(view, mDelegate)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
                return NewsViewHolder(view, mDelegate)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        when {
            position % 3 == 0 -> {
                return VIEW_TYPE_LARGE
            }
            else -> {
                return VIEW_TYPE_SMALL
            }
        }
    }
}