package ghanam.com.univeo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.univeo.databinding.MajorCardBinding


class MajorAdapter(  private val majors: MutableList<String>
) : RecyclerView.Adapter<MajorAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: MajorCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            MajorCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentMajor = majors[position]
        holder.binding.apply {
            majorText.text=currentMajor
        }
    }

    override fun getItemCount(): Int {
        return majors.size
    }
}