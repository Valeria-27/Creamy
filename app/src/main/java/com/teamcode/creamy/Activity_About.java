package com.teamcode.creamy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Activity_About extends AppCompatActivity {


    private TextView tvNumberMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupHyperlink();
        tvNumberMessage = findViewById(R.id.tvNumberMessage);

        tvNumberMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SendMessage2(view);

            }
        });

    }

    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.tvNumberMessage);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }


    public void SendMessage(View view)
    {
        String numeroTelefono = "59167550712"; // Aquí va el número de teléfono, no olvidar el código de pais al inicio
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setComponent(new ComponentName("com.whatsapp", "com.whatsapp.ContactPicker"));
        sendIntent.setType("text/plain");
        sendIntent.putExtra("jid", PhoneNumberUtils.stripSeparators(numeroTelefono) + "@s.whatsapp.net");
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Me vende un heladito?");
        startActivity(sendIntent);
    }

    public void SendMessage2(View view)
    {
        String telefono = "67550712";
        Intent _intencion = new Intent(Intent.ACTION_SEND);
        _intencion.setComponent(new ComponentName("com.whatsapp","com.whatsapp.Conversation"));
        _intencion.setType("text/plain");
        _intencion.putExtra("jid", PhoneNumberUtils.stripSeparators("591" + telefono)+"@s.whatsapp.net");
        _intencion.putExtra(Intent.EXTRA_TEXT, "Me vende un heladito?");
        startActivity(_intencion);
    }




}