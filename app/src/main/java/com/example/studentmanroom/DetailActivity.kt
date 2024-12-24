package com.example.studentmanroom

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Nhận dữ liệu từ Intent
        val mssv = intent.getStringExtra("EXTRA_MSSV")
        val fullName = intent.getStringExtra("EXTRA_FULL_NAME")
        val dateOfBirth = intent.getStringExtra("EXTRA_DATE_OF_BIRTH")
        val email = intent.getStringExtra("EXTRA_EMAIL")

        // Tham chiếu tới các TextView
        findViewById<TextView>(R.id.textMSSV).text = mssv
        findViewById<TextView>(R.id.textFullName).text = fullName
        findViewById<TextView>(R.id.textDateOfBirth).text = dateOfBirth
        findViewById<TextView>(R.id.textEmail).text = email

        // Xử lý khi nhấn nút Edit hoặc Delete
        findViewById<Button>(R.id.btnEdit).setOnClickListener {
            // Code để mở màn hình chỉnh sửa thông tin sinh viên
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            // Code để xóa sinh viên
        }
    }
}
