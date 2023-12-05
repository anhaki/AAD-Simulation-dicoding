package com.dicoding.courseschedule.paging

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.data.Course
import com.dicoding.courseschedule.util.DayName.Companion.getByNumber

class CourseViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private lateinit var course: Course
    private val timeString = itemView.context.resources.getString(R.string.time_format)

    //TODO 7 : Complete ViewHolder to show item
    private val viewCourseName: TextView = view.findViewById(R.id.tv_course)
    private val viewTime: TextView = view.findViewById(R.id.tv_time)
    private val viewLecturer: TextView = view.findViewById(R.id.tv_lecturer)

    fun bind(course: Course, clickListener: (Course) -> Unit) {

        this.course = course

        course.apply {
            val dayName = getByNumber(day)
            val timeFormat = String.format(timeString, dayName, startTime, endTime)

            viewCourseName.text = courseName
            viewTime.text = timeFormat
            viewLecturer.text = lecturer
        }

        itemView.setOnClickListener {
            clickListener(course)
        }
    }

    fun getCourse(): Course = course
}