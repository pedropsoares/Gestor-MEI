package com.example.gestormei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes extends AppCompatActivity {

    InserirClientes inserirClientes;
    private ListView listView;
    private  ClienteDados dao;
    private List<Cliente> clientes;
    private  List<Cliente> clientesFiltrados = new ArrayList<>();
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);


        listView = findViewById(R.id.listarClientes);
        dao = new ClienteDados(this);
        clientes = dao.ObterTodos();
        clientesFiltrados.addAll(clientes);
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);
        listView.setAdapter(adapter);


        button = (Button) findViewById(R.id.btnCadastrar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaClientes.this, InserirClientes.class);
                startActivity(intent);



            }
        });
    }
}
