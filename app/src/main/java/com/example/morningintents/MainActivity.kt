package com.example.morningintents

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.sax.StartElementListener
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat


class MainActivity : AppCompatActivity() {
    lateinit var btnsms:Button
    lateinit var btnEmail:Button
    lateinit var btnCamera: Button
    lateinit var btnShare:Button
    lateinit var btnMpesa:Button
    lateinit var btnCall:Button
    lateinit var btnWebsite:Button
    lateinit var btnmap:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnsms=findViewById(R.id.mbtnsms)
        btnEmail=findViewById(R.id.mbtnemail)
        btnCamera=findViewById(R.id.mbtncamera)
        btnShare=findViewById(R.id.mbtnshare)
        btnMpesa=findViewById(R.id.mbtnmpesa)
        btnCall=findViewById(R.id.mbtncall)
        btnWebsite=findViewById(R.id.mbtnwebsite)
        btnmap=findViewById(R.id.mbtnMap)


        btnsms.setOnClickListener {
            val uri: Uri = Uri.parse("smsto:Ek")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hi,how are you?")
            startActivity(intent)
        }
        btnEmail.setOnClickListener {
            val emailIntent =
                Intent(Intent.ACTION_SENDTO, Uri.fromParts("fred.dorcar@gmail.com", "otienoglory20@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Job Application")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear sir,Following the advertisement")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))

        }
        btnCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)
        }
        btnShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!")
            startActivity(shareIntent)
        }
        btnMpesa.setOnClickListener {
            val simToolKitLaunchIntent =
                applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitLaunchIntent?.let { startActivity(it) }

        }
        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0721738395"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            } else {
                startActivity(intent)
            }

        }
        btnWebsite.setOnClickListener {
            var tembea = Intent(this@MainActivity,WebsiteActivity::class.java)
            startActivity(tembea)
        }
        btnmap.setOnClickListener {
             val ramani= Intent(this,MapsActivity::class.java)
            startActivity(ramani)
        }




    }
}