package com.globalgatway.cuisine512.mvvmkotlincorotines.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.globalgatway.cuisine512.mvvmkotlincorotines.R
import com.globalgatway.cuisine512.mvvmkotlincorotines.data.model.User
import com.globalgatway.cuisine512.mvvmkotlincorotines.databinding.ItemLayoutBinding

class MainAdapter(private val users: ArrayList<User>) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(private val itemLayoutBinding: ItemLayoutBinding) : RecyclerView.ViewHolder(itemLayoutBinding.root) {

        fun bind(user: User) {
            itemView.apply {
                itemLayoutBinding.textViewUserName.text = user.name
                itemLayoutBinding.textViewUserEmail.text = user.email
                Glide.with(itemLayoutBinding.imageViewAvatar.context)
                    .load(user.avatar)
                    .into(itemLayoutBinding.imageViewAvatar)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

        val itemBinding = ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }
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