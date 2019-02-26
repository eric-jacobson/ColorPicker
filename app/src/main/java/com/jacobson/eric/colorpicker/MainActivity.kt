package com.jacobson.eric.colorpicker

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    var rgbColors:IntArray = intArrayOf(0, 0, 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        seekBarProgress()
        setUpSeekBarChangeListener(redSeekBar, 0)
        setUpSeekBarChangeListener(greenSeekBar, 1)
        setUpSeekBarChangeListener(blueSeekBar, 2)
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
            //TODO: Set up the actions when methods are implemented
            R.id.action_save -> {
                return true
            }
            R.id.action_recall -> {
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpSeekBarChangeListener(seekBar: SeekBar, index:Int){
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                rgbColors[index] = progress
                seekBarProgress()
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }

    private fun seekBarProgress(){
        redProgressText.text = rgbColors[0].toString()
        greenProgressText.text = rgbColors[1].toString()
        blueProgressText.text = rgbColors[2].toString()
        textView_colorFill.setBackgroundColor(Color.rgb(rgbColors[0], rgbColors[1], rgbColors[2]))
    }
}
