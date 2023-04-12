package com.example.nateintents

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnSms:Button
    private lateinit var btnEmail:Button
    private lateinit var btnCamera:Button
    private lateinit var btnShare:Button
    private lateinit var btnMpesa:Button
    private lateinit var btnCall:Button
    private lateinit var btnWebsite:Button
    private lateinit var btnMap:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSms = findViewById(R.id.mBtnSms)
        btnEmail = findViewById(R.id.mBtnEmail)
        btnCamera = findViewById(R.id.mBtncamera)
        btnShare = findViewById(R.id.mBtnShare)
        btnMpesa = findViewById(R.id.mBtnMpesa)
        btnCall = findViewById(R.id.mBtnCall)
        btnWebsite = findViewById(R.id.mBtnWebsite)
        btnMap = findViewById(R.id.mBtnMap)


        btnSms.setOnClickListener {
            val uri:Uri = Uri.parse("smsto:0792715825")
            val intent = Intent(Intent.ACTION_SENDTO, uri)
            intent.putExtra("sms_body", "Hello there...")
            startActivity(intent)
        }

        btnEmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jangaranath@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "JOB APPLICATION")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Dear sir, I'm applying for a data analyst job")
            startActivity(Intent.createChooser(emailIntent, "Send email..."))
        }

        btnCamera.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, 1)
        }

        btnMpesa.setOnClickListener {
            val simToolKitintent = applicationContext.packageManager.getLaunchIntentForPackage("com.android.stk")
            simToolKitintent?.let { startActivity(it) }
        }

        btnCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0792715825"))
            if (ContextCompat.checkSelfPermission(
                    this@MainActivity,
                    android.Manifest.permission.CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
         ){
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf<String>(android.Manifest.permission.CALL_PHONE),
                    1
                )
            }else{
                startActivity(intent)
            //    ContextCompat.checkSelfPermission(
//                this@MainActivity,
//                android.Manifest.permission.CALL_PHONE
//            )!= PackageManager.PERMISSION_DENIED)

            }
        }

        btnWebsite.setOnClickListener {
            val gotowebsite = Intent(this@MainActivity, WebsiteActivity::class.java)
            startActivity(gotowebsite)
        }
    }
}