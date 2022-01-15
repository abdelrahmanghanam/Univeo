package ghanam.com.univeo.adapters


import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.univeo.databinding.FacultyCardBinding
import ghanam.com.univeo.singletons.DBReader
import ghanam.com.univeo.university.FacultyActivity


class FacultyAdapter(  private val Faculties: MutableList<HashMap<String, Any>>
) : RecyclerView.Adapter<FacultyAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: FacultyCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            FacultyCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentFaculty = Faculties[position]
        holder.binding.apply {
            facultyButton.text=currentFaculty["name"] as String
            facultyButton.setOnClickListener {
                DBReader.currentFaculty=currentFaculty
                if (currentFaculty!=null) {
                    val intent = Intent(it.context, FacultyActivity::class.java)

                    it.context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return Faculties.size
    }
}