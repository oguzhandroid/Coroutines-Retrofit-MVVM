package com.ogocer.coroutinesretrofitmvvm.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ogocer.coroutinesretrofitmvvm.R
import com.ogocer.coroutinesretrofitmvvm.data.model.User

class MainAdapter(private val users : ArrayList<User>): RecyclerView.Adapter<MainAdapter.DataViewHolder>() {
    class DataViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        fun bind(user: User){
            itemView.apply {
                this.findViewById<TextView>(R.id.tvName).text = user.name
                this.findViewById<TextView>(R.id.tvEmail).text = user.email
                Glide.with(this.findViewById<ImageView>(R.id.imgAvatar).context)
                    .load(user.avatar)
                    .into(this.findViewById<ImageView>(R.id.imgAvatar))
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])

    }

    fun addUsers(users: List<User>) {
        this.users.apply {
            clear()
            addAll(users)
        }
    }
}