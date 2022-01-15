package ghanam.com.univeo.adapters



import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ghanam.com.univeo.databinding.UniversitiesCardBinding
import ghanam.com.univeo.dataclasses.UniversityGeneral
import ghanam.com.univeo.singletons.DBReader
import ghanam.com.univeo.university.UniversityActivity

class UniversitiesAdapter(  private var universities: MutableList<UniversityGeneral>
) : RecyclerView.Adapter<UniversitiesAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: UniversitiesCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            UniversitiesCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun addUniversity(uni: UniversityGeneral) {
        universities.add(uni)
        notifyItemInserted(universities.size - 1)
    }

    fun clearList(){
        universities= mutableListOf()
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentUni = universities[position]
        holder.binding.apply {
            rankText.text=currentUni.rank
            cityText.text=currentUni.location
            universityTitle.text=currentUni.title
            Picasso.with(universityImage.context).load(currentUni.imgUrl).into(universityImage)
            exploreButton.setOnClickListener {
                val uni=DBReader.getUniversityById(currentUni.id)
                if (uni!=null){
                    val intent = Intent(it.context,  UniversityActivity::class.java)
                    it.context.startActivity(intent)
                }

            }

        }
    }

    override fun getItemCount(): Int {
        return universities.size
    }
}


