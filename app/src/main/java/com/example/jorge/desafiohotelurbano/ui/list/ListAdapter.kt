package com.example.jorge.desafiohotelurbano.ui.list

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jorge.desafiohotelurbano.R
import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.models.Hotels
import com.squareup.picasso.Picasso

class ListAdapter(private val context: Context, private val list: MutableList<Hotels>,
                  fragment: Fragment): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var results = list[position]
        holder!!.title!!.setText(results.name)
        holder!!.mainTitle.setText(results.mainTitle)
        // Show Main Title when title not null
        holder!!.layoutMainTitle!!.visibility = if (results.mainTitle != null) View.VISIBLE else View.GONE


        val urlHotel = results.gallery[0]?.url
        Picasso.with(context).load(urlHotel).fit().centerCrop().error(R.mipmap.ic_launcher).into(holder!!.imageHotel)
        holder.body!!.setText(results.description)
        holder.layout!!.setOnClickListener {
            listener.itemDetail(results)!!
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false)
        return ListAdapter.ListViewHolder(itemView)
    }


    private val listener: ListAdapter.onItemClickListener

    init {
        this.listener = fragment as ListAdapter.onItemClickListener
    }


    override fun getItemCount(): Int {
        return list.size
    }


    fun removeAt(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var layout = itemView.findViewById<ConstraintLayout>(R.id.item_layout)
        val title = itemView.findViewById<TextView>(R.id.item_description)
        val body = itemView.findViewById<TextView>(R.id.item_body)
        val layoutMainTitle = itemView.findViewById<LinearLayout>(R.id.item_layout_title)
        val mainTitle = itemView.findViewById<TextView>(R.id.item_title)
        val imageHotel = itemView.findViewById<ImageView>(R.id.iv_image_hotel)

        fun bind(item: Results) {
            // title = item.post
            // body etc.
        }
    }

    interface onItemClickListener {
        fun itemRemoveClick(postId: String)
        fun itemDetail(hotels: Hotels)
    }
}