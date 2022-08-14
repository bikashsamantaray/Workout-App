package com.bikash.a7minworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bikash.a7minworkout.databinding.ActivityBmiactivityBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    companion object{
        private const val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
        private const val US_UNITS_VIEW = "US_UNITS_VIEW"
    }

    private var currentVisibleView: String = METRIC_UNITS_VIEW

    private var binding: ActivityBmiactivityBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarBMIActivity)
        if (supportActionBar != null){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "CALCULATE BMI"
        }
        binding?.toolbarBMIActivity?.setNavigationOnClickListener{
            onBackPressed()
        }

        binding?.btnCalculateUnits?.setOnClickListener{
            calculateUnits()
        }



        makeVisibleMetricUnitsView()

        binding?.rgUnits?.setOnCheckedChangeListener { _, checkedId: Int ->

            if (checkedId == R.id.rbMetricUnits){
                makeVisibleMetricUnitsView()
            }else{
                makeVisibleUSUnitsView()
            }

        }


    }

    private fun makeVisibleMetricUnitsView(){
        currentVisibleView = METRIC_UNITS_VIEW

        binding?.tilMetricUnitWeight?.visibility = View.VISIBLE
        binding?.tilUSUnitWeight?.visibility = View.INVISIBLE
        binding?.llFeetInch?.visibility = View.INVISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.VISIBLE

        binding?.etMetricUnitHeight?.text!!.clear()
        binding?.etMetricUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun makeVisibleUSUnitsView(){
        currentVisibleView = US_UNITS_VIEW

        binding?.tilMetricUnitWeight?.visibility = View.INVISIBLE
        binding?.tilUSUnitWeight?.visibility = View.VISIBLE
        binding?.llFeetInch?.visibility = View.VISIBLE
        binding?.tilMetricUnitHeight?.visibility = View.INVISIBLE

        binding?.etMetricUnitFeet?.text!!.clear()
        binding?.etMetricUnitInch?.text!!.clear()
        binding?.etUSUnitWeight?.text!!.clear()

        binding?.llDisplayBMIResult?.visibility = View.INVISIBLE
    }

    private fun displayBMIResults(bmi:Float){

        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0){
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops!! You really need to take better care of yourself! Eat More!"
        }else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0){
            bmiLabel = " Severely Underweight"
            bmiDescription = "Oops!! You really need to take better care of yourself! Eat More!"
        }else if(bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0){
            bmiLabel = "Underweight"
            bmiDescription = "Oops!! You really need to take better care of yourself! Eat More!"
        }else if(bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! you are in good shape"
        }else if(bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops!! You really need to take better care of yourself! WORKOUT"
        }else if(bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0) {
            bmiLabel = "Obese Class | (Moderately Obese)"
            bmiDescription = "Oops!! You really need to take better care of yourself! WORKOUT"
        }else if(bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class | (Severely Obese)"
            bmiDescription = "OMG!! You are in dangerous condition! WORKOUT NOW"
        }else {
            bmiLabel = "Obese Class | (Very Severely Obese)"
            bmiDescription = "OMG!! You are in dangerous condition! WORKOUT NOW "
        }

        binding?.llDisplayBMIResult?.visibility = View.VISIBLE
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()
        binding?.tvBMIValue?.text = bmiValue
        binding?.tvBMIType?.text = bmiLabel
        binding?.tvBMIDescription?.text = bmiDescription
    }

    private fun validateMetricUnits():Boolean{
        var isValid = true

        if (binding?.etMetricUnitWeight?.text.toString().isEmpty()){
            isValid = false
        }else if(binding?.etMetricUnitHeight?.text.toString().isEmpty()){
            isValid = false
        }

        return isValid
    }

    private fun validateUSUnits():Boolean{
        var isValid = true

        when{
            binding?.etUSUnitWeight?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etMetricUnitFeet?.text.toString().isEmpty() -> {
                isValid = false
            }
            binding?.etMetricUnitInch?.text.toString().isEmpty() -> {
                isValid = false
            }
        }
        return isValid
    }

    private fun calculateUnits(){
        if(currentVisibleView == METRIC_UNITS_VIEW){
            if (validateMetricUnits()){
                val heightValue: Float = binding?.etMetricUnitHeight?.text.toString().toFloat() / 100

                val weightValue: Float = binding?.etMetricUnitWeight?.text.toString().toFloat()

                val bmi = weightValue / (heightValue * heightValue)

                displayBMIResults(bmi)
            }else{
                Toast.makeText(this,"Please enter valid values",Toast.LENGTH_SHORT).show()
            }
        }else{
            if(validateUSUnits()){
                val usUnitHeightValueFeet: String = binding?.etMetricUnitFeet?.text.toString()
                val usUnitHeightValueInch: String = binding?.etMetricUnitInch?.text.toString()
                val usUnitWeightValue: Float = binding?.etUSUnitWeight?.text.toString().toFloat()

                val heightValue = usUnitHeightValueInch.toFloat() + usUnitHeightValueFeet.toFloat() * 12

                val bmi = 703 * (usUnitWeightValue / (heightValue * heightValue))

                displayBMIResults(bmi)
            }else {
                Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show()
            }
        }
    }
}