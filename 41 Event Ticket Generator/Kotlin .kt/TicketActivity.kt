package com.najrudinan.ticket

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.FileProvider
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

class TicketActivity : AppCompatActivity() {

    private lateinit var ticketCard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        ticketCard = findViewById(R.id.ticketCard)
        val eventNameTextView = findViewById<TextView>(R.id.eventNameTextView)
        val ticketNumberTextView = findViewById<TextView>(R.id.ticketNumberTextView)
        val qrCodeImageView = findViewById<ImageView>(R.id.qrCodeImageView)
        val downloadButton = findViewById<Button>(R.id.downloadButton)
        val shareButton = findViewById<Button>(R.id.shareButton)

        val eventName = intent.getStringExtra("eventName")
        val ticketNumber = UUID.randomUUID().toString().substring(0, 8).toUpperCase()

        eventNameTextView.text = eventName
        ticketNumberTextView.text = "Ticket #: $ticketNumber"

        val qrCodeBitmap = generateQrCode(eventName, ticketNumber)
        qrCodeImageView.setImageBitmap(qrCodeBitmap)

        downloadButton.setOnClickListener {
            val ticketBitmap = getBitmapFromView(ticketCard)
            saveTicketToGallery(ticketBitmap)
        }

        shareButton.setOnClickListener {
            val ticketBitmap = getBitmapFromView(ticketCard)
            shareTicket(ticketBitmap)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generateQrCode(eventName: String?, ticketNumber: String): Bitmap {
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode("Event: $eventName\nTicket: $ticketNumber", BarcodeFormat.QR_CODE, 512, 512)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bitmap.setPixel(x, y, if (bitMatrix[x, y]) 0xFF000000.toInt() else 0xFFFFFFFF.toInt())
            }
        }
        return bitmap
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    private fun saveTicketToGallery(bitmap: Bitmap) {
        val fileName = "Ticket_${System.currentTimeMillis()}.png"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/png")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/Tickets")
            }
        }

        val resolver = contentResolver
        val uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        uri?.let {
            try {
                resolver.openOutputStream(it)?.use { outputStream ->
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
                    Toast.makeText(this, "Ticket saved to Gallery", Toast.LENGTH_SHORT).show()
                } ?: Toast.makeText(this, "Failed to get output stream", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                Toast.makeText(this, "Failed to save ticket", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "Failed to create new MediaStore record", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareTicket(bitmap: Bitmap) {
        val imagePath = File(cacheDir, "images")
        imagePath.mkdirs()
        val file = File(imagePath, "ticket.png")
        val fileOutputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)
        fileOutputStream.close()

        val imageUri = FileProvider.getUriForFile(this, "com.najrudinan.ticket.fileprovider", file)

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, imageUri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(shareIntent, "Share Ticket"))
    }
}
