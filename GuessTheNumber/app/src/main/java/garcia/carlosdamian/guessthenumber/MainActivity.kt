package garcia.carlosdamian.guessthenumber


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue = 0
    var maxValue = 101
    var num: Int = 0
    var won = false


    var intentos = 0
    val maxIntentos = 15


    private lateinit var downButton: Button
    private lateinit var upButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessings)

        downButton = findViewById(R.id.down)
        upButton = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {

            resetValues()
            num = Random.nextInt(minValue, maxValue)
            guessings.text = num.toString()
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        upButton.setOnClickListener {
            intentos++
            if (intentos >= maxIntentos) {
                guessings.text = "me ganaste no pude adivinar tu numero"
                upButton.isEnabled = false
                downButton.isEnabled = false
            } else {

                minValue = num + 1
                if (checkingLimits()) {
                    guessings.text = "me ganaste no pude adivinar tu numero"
                    upButton.isEnabled = false
                    downButton.isEnabled = false
                } else {
                    num = Random.nextInt(minValue, maxValue)
                    guessings.text = num.toString()
                }
            }
        }

        downButton.setOnClickListener {
            intentos++
            if (intentos >= maxIntentos) {
                guessings.text = "me ganaste no pude adivinar tu numero"
                upButton.isEnabled = false
                downButton.isEnabled = false
            } else {

                maxValue = num
                if (checkingLimits()) {
                    guessings.text = "Me ganaste papu :,v"
                    upButton.isEnabled = false
                    downButton.isEnabled = false
                } else {
                    num = Random.nextInt(minValue, maxValue)
                    guessings.text = num.toString()
                }
            }
        }

        guessed.setOnClickListener {

            generate.visibility = View.VISIBLE
            guessings.text = "Tap on generate to start"
            guessed.visibility = View.GONE
            resetValues()
        }
    }

    fun resetValues() {
        minValue = 0
        maxValue = 101
        num = 0
        won = false
        intentos = 0

        if (::upButton.isInitialized && ::downButton.isInitialized) {
            upButton.isEnabled = true
            downButton.isEnabled = true
        }
    }

    fun checkingLimits(): Boolean {
        return minValue >= maxValue
    }
}
