package com.komnacki.sportresultstracker

import android.support.v7.widget.RecyclerView
import android.view.View


class SportsListEmptyObserver(
        private var recyclerView: RecyclerView,
        private var emptyView: View)
    : RecyclerView.AdapterDataObserver() {

    init {
        checkIfEmpty()
    }

    private fun checkIfEmpty() {
        if(recyclerView.adapter != null){
            var isEmptyList = recyclerView.adapter.itemCount == 0
            if(isEmptyList){
                emptyView.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }else{
                emptyView.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }
    }

    override fun onChanged() {
        checkIfEmpty()
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        checkIfEmpty()
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        checkIfEmpty()
    }
}