package ghanam.com.univeo.adapters



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.univeo.databinding.QuestionCardBinding
import ghanam.com.univeo.dataclasses.Quiz


class QuizAdapter(private val questions: MutableList<Quiz>, private val answers: IntArray
) : RecyclerView.Adapter<QuizAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: QuestionCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            QuestionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }



    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentQuestion = questions[position]
        holder.binding.apply {
            questionText.text=currentQuestion.question
            c1.text=currentQuestion.choice1
            c2.text=currentQuestion.choice2
            c3.text=currentQuestion.choice3
            c1.setOnClickListener {
                answers[position]=1
            }
            c2.setOnClickListener {
                answers[position]=2
            }
            c3.setOnClickListener{
                answers[position]=3
            }
        }

    }

    override fun getItemCount(): Int {
        return questions.size
    }
}