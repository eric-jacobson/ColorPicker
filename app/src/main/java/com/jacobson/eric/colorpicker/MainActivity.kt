package com.jacobson.eric.colorpicker

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.jetbrains.anko.selector
import java.io.OutputStreamWriter
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var rgbColors:IntArray = intArrayOf(0, 0, 0)
    var savedColors = arrayListOf<CharSequence>()

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
            R.id.action_save -> {
                saveColor()
                return true
            }
            R.id.action_recall -> {
                showColorsList()
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

    private fun saveColor(){
        val file = openFileOutput("savedColorData.txt", Context.MODE_APPEND)
        val fileOut = OutputStreamWriter(file)
        var color = EditText(this)
        val alert = AlertDialog.Builder(this@MainActivity).create()
        alert.setTitle("Save Color")
        alert.setView(color)
        alert.setButton(AlertDialog.BUTTON_POSITIVE, "Save", {
            dialogInterface, i ->
            //var result = color.text.toString()
            val data = "${rgbColors[0]} ${rgbColors[1]} ${rgbColors[2]}"
            fileOut.append(data)
            fileOut.close()
        })
        alert.show()
    }

    private fun loadColors(){
        try {
            val fileIn = openFileInput("savedColorData.txt")
            val reader = fileIn.bufferedReader()
            savedColors.clear()
            reader.forEachLine {
                savedColors.add(it)
            }
        } catch (e:Exception){
            Log.i("Exception: ", e.toString())
        }
    }

    private fun showColorsList(){
        loadColors()
        selector("Saved Colors", savedColors, {dialogInterface, i ->
            selectColorFromList(i)
            val colorName = savedColors[i].split(" ")
            Toast.makeText(applicationContext, "${colorName} selected!", Toast.LENGTH_SHORT).show()
        })
    }

    private fun selectColorFromList(index: Int){
        val color = savedColors[index].split(" ")
        val red:Int = color[0].toInt()
        val green:Int = color[1].toInt()
        val blue:Int = color[2].toInt()
        rgbColors[0] = red
        rgbColors[1] = green
        rgbColors[2] = blue
        redSeekBar.progress = red
        greenSeekBar.progress = green
        blueSeekBar.progress = blue
        seekBarProgress()
    }
}
