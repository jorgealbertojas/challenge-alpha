package com.example.jorge.desafiohotelurbano.ui.list

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jorge.desafiohotelurbano.R
import com.example.jorge.desafiohotelurbano.models.Results
import com.example.jorge.desafiohotelurbano.models.Hotels

class ListAdapter(private val context: Context, private val list: MutableList<Hotels>,
                  fragment: Fragment): RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var results = list[position]
        holder!!.title!!.setText(results.name)
        holder!!.mainTitle!!.visibility = View.INVISIBLE
        holder.body!!.setText(results.description)
        holder.layout!!.setOnClickListener {
            listener.itemDetail(results.sku.toString()!!)
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
        val title = itemView.findViewById<TextView>(R.id.item_title)
        val body = itemView.findViewById<TextView>(R.id.item_body)
        val mainTitle = itemView.findViewById<LinearLayout>(R.id.item_layout_title)

        fun bind(item: Results) {
            // title = item.post
            // body etc.
        }
    }

    interface onItemClickListener {
        fun itemRemoveClick(post: Results)
        fun itemDetail(postId : String)
    }
}