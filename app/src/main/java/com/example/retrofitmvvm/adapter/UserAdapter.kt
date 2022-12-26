package com.example.retrofitmvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitmvvm.databinding.SmapleUserBinding
import com.example.retrofitmvvm.listener.UserListener
import com.example.retrofitmvvm.models.Users

class UserAdapter(
    private val list: List<Users>,
    private val listener: UserListener) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(val sampleUserBinding: SmapleUserBinding) :
        RecyclerView.ViewHolder(sampleUserBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = SmapleUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.sampleUserBinding.user = list[position]
        holder.sampleUserBinding.listener = listener
        holder.sampleUserBinding.executePendingBindings()
    }

    override fun getItemCount(): Int = list.size
}