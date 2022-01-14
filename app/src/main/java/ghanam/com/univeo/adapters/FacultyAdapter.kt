package ghanam.com.univeo.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.univeo.databinding.FacultyCardBinding
import ghanam.com.univeo.dataclasses.FacultyGeneral
import android.os.Bundle
import androidx.navigation.Navigation
import ghanam.com.univeo.R
import ghanam.com.univeo.singletons.DBReader


class FacultyAdapter(  private val Faculties: MutableList<HashMap<String, Any>>
) : RecyclerView.Adapter<FacultyAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: FacultyCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            FacultyCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun addFaculty(fac: HashMap<String, Any>) {
        Faculties.add(fac)
        notifyItemInserted(Faculties.size - 1)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentFaculty = Faculties[position]
        holder.binding.apply {
            facultyButton.text=currentFaculty["name"] as String
            facultyButton.setOnClickListener {
                DBReader.currentFaculty=currentFaculty
                Navigation.findNavController(it).navigate(R.id.action_universityFragment_to_facultyFragment)
            }
        }
    }

    override fun getItemCount(): Int {
        return Faculties.size
    }
}