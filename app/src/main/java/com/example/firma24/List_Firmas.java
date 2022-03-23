package com.example.firma24;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firma24.clases.Posicion;
import com.example.firma24.clases.Obtener;
import com.example.firma24.clases.ConexionSQLite;
import com.example.firma24.clases.Posicion;

import java.util.ArrayList;
import java.util.List;

public class List_Firmas extends AppCompatActivity {
    List<Obtener> listaItem= new ArrayList<>();
    RecyclerView recyclerViewItem;
    ConexionSQLite sql;
    Posicion adaptador;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Lista de firmas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sql=new ConexionSQLite(this);
        setContentView(R.layout.activity_list_firmas);
        listaItem=sql.getData();
        recyclerViewItem = findViewById(R.id.recycler);
        recyclerViewItem.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerViewItem.setLayoutManager(layoutManager);
        adaptador= new Posicion(this, listaItem, recyclerViewItem);
        recyclerViewItem.setAdapter(adaptador);
    }
}