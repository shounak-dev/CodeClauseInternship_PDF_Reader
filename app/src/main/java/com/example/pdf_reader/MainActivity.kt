package com.example.pdf_reader

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import android.Manifest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        checkPermission()

    }
    fun initViews() {
        var openFileLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                val intent = Intent(this, PdfViewer::class.java)

                intent.putExtra("fileToOpen", data?.dataString)

                startActivity(intent)
            }
        }

        var openFileButton: Button = findViewById(R.id.loadBtn)

        openFileButton.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?) {
                val intent = Intent()
                    .setType("application/pdf")
                    .setAction(Intent.ACTION_GET_CONTENT)

                openFileLauncher.launch(intent)
            }
        })
    }

    fun checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, getString(R.string.permission_insufficient), Toast.LENGTH_SHORT).show()

            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 101)
        } else {
            // Do nothing.
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 101){
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, getString(R.string.permission_granted), Toast.LENGTH_SHORT).show()
            }
        }
    }
}