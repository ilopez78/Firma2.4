package com.example.firma24;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.firma24.clases.CaptureImageView;
import com.example.firma24.clases.ConexionSQLite;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;



public class MainActivity extends AppCompatActivity {
    private CaptureImageView fir;
    private EditText editText;
    private Button button,btnlimpiar;
    ConexionSQLite dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.guardar);
        btnlimpiar=findViewById(R.id.limpiar);

        editText=findViewById(R.id.descripcion);
        LinearLayout mContent = (LinearLayout) findViewById(R.id.view);
        fir = new CaptureImageView(this, null);
        mContent.addView(fir, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        dbHelper = new ConexionSQLite(this);

        button.setOnClickListener(view -> {
            Bitmap img=  fir.getBitmap();

            if(validarCampos()){
                boolean estate=dbHelper.insertSQL(editText.getText().toString(),
                        setFrom(img));
                if(estate){
                    Toast.makeText(this, "Firma de "+editText.getText().toString()+" agregada", Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }
        });
        btnlimpiar.setOnClickListener(view -> {
            limpiar();
        });
    }
    public void limpiar(){
        fir.ClearCanvas();
        editText.setText("");
    }
    public boolean validarCampos(){
        boolean estado=true;
        if(editText.getText().toString().isEmpty() ){
            Toast.makeText(this, "Campo vacio", Toast.LENGTH_SHORT).show();
            estado=false;
            return estado;
        }

        if(!fir.isStatus()){
            Toast.makeText(this, "Por favor cree una firma", Toast.LENGTH_SHORT).show();
            estado=false;
            return estado;
        }
        return estado;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public static byte[] setFrom(Bitmap imagen){
        if(imagen!=null){

            ByteArrayOutputStream stream =new ByteArrayOutputStream();
            imagen.compress(Bitmap.CompressFormat.JPEG,70,stream);
            return stream.toByteArray();
        }
        return null;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        if (item.getItemId()==R.id.lista_item){
            Intent intent=new Intent(this,List_Firmas.class);

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}