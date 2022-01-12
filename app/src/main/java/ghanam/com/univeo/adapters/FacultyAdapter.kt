package ghanam.com.univeo.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.univeo.databinding.FacultyCardBinding
import ghanam.com.univeo.dataclasses.FacultyGeneral


class FacultyAdapter(  private val Faculties: MutableList<FacultyGeneral>
) : RecyclerView.Adapter<FacultyAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: FacultyCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            FacultyCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun addFaculty(msg: FacultyGeneral) {
        Faculties.add(msg)
        notifyItemInserted(Faculties.size - 1)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentFaculty = Faculties[position]
        holder.binding.apply {

        }
    }

    override fun getItemCount(): Int {
        return Faculties.size
    }
}