package com.example.gestormei;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListaClientes extends AppCompatActivity {

    private ListView listView;
    private ClienteDados dao;
    private List<Cliente> clientes;
    private List<Cliente> clientesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        listView = findViewById(R.id.listarClientes);
        dao = new ClienteDados(this);
        clientes = dao.obterTodos();
        clientesFiltrados.addAll(clientes);
        ArrayAdapter<Cliente> adaptador = new ArrayAdapter<Cliente>(this, android.R.layout.simple_list_item_1, clientesFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);

        FirebaseApp.initializeApp(ListaClientes.this);
        FirebaseDatabase bd = FirebaseDatabase.getInstance();
        DatabaseReference bdRef = bd.getReference();
        FirebaseDatabase.getInstance().getReference("cliente").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    clientesFiltrados = new ArrayList<>();
                    //listView.setAdapter(null);
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Cliente cli = snapshot.getValue(Cliente.class);
                        cli.codFireBase = snapshot.getKey();//Código Firebase
                        clientesFiltrados.add(cli);
                    }
                    ArrayAdapter<Cliente> adaptador = new ArrayAdapter<Cliente>(ListaClientes.this, android.R.layout.simple_list_item_1, clientesFiltrados);
                    listView.setAdapter(adaptador);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                procurarCliente(s);
                return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }

    public void procurarCliente(String nome) {//FILTRA POR LETRA DIGITADA
        clientesFiltrados.clear();
        for (Cliente cli : clientes) {
            if (cli.getNome().toLowerCase().contains(nome.toLowerCase())) {//TODOS EM MINÚSCULO
                clientesFiltrados.add(cli);
            }
        }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Cliente clienteExcluir = clientesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Deseja realmente excluir o cliente?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        //  clientesFiltrados.remove(clienteExcluir);
                        clientesFiltrados.remove(clienteExcluir);
                        dao.excluir(clienteExcluir);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Cliente clienteAtualizar = clientesFiltrados.get(menuInfo.position);
        Intent intent = new Intent(this, InserirClientes.class);
        intent.putExtra("cliente", clienteAtualizar);
        startActivity(intent);
//        Toast.makeText(getApplicationContext(), "*ATUALIZAR* está em desenvolvimento. Favor aguardar.",Toast.LENGTH_LONG).show();
    }

    public void detalhes(MenuItem item) {

        /*não está funcionando - o código está comentado no final desta página*/


    }

    public void cadastrar01(MenuItem item) {
        Intent intent = new Intent(this, InserirClientes.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        clientes = dao.obterTodos();
        clientesFiltrados.clear();
        clientesFiltrados.addAll(clientes);
        listView.invalidateViews();
    }
}


/*
public void detalhes(MenuItem item){

        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Cliente clienteAt = clientesFiltrados.get(menuInfo.position);
        Intent intent = new Intent(this, DetalhesClientes.class);
        intent.putExtra("cliente", clienteAt);
        startActivity(intent);

        /* Intent intent = new Intent(ListaClientes.this, DetalhesClientes.class);
        startActivity(intent); */
//  }
//*/