package com.example.studentmanroom

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentmanroom.ui.theme.StudentManRoomTheme
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: StudentViewModel
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val searchView = findViewById<SearchView>(R.id.searchView)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        adapter = StudentAdapter(
            onItemClicked = { student ->
                val intent = Intent(this, DetailActivity::class.java).apply {
                    putExtra("EXTRA_MSSV", student.mssv)
                    putExtra("EXTRA_FULL_NAME", student.fullName)
                    putExtra("EXTRA_DATE_OF_BIRTH", student.dateOfBirth)
                    putExtra("EXTRA_EMAIL", student.email)
                }
                startActivity(intent)
            },
            onCheckedChange = { student, isChecked ->
                // Handle multiple selection
            }
        )
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        viewModel.allStudents.observe(this) { students ->
            adapter.updateData(students)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    viewModel.searchStudents(it).observe(this@MainActivity) { results ->
                        adapter.updateData(results)
                    }
                }
                return true
            }
        })
    }
}
