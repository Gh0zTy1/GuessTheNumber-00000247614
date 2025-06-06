package garcia.carlosdamian.guessthenumber

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
    var num: Int = 0
    var won = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)


       generate.setOnClickListener { it;
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener { it;
            minValue = num
            if (checkingLimits()) {

                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            }else{
                guessings.setText("Me ganaste papu :,v")
            }

        }

        down.setOnClickListener {
            it;
            maxValue = num
            if (checkingLimits()) {

                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("Me ganaste papu :,v")
            }
        }
        guessed.setOnClickListener {
            it;
if (won){
    guessings.setText("Adivine tu numero we $num")
    guessings.setText("Jugar de nuevo")
    won = true
}else{


generate.visibility = View.VISIBLE
    guessings.setText("Tap on generate to start")
guessed.visibility = View.GONE
resetValues()
}





        }

    }

    fun resetValues() {

        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }


    fun checkingLimits(): Boolean {
        return minValue == maxValue
    }
}