package com.example.gestormei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InserirClientes extends AppCompatActivity {
    ClienteDados dao;
    Cliente cli;
    EditText edtNome, edtEmail, edtCelular, edtCep, edtN;
    Button BtnInf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_clientes);


        dao = new ClienteDados(InserirClientes.this);

        edtNome = findViewById(R.id.editTextNome);
        edtEmail = findViewById(R.id.editTextEmail);
        edtCelular = findViewById(R.id.editTextcelular);
        edtCep = findViewById(R.id.editTextCep);
        edtN = findViewById(R.id.editTextNumero);
        BtnInf = findViewById(R.id.btn1);
        final Button button = findViewById(R.id.btn1);


        try {


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(InserirClientes.this, "Informações enviadas com Sucesso", Toast.LENGTH_LONG).show();
                    cli = new Cliente(edtNome.getText().toString(), edtEmail.getText().toString(), edtCelular.getText().toString(), edtCep.getText().toString(), edtN.getText().toString());
                    dao.insert(cli);

                    Intent intent = new Intent(InserirClientes.this, DetalhesClientes.class);
                    intent.putExtra("informacoes", cli);
                    startActivity(intent);
                }
            });
        } catch (NullPointerException e) {
            System.out.println(e);
        }

    }
}
