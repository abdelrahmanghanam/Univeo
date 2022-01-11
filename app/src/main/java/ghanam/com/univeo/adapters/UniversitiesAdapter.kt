package ghanam.com.univeo.adapters



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.univeo.databinding.UniversitiesCardBinding
import ghanam.com.univeo.dataclasses.UniversityGeneral

class UniversitiesAdapter(  private val universities: MutableList<UniversityGeneral>
) : RecyclerView.Adapter<UniversitiesAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: UniversitiesCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            UniversitiesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun addMessage(msg: UniversityGeneral) {
        universities.add(msg)
        notifyItemInserted(universities.size - 1)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentUni = universities[position]
        holder.binding.apply {
            rankText.text=currentUni.rank
            cityText.text=currentUni.location
            universityTitle.text=currentUni.title



        }
    }

    override fun getItemCount(): Int {
        return universities.size
    }
}