package com.example.taskmanageractivity

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Spinner
import android.widget.Button
import android.widget.ListView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    private lateinit var taskInput: EditText
    private lateinit var categorySpinner: Spinner
    private lateinit var addButton: Button
    private lateinit var clearButton: Button
    private lateinit var taskListView: ListView
    private lateinit var taskAdapter: ArrayAdapter<String>
    private val taskList = ArrayList<String>()
    private val sharedPrefKey = "TASKS_PREF"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


            taskInput = findViewById(R.id.taskInput)
            categorySpinner = findViewById(R.id.catSpinner)
            addButton = findViewById(R.id.addBtn)
            clearButton = findViewById(R.id.clearBtn)
            taskListView = findViewById(R.id.taskListView)


            val categories = arrayOf("Work", "Personal", "Urgent", "Education", "Family", "Health")
            val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, categories)
            categorySpinner.adapter = spinnerAdapter

            loadTasks()
            taskAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, taskList)
            taskListView.adapter = taskAdapter

            addButton.setOnClickListener {
                addTask()
            }
             taskListView.setOnItemClickListener { _, _, position, _ ->
            showTaskOptions(position)
             }

            clearButton.setOnClickListener {
                clearAllTasks()
            }

        }

        private fun addTask() {
            val task = taskInput.text.toString().trim()
            val category = categorySpinner.selectedItem.toString()
            if (task.isNotEmpty()) {
                val taskEntry = "$task - [$category]"
                taskList.add(taskEntry)
                taskAdapter.notifyDataSetChanged()
                saveTasks()
                taskInput.text.clear()
            } else {
                Toast.makeText(this, "Enter a task", Toast.LENGTH_SHORT).show()
            }
        }

          private fun showTaskOptions(position: Int) {
        val options = arrayOf("Update Task", "Delete Task")
        AlertDialog.Builder(this)
            .setTitle("Task Options")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> updateTask(position)
                    1 -> removeTask(position)
                }
            }
            .show()
    }
    private fun updateTask(position: Int) {
        val taskParts = taskList[position].split(" - [")
        val taskName = taskParts[0]
        taskInput.setText(taskName)
        taskList.removeAt(position)
        taskAdapter.notifyDataSetChanged()
        saveTasks()
    }

        private fun removeTask(position: Int) {
            taskList.removeAt(position)
            taskAdapter.notifyDataSetChanged()
            saveTasks()
        }

        private fun clearAllTasks() {
            taskList.clear()
            taskAdapter.notifyDataSetChanged()
            saveTasks()
        }

        private fun saveTasks() {
            val sharedPreferences = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putStringSet("tasks", taskList.toSet())
            editor.apply()
        }

        private fun loadTasks() {
            val sharedPreferences = getSharedPreferences(sharedPrefKey, Context.MODE_PRIVATE)
            val savedTasks = sharedPreferences.getStringSet("tasks", setOf())
            taskList.clear()
            taskList.addAll(savedTasks ?: setOf())
        }
    }

