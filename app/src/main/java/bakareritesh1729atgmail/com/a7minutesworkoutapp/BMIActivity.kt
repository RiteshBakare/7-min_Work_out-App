package bakareritesh1729atgmail.com.a7minutesworkoutapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import bakareritesh1729atgmail.com.a7minutesworkoutapp.databinding.ActivityBmiBinding
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    private var binding : ActivityBmiBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        
        setSupportActionBar(binding?.bmiActionBar)

        if(supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = "BMI Calculator"
        }
        binding?.bmiActionBar?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding?.btnCalculate?.setOnClickListener {
            if(validatesMetricsUnit()) {
                val heightValue : Float = binding?.edMetricHeight?.text.toString().toFloat() / 100
                val weightValue : Float = binding?.edMetricWeight?.text.toString().toFloat()
                val bmi = weightValue / (heightValue*heightValue)

                displayBMIResult(bmi)
            }
            else {
                Toast.makeText(this@BMIActivity ,"Please Enter Valid values",Toast.LENGTH_LONG).show()
            }
        }

        // binding?.metricsLL?.visibility = View.VISIBLE
        // binding?.USUnitsLL?.visibility = View.GONE

        binding?.myRadioGroup?.setOnCheckedChangeListener{_,checkedId : Int ->
            if(checkedId == R.id.metricsLL) {
                binding?.metricsLL?.visibility = View.VISIBLE
                binding?.USUnitsLL?.visibility = View.GONE
            }
            else  {
                binding?.USUnitsLL?.visibility = View.VISIBLE
                binding?.metricsLL?.visibility = View.GONE
            }
        }

    }


    private fun displayBMIResult(bmi : Float) {

        val bmiLabel : String
        val bmiDescription : String

        if(bmi.compareTo(15f) <= 0 ) {
            bmiLabel = "Very Severely Under Weight"
            bmiDescription = "Oops! You Really need to take better care of yourself! Eat More "
        }
        else if (bmi.compareTo(15f)>0 && bmi.compareTo(16f) <=0) {
            bmiLabel = "Severally Under Weight "
            bmiDescription = "Oops! You Really need to take better care of yourself! Eat More "
        }
        else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <=0 ) {
            bmiLabel = "UnderWeight"
            bmiDescription = "Oops! You Really need to take better care of yourself! Eat More"
        }
        else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! you are in good shape "
        }
        else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <=0) {
            bmiLabel = "OverWeight"
            bmiDescription = "Oops! You really need to take care of yourself! Workout"
        }
        else if (bmi.compareTo(30f) >0 && bmi.compareTo(35f) <=0) {
            bmiLabel = "Obese Class | ( Moderately Obese )"
            bmiDescription = "Oops! You really need to take care of yourself! Workout"
        }
        else if (bmi.compareTo(35f)>0 && bmi.compareTo(40f) <=0 ) {
            bmiLabel = "Obese Class | ( Severely Obese )"
            bmiDescription = "OMG You are in very dangerous condition act now! "
        }
        else {
            bmiLabel = "Obese Class | ( Very Severely Obese )"
            bmiDescription = "OMG You are in very dangerous condition act now! "
        }

        val bmiValue =  BigDecimal(bmi.toDouble()).setScale(2,RoundingMode.HALF_EVEN).toString()

        binding?.tvBMINum?.text = bmiValue
        binding?.tvBMIResult?.text = bmiLabel
        binding?.tvBMIMessage?.text = bmiDescription

    }


    private  fun validatesMetricsUnit() : Boolean {
        var isValid = true
        if(binding?.edMetricHeight?.text.toString().isEmpty()) {
            isValid = false
        }
        else if (binding?.edMetricWeight?.text.toString().isEmpty()) {
            isValid = false
        }
        return isValid
    }


}