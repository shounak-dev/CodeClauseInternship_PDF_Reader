package com.example.pdf_reader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.github.barteksc.pdfviewer.PDFView

class PdfViewer : AppCompatActivity() {

    private lateinit var pdfView: PDFView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_viewer)

        val path = intent.getStringExtra("fileToOpen")

        Toast.makeText(this, path, Toast.LENGTH_SHORT).show()

        pdfView = findViewById(R.id.pdfView)

        pdfView.fromUri(Uri.parse(path))
            .defaultPage(0)
            .enableSwipe(true)
            .swipeHorizontal(false)
            .enableAnnotationRendering(true)
            .load();
    }
}