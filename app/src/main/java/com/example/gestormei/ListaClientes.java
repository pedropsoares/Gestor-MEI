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
    private ClienteDados dao;
    private List<Cliente> clientes;
    private List<Cliente> clientesFiltrados = new ArrayList<>();
    private Button buttonCadastrar, btnListarClientes;

    private void listarClientes(){
        dao = new ClienteDados(ListaClientes.this);
        clientes = dao.select();
        clientesFiltrados.addAll(clientes);
        ArrayAdapter<Cliente> adapter = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientes);
        listView.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);


        listView = findViewById(R.id.listarClientes);



        buttonCadastrar = (Button) findViewById(R.id.btnCadastrar);
        btnListarClientes = (Button) findViewById(R.id.btnPesquisar);


        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaClientes.this, InserirClientes.class);
                startActivity(intent);


            }
        });
        btnListarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarClientes();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.listarClientes();
    }
}
