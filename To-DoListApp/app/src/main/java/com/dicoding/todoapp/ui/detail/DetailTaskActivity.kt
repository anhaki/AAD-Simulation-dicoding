package com.dicoding.todoapp.ui.detail

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.ViewModelFactory
import com.dicoding.todoapp.utils.DateConverter
import com.dicoding.todoapp.utils.TASK_ID
import com.google.android.material.textfield.TextInputEditText

class DetailTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        //TODO 11 : Show detail task and implement delete action
        val edTitle: TextInputEditText = findViewById(R.id.detail_ed_title)
        val edDescription = findViewById<TextInputEditText>(R.id.detail_ed_description)
        val edDueDate = findViewById<TextInputEditText>(R.id.detail_ed_due_date)
        val btnDelete = findViewById<Button>(R.id.btn_delete_task)

        val taskId = intent.getIntExtra(TASK_ID, 0)

        val factory = ViewModelFactory.getInstance(this)
        val detailTaskViewModel = ViewModelProvider(this, factory)[DetailTaskViewModel::class.java]

        detailTaskViewModel.setTaskId(taskId)

        detailTaskViewModel.task.observe(this) {
            edTitle.setText(detailTaskViewModel.task.value?.title)
            edDescription.setText(detailTaskViewModel.task.value?.description)
            detailTaskViewModel.task.value?.dueDateMillis?.let {
                edDueDate.setText(DateConverter.convertMillisToString(it))
            }
        }

        btnDelete.setOnClickListener{
            detailTaskViewModel.deleteTask()
            onBackPressedDispatcher.onBackPressed()
        }
    }
}