package com.example.latestnews

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide

class NewsAdapter(private var context: Context, private var newsData: List<Article>, var inter : handleonclick) : RecyclerView.Adapter<NewsAdapter.viewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
       val view = LayoutInflater.from(context).inflate(R.layout.news_recycler_view,parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
       return newsData.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        val article = newsData[position]
        holder.newsName.text = article.title
        holder.newsDesc.text = article.description
         Glide.with(context).load(article.urlToImage).placeholder(android.R.drawable.progress_indeterminate_horizontal).error(android.R.drawable.stat_notify_error).into(holder.image)
        holder.itemView.setOnClickListener(View.OnClickListener {
            inter.handleclick(article.url)
        })
    }

    class viewHolder(itemView: View) : ViewHolder(itemView){
      val image : ImageView = itemView.findViewById(R.id.news_image)
        val newsName : TextView = itemView.findViewById(R.id.textView)
        val newsDesc : TextView = itemView.findViewById(R.id.textView_detail)

    }
}

interface handleonclick{
    fun handleclick(url: String)
}