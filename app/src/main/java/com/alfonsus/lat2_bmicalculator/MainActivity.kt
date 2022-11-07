package com.alfonsus.lat2_bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsMessage.SubmitPdu
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.alfonsus.lat2_bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //Deklarasi variable
    private lateinit var binding :ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        //variablenya diinisialisasi nilai
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = binding.root
        setContentView(view)

        val btn = binding.btnCalculate

        btn.setOnClickListener {
            val weight = binding.etWeight.getText()!!.toString()
            val height = binding.etHeight.getText()!!.toString()
            val BMI = weight.toFloat()/((height.toFloat()/100)*((height.toFloat()/100)))
            val BMI2DIGIT = String.format("%.2f",BMI).toFloat()
            Log.d("Test hasil hitung", BMI.toString())
            if(validateInput(weight,height))
            {
            Result(BMI2DIGIT)
            }

        }


    }

    private fun validateInput(weight:String?,height:String?):Boolean{
        when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        return true
    }
    private fun Result(BMI:Float)
    {
        var displayText =""
        var color = 0
        when{
            BMI<18.50 ->{
                binding.tvResult.setText("Underweight")
                color = R.color.text
//                binding.tvResult.getColor
            }
            BMI in 18.50..24.99->{
                binding.tvResult.setText("Normal")
                color = R.color.green
            }
            BMI in 25.00..29.99->{
                binding.tvResult.setText("Overweight")
                color = R.color.darkyellow
            }
            BMI > 29.99->{
                binding.tvResult.setText("Obesse")
                color = R.color.red
            }
        }
        binding.tvResult.setTextColor(ContextCompat.getColor(this,color))
    }
}



