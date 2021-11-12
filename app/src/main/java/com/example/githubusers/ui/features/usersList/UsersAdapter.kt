package com.example.githubusers.ui.features.usersList

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.example.githubusers.common.loadImage
import com.example.githubusers.data.model.UsersResponseItem
import com.example.githubusers.databinding.ItemUserBinding


class UsersAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<UsersResponseItem>() {

        override fun areItemsTheSame(oldItem: UsersResponseItem, newItem: UsersResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UsersResponseItem, newItem: UsersResponseItem): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return UsersViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false),
            interaction
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UsersViewHolder -> {
                holder.bind(differ.currentList[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    class UsersViewHolder ( val binding: ItemUserBinding, private val interaction: Interaction?) :
        RecyclerView.ViewHolder(binding.root) {



        fun bind(item: UsersResponseItem) = with(itemView) {
            itemView.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)
            }
            binding.userImage.loadImage(item.avatar_url)
            binding.userName.text=item.login


        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: UsersResponseItem)
    }
}

