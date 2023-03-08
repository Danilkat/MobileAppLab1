package com.example.mobileapplab1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DictionaryMeaningItemsAdapter(private val dictionaryMeaningItems: List<DictionaryMeaningItem>) :
    RecyclerView.Adapter<DictionaryMeaningItemsAdapter.DictionaryMeaningItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DictionaryMeaningItemViewHolder {
        return DictionaryMeaningItemViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.dictionary_meaning_item_container,
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

    inner class DictionaryMeaningItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val textMeaning = view.findViewById<TextView>(R.id.textMeaning)

        fun bind(dictionaryMeaningItem: DictionaryMeaningItem) {
            textMeaning.text = dictionaryMeaningItem.meaning
        }
    }
}