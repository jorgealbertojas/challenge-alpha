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

        val res = context?.getResources()
        holder.price!!.setText(res.getString(R.string.string_money) + results.price.current_price?.toString())
        holder.city!!.setText(results.address?.city!!)
        holder.state!!.setText(results.address?.state!!)



        when (results.amenities.size){
            0 -> {
                holder.amenities1!!.setText(res?.getString(R.string.string_amenities))
                holder.amenities2!!.setText("")
                holder.amenities3!!.setText("")
            }
            1 -> {
                holder.amenities1!!.setText(results?.amenities[0]?.name!!)
                holder.amenities2!!.setText("")
                holder.amenities3!!.setText("")
            }
            2 -> {
                holder.amenities1!!.setText(results?.amenities[0]?.name!!)
                holder.amenities2!!.setText(results?.amenities[1]?.name!!)
                holder.amenities3!!.setText("")
            }
            else -> {
                holder.amenities1!!.setText(results?.amenities[0]?.name!!)
                holder.amenities2!!.setText(results?.amenities[1]?.name!!)
                holder.amenities3!!.setText(results?.amenities[2]?.name!!)
            }
        }


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
        var layout = itemView.findViewById<ConstraintLayout>(R.id.cl_item_layout)
        val title = itemView.findViewById<TextView>(R.id.tv_item_description)
        val body = itemView.findViewById<TextView>(R.id.tv_item_body)
        val layoutMainTitle = itemView.findViewById<LinearLayout>(R.id.ll_item_layout_title)
        val mainTitle = itemView.findViewById<TextView>(R.id.tv_item_title)
        val imageHotel = itemView.findViewById<ImageView>(R.id.iv_image_hotel)

        val price = itemView.findViewById<TextView>(R.id.tv_price)
        val city = itemView.findViewById<TextView>(R.id.tv_city)
        val state = itemView.findViewById<TextView>(R.id.tv_state)
        val amenities1 = itemView.findViewById<TextView>(R.id.tv_amenities1)
        val amenities2 = itemView.findViewById<TextView>(R.id.tv_amenities2)
        val amenities3 = itemView.findViewById<TextView>(R.id.tv_amenities3)


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