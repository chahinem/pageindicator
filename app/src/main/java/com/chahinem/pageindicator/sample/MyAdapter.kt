package com.chahinem.pageindicator.sample

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.chahinem.pageindicator.sample.MyAdapter.MyViewHolder
import com.squareup.picasso.Picasso

class MyAdapter(private val picasso: Picasso) : androidx.recyclerview.widget.RecyclerView.Adapter<MyViewHolder>() {

  private val items: MutableList<MyItem> = mutableListOf()

  override fun getItemCount() = items.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
      LayoutInflater
          .from(parent.context)
          .inflate(R.layout.item_card, parent, false))

  override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
    holder.bind(picasso, items[holder.adapterPosition])
  }

  fun swapData(data: Iterable<MyItem>?) {
    items.clear()
    data?.let { items.addAll(data) }
    notifyDataSetChanged()
  }

  class MyViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    private val title: TextView = itemView.findViewById(R.id.title)
    private val caption: TextView = itemView.findViewById(R.id.caption)
    private val image: ImageView = itemView.findViewById(R.id.image)

    fun bind(picasso: Picasso, item: MyItem) {
      picasso
          .load(item.image)
          .placeholder(R.color.colorPrimaryDark)
          .fit()
          .centerCrop()
          .into(image)
      title.text = item.title
      caption.text = item.caption
    }
  }

  class MyItem(val title: String, val caption: String, val image: String)
}