package com.jacobson.eric.colorpicker

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val seekBarRed = findViewById<SeekBar>(R.id.seekBar_red)
        val seekBarGreen = findViewById<SeekBar>(R.id.seekBar_green)
        val seekBarBlue = findViewById<SeekBar>(R.id.seekBar_blue)

        var textViewValRed = findViewById<TextView>(R.id.textView_redVal)
        var textViewValGreen = findViewById<TextView>(R.id.textView_greenVal)
        var textViewValBlue = findViewById<TextView>(R.id.textView_blueVal)

        var red = 0
        var green = 0
        var blue = 0

        textView_colorFill.setBackgroundColor(Color.rgb(red, green, blue))
        textViewValRed.text = "$red"
        textViewValGreen.text = "$green"
        textViewValBlue.text = "$blue"


        seekBarRed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                red = progress
                textView_colorFill.setBackgroundColor(Color.rgb(red, green, blue))
                textViewValRed.text = "$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarGreen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                green = progress
                textView_colorFill.setBackgroundColor(Color.rgb(red, green, blue))
                textViewValGreen.text = "$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        seekBarBlue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                blue = progress
                textView_colorFill.setBackgroundColor(Color.rgb(red, green, blue))
                textViewValBlue.text = "$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
