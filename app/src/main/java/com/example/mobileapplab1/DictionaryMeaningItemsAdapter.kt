package com.example.mobileapplab1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileapplab1.databinding.DictionaryMeaningItemContainerBinding

class DictionaryMeaningItemsAdapter(private val dictionaryMeaningItems: List<DictionaryMeaningItem>) :
    RecyclerView.Adapter<DictionaryMeaningItemsAdapter.DictionaryMeaningItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryMeaningItemViewHolder {
        return DictionaryMeaningItemViewHolder(DictionaryMeaningItemContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: DictionaryMeaningItemViewHolder, position: Int) {
        holder.bind(dictionaryMeaningItems[position])
    }

    override fun getItemCount(): Int {
        return dictionaryMeaningItems.size
    }

    inner class DictionaryMeaningItemViewHolder(binding: DictionaryMeaningItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val textMeaning = binding.textMeaning
        private val textExample = binding.textExample

        @SuppressLint("SetTextI18n")
        fun bind(dictionaryMeaningItem: DictionaryMeaningItem) {
            textMeaning.text = dictionaryMeaningItem.meaning
            textExample.text = "Example: " + dictionaryMeaningItem.meaningExample
        }
    }
}