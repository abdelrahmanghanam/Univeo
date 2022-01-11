package ghanam.com.univeo.adapters


import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.univeo.databinding.NewsCardBinding
import ghanam.com.univeo.dataclasses.NewsGeneral
import androidx.core.content.ContextCompat.startActivity

import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import kotlinx.coroutines.currentCoroutineContext


class NewsAdapter(  private val news: MutableList<NewsGeneral>
) : RecyclerView.Adapter<NewsAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: NewsCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            NewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun addMessage(msg: NewsGeneral) {
        news.add(msg)
        notifyItemInserted(news.size - 1)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentNews = news[position]
        holder.binding.apply {
            newsTitle.text=currentNews.title

            detailsButton.setOnClickListener {
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(currentNews.imgUrl))
                detailsButton.context.startActivity(browserIntent)
            }



        }
    }

    override fun getItemCount(): Int {
        return news.size
    }
}