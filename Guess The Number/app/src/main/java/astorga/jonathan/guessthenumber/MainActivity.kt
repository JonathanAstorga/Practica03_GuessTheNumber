package astorga.jonathan.guessthenumber

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 100
    var num:Int = 0
    var won = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val guessings: TextView = findViewById(R.id.txtGuessings)
        val down: Button = findViewById(R.id.btnDown)
        val up: Button = findViewById(R.id.btnUp)
        val generate: Button = findViewById(R.id.btnGenerate)
        val guessed: Button = findViewById(R.id.btnGuessed)

        generate.setOnClickListener{
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener{
            minValue = num
            if(chekingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else{
                guessings.setText("No puede ser :( Me ganaste")
            }
        }

        down.setOnClickListener{
            maxValue = num
            if (chekingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else{
                guessings.setText("No puede ser :( Me ganaste")
            }
        }

        guessed.setOnClickListener{
            if(!won) {
                guessings.setText("Adiviné, tu número es el " + num)
                guessed.setText("Play again")
                won = true
            } else{
                generate.visibility = View.VISIBLE
                generate.setText("Tap on generate to start")
                guessed.setText("Guessed")
                guessed.visibility = View.GONE
                resetValues()
            }
        }

    }

    fun resetValues(){
        minValue = 0
        maxValue = 100
        won = false
    }

    fun chekingLimits(): Boolean{
        return minValue != maxValue
    }
}