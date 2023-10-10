package com.example.measuringapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private val lengths = arrayOf("Centimeter", "Meter", "Inches", "Foot")
    private val lengths2 = arrayOf("Inches", "Foot", "Centimeter", "Meter")
    private var lengthType1 = ""
    private var lengthType2 = ""
    private var result = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val item1 = findViewById<Spinner>(R.id.type1)
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,lengths)
        item1.adapter = arrayAdapter
        item1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                lengthType1 = item1.selectedItem.toString()

            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val item2 = findViewById<Spinner>(R.id.type2)
        val arrayAdapter1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,lengths2)
        item2.adapter = arrayAdapter1
        item2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                lengthType2 = item2.selectedItem.toString()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        val calculateButton = findViewById<TextView>(R.id.calculateButton)
        calculateButton.setOnClickListener{
            showResult()
        }
    }
    private fun calculateResult():Double{
        val measureAText = findViewById<EditText>(R.id.measure)
        val measureA = measureAText.text.toString().toDouble()

        when (lengthType1) {
            "Centimeter" -> {
                when (lengthType2) {
                    "Centimeter" -> result = measureA
                    "Meter" -> result = measureA / 100
                    "Inches" -> result = measureA / 2.54
                    "Foot" -> result = measureA * 0.0328084
                }
            }
            "Meter" -> {
                when (lengthType2) {
                    "Centimeter" -> result = measureA * 100
                    "Meter" -> result = measureA
                    "Inches" -> result = measureA * 39.3701
                    "Foot" -> result = measureA * 3.28084
                }
            }
            "Inches" -> {
                when (lengthType2) {
                    "Centimeter" -> result = measureA * 2.54
                    "Meter" -> result = measureA / 39.3701
                    "Inches" -> result = measureA
                    "Foot" -> result = measureA / 12
                }
            }
            "Foot" -> {
                when (lengthType2) {
                    "Centimeter" -> result = measureA * 30.48
                    "Meter" -> result = measureA / 3.28084
                    "Inches" -> result = measureA * 12
                    "Foot" -> result = measureA
                }
            }
        }

        return result

    }
    private fun showResult(){
        val res = calculateResult()
        val resText = findViewById<TextView>(R.id.res)

        val resultText = String.format("%.2f", res)



        resText.text = resultText
    }
}