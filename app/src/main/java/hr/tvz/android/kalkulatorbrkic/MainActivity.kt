package hr.tvz.android.kalkulatorbrkic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import hr.tvz.android.kalkulatorbrkic.databinding.ActivityMainBinding
import kotlin.math.round
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.themeSwitch.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                false -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                true -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.calculate.setOnClickListener{
            var ok = true
            binding.rentInput.error = null
            val monthlyPay = binding.monthlyPayInput.text.toString().toFloatOrNull()
            val utilities = binding.utilitiesInput.text.toString().toFloatOrNull()
            val rent = binding.rentInput.text.toString().toFloatOrNull()
            val otherCosts = binding.otherCostsInput.text.toString().toFloatOrNull()
            if(monthlyPay == null)
            {
                binding.monthlyPayInput.error = getString(R.string.inputError)
                ok = false
            }
            if(utilities == null)
            {
                binding.utilitiesInput.error = getString(R.string.inputError)
                ok = false
            }
            if(rent == null)
            {
                binding.rentInput.error = getString(R.string.inputError)
                ok = false
            }
            if(otherCosts == null)
            {
                binding.otherCostsInput.error = getString(R.string.inputError)
                ok = false
            }
            if (ok) {
                val monthlyBalance = monthlyPay!! - (utilities!! +  rent!! + otherCosts!!)
                if(monthlyBalance <= 0)
                    Toast.makeText(applicationContext, getString(R.string.adjective), Toast.LENGTH_LONG).show()
                else
                {
                    var calc = (1000000.0 / monthlyBalance) / 12.0
                    val rounded = (calc * 100.0).roundToInt() / 100.0
                    Toast.makeText(applicationContext, rounded.toString() + " " + getString(R.string.years), Toast.LENGTH_LONG).show()
                }

            }

        }
    }
}