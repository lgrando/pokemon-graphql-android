package com.grando.pokemonapp.presentation.views.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.grando.pokemonapp.BR

@SuppressLint("NotifyDataSetChanged")
open class GenericAdapter<T>(
    @LayoutRes val layoutId: Int,
    private val listener: ((T) -> Unit)? = null
) : RecyclerView.Adapter<GenericAdapter.GenericViewHolder<T>>() {

    protected val items = mutableListOf<T>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenericViewHolder<T> {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            layoutId, parent, false
        )
        return GenericViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: GenericViewHolder<T>, position: Int) =
        holder.bind(items[position])

    fun setupItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class GenericViewHolder<T>(
        private val binding: ViewDataBinding,
        private val listener: ((T) -> Unit)? = null
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.setVariable(BR.item, item)
            itemView.setOnClickListener { listener?.invoke(item) }
            binding.executePendingBindings()
        }
    }
}