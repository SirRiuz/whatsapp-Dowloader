package com.lista.listadetareas.riuz.whatsappdowloader;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AlertDialog;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.ActivityCompat;
        import androidx.core.content.ContextCompat;
        import android.Manifest;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.content.pm.PackageManager;
        import android.os.Bundle;
        import android.os.Environment;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;
        import android.widget.Toast;
        import java.io.File;
        import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listaEstados;
    private static ArrayList<String> arrayEstados = new ArrayList<>();
    private static ArrayList<String> arrayRutaFile = new ArrayList<>();

    private final String fotmatImg[] = new String[]{".jpg" , ".png"};

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            getEstadosList();
        }catch (Exception e){
            Toast.makeText(this , e.toString() , Toast.LENGTH_LONG).show();
            Log.e("Whapsap dowloader -> " , e.toString());
        }

        listaEstados = findViewById(R.id.ListView);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,R.layout.list_view_model ,arrayEstados);
        ActivityCompat.requestPermissions(this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , 1);
        listaEstados.setAdapter(adapter);
        listaEstados.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (String formato : fotmatImg) {
                    if (arrayEstados.get(position).endsWith(formato)) {
                        Intent intent = new Intent(MainActivity.this , ImagenActivity.class)
                                .putExtra("name" , arrayEstados.get(position))
                                .putExtra("ruteName" , arrayRutaFile.get(position));
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext() , "El fotmato tiene que ser de imagen" , Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    public void getEstadosList () throws Exception {
        File file = new File(Environment.getExternalStoragePublicDirectory("WhatsApp") + "/Media/.Statuses/");
        if (file.exists()){
            Toast.makeText(this , "Hecho" , Toast.LENGTH_LONG).show();
            File x[] = file.listFiles();

            for (File fileName : x){
                Toast.makeText(this , fileName.getName() , Toast.LENGTH_LONG).show();
                arrayEstados.add(fileName.getName());
                arrayRutaFile.add(fileName.toString());
            }

        }
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (ContextCompat.checkSelfPermission(getApplicationContext() , Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext() , "Permiso concedido" , Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext() , "Permiso no concedido ..." , Toast.LENGTH_LONG).show();
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this , R.style.DialogTheme)
                    .setCancelable(false)
                    .setMessage("Hey , es nesesario que actives el permiso , para que la app funcione correctamente")
                    .setPositiveButton("Pedir permisos", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this , new String[]{Manifest.permission.READ_EXTERNAL_STORAGE} , 1);
                        }
                    });

            dialog.show();
        }

    }
}
