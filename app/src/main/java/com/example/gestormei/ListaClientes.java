package com.example.gestormei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes extends AppCompatActivity {

    // InserirClientes inserirClientes;

    private ListView listView;
    private ClienteListAdapter01 adapter01;
    private ClienteDados dao;
    private List<Cliente> mClienteList;
    private Button buttonCadastrar, btnListarClientes;
    private List<Cliente> clientesFiltrados = new ArrayList<>();


    private void listarClientes() {

        listView = findViewById(R.id.listarClientes);
        mClienteList = new ArrayList<>();

        dao = new ClienteDados(ListaClientes.this);
        mClienteList = dao.select();
        clientesFiltrados.addAll(mClienteList);
        //(ListaClientes.this, android.R.layout.simple_list_item_1, mClienteList);
        adapter01 = new ClienteListAdapter01(getApplicationContext(), mClienteList);
        listView.setAdapter(adapter01);
        //listView.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);


        buttonCadastrar = (Button) findViewById(R.id.btnCadastrar);
        // btnListarClientes = (Button) findViewById(R.id.btnPesquisar);


        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaClientes.this, InserirClientes.class);
                startActivity(intent);


            }
        });
      /*  btnListarClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarClientes();
            }
        });*/

    }

    @Override
    protected void onResume() {
        super.onResume();
        listarClientes();
    }
}
