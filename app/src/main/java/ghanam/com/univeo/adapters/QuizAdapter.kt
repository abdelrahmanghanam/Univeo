package ghanam.com.univeo.adapters



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ghanam.com.univeo.databinding.MajorCardBinding
import ghanam.com.univeo.databinding.QuestionCardBinding
import ghanam.com.univeo.dataclasses.Quiz


class QuizAdapter(  private val questions: MutableList<Quiz>
) : RecyclerView.Adapter<QuizAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: QuestionCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            QuestionCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    fun addQustion(question: Quiz) {
        questions.add(question)
        notifyItemInserted(questions.size - 1)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentQuestion = questions[position]
        holder.binding.apply {
            questionText.text=currentQuestion.question
            choice1Text.text=currentQuestion.choice1
            choice2Text.text=currentQuestion.choice2
            choice3Text.text=currentQuestion.choice3
        }
    }

    override fun getItemCount(): Int {
        return questions.size
    }
}