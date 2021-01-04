package com.etpl.demoencora.adapters
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.etpl.demoencora.databinding.HomeItemBinding
import com.etpl.demoencora.model.Docs
import com.etpl.demoencora.utils.getDate

class HomeAdapter(val context: Context, var modelList:List<Docs>): RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    var homeItemBinding:HomeItemBinding?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        homeItemBinding = HomeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(homeItemBinding!!)
    }

    override fun getItemCount(): Int {
        return modelList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.e("OnBind",position.toString())
        holder.bind(modelList[position])
    }

    class ViewHolder(var homeItemBinding: HomeItemBinding):RecyclerView.ViewHolder(homeItemBinding.root) {
        fun bind(item:Docs){
           // this.homeItemBinding.docsJob = item
           // homeItemBinding.publicationDate.text = item.publication_date.getDate()
            homeItemBinding.executePendingBindings()
        }
    }

    fun setData(list:List<Docs>){
        modelList = list
        notifyDataSetChanged()
    }
}