package com.example.bai1

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var edtNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var btnShow: Button
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ các view
        edtNumber = findViewById(R.id.edtNumber)
        radioGroup = findViewById(R.id.radioGroup)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)

        btnShow.setOnClickListener { handleShowButton() }
    }

    private fun handleShowButton() {
        val input = edtNumber.text.toString().trim()

        if (input.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập số nguyên dương.", Toast.LENGTH_SHORT).show()
            return
        }

        val n = input.toIntOrNull()
        if (n == null || n < 0) {
            Toast.makeText(this, "Dữ liệu không hợp lệ.", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedId = radioGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Vui lòng chọn loại số.", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (selectedId) {
            R.id.radioEven -> generateEvenNumbers(n)
            R.id.radioOdd -> generateOddNumbers(n)
            R.id.radioSquare -> generateSquareNumbers(n)
            else -> emptyList()
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
        listView.adapter = adapter
    }

    private fun generateEvenNumbers(n: Int): List<String> =
        (0..n step 2).map { it.toString() }

    private fun generateOddNumbers(n: Int): List<String> =
        (1..n step 2).map { it.toString() }

    private fun generateSquareNumbers(n: Int): List<String> =
        (0..n).mapNotNull { i ->
            val square = i * i
            if (square <= n) square.toString() else null
        }
}
