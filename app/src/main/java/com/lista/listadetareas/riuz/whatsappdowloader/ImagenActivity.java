package com.lista.listadetareas.riuz.whatsappdowloader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

import com.zolad.zoominimageview.ZoomInImageView;

import java.net.URI;
import java.net.URISyntaxException;

public class ImagenActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ZoomInImageView zoom;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

        Bundle bundle = getIntent().getExtras();
        zoom = findViewById(R.id.Imagen);

        toolbar = findViewById(R.id.tool);
        toolbar.setTitle(bundle.getString("name" , "one").toString());
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext() , "Onka" , Toast.LENGTH_LONG).show();
            }
        });
        Toast.makeText(this , bundle.getString("ruteName") , Toast.LENGTH_LONG).show();

        Bitmap bitmap = BitmapFactory.decodeFile(bundle.getString("ruteName"));
        zoom.setImageBitmap(bitmap);
    }


}
