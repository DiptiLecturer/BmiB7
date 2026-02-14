package org.freedu.bmicalculatorb7

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.freedu.bmicalculatorb7.databinding.ActivityCalculationBinding

class CalculationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalculationBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding= ActivityCalculationBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.gotoBtn.setOnClickListener {

            val heightTv = binding.heightET.text.toString().trim()
            val weightTv = binding.weightET.text.toString().trim()

            ///validation for height
            if (heightTv.isEmpty()) {
                binding.heightTIL.error="Enter height"
                return@setOnClickListener
            }else{
                binding.heightTIL.error=null
            }
            //validation for weight



            val height = heightTv.toDouble()
            val weight = weightTv.toDouble()

            if (height <= 0){
                binding.heightTIL.error="invalid height"
                return@setOnClickListener
            }
            //Bmi Calculation
            val bmi = weight/(height*height)

            //result section
            binding.title.parent?.let{
                (it as View).visibility= View.VISIBLE
            }
            binding.bmiResult.text = String.format("%.2f",bmi)

            //Bmi Status

            when{
                bmi<18.5 ->{
                    binding.bmiStatus.text ="Underweight"
                    binding.bmiAdvice.text="Eat more"
                }
                bmi in 18.5..24.9-> {
                    binding.bmiStatus.text ="Normal"
                    binding.bmiAdvice.text="Great!Please maintain"
                }

            }

            binding.bmiStatus.visibility= View.VISIBLE
            binding.bmiAdvice.visibility= View.VISIBLE
            //


        }

    }
}