package com.dicoding.courseschedule.ui.add

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.util.TimePickerFragment
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddCourseActivity : AppCompatActivity(), OnClickListener, TimePickerFragment.DialogTimeListener {

    private var isStart = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_course)

        supportActionBar?.title = getString(R.string.add_course)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val btnStartTime = findViewById<ImageButton>(R.id.ib_start_time)
        val btnEndTime = findViewById<ImageButton>(R.id.ib_end_time)

        btnStartTime.setOnClickListener(this)
        btnEndTime.setOnClickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_insert -> {
                val courseName = findViewById<TextInputEditText>(R.id.ed_course_name).text.toString()
                val lecturer = findViewById<TextInputEditText>(R.id.ed_lecturer).text.toString()
                val note = findViewById<TextInputEditText>(R.id.ed_note).text.toString()

                val day = findViewById<Spinner>(R.id.spinner_day).selectedItemPosition

                val startTime = findViewById<TextView>(R.id.tv_start_time).text.toString()
                val endTime = findViewById<TextView>(R.id.tv_end_time).text.toString()

                val factory = AddViewModelFactory.createFactory(this)
                val addViewModel = ViewModelProvider(this, factory)[AddCourseViewModel::class.java]

                addViewModel.insertCourse(
                    courseName = courseName,
                    lecturer = lecturer,
                    note = note,
                    startTime = startTime,
                    endTime = endTime,
                    day = day
                )

                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onClick(view: View) {
        val dialogFragment = TimePickerFragment()

        when(view.id){
            R.id.ib_start_time -> {
                isStart = true
                dialogFragment.show(supportFragmentManager, "timePicker")
            }
            R.id.ib_end_time -> {
                isStart = false
                dialogFragment.show(supportFragmentManager, "timePicker")
            }
        }
    }

    override fun onDialogTimeSet(tag: String?, hour: Int, minute: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, hour)
        calendar.set(Calendar.MINUTE, minute)

        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        if(isStart){
            findViewById<TextView>(R.id.tv_start_time).text = dateFormat.format(calendar.time)
        } else{
            findViewById<TextView>(R.id.tv_end_time).text = dateFormat.format(calendar.time)
        }
    }

}