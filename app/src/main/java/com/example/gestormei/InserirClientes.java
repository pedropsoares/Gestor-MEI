package com.example.gestormei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class InserirClientes extends AppCompatActivity {
    private ClienteDados dao;
    private Cliente cli = null;
    private EditText edtNome, edtEmail, edtCelular, edtCep, edtN;
    private Button BtnInf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_clientes);

        edtNome = findViewById(R.id.editTextNome);
        edtEmail = findViewById(R.id.editTextEmail);
        edtCelular = findViewById(R.id.editTextcelular);
        edtCep = findViewById(R.id.editTextCep);
        edtN = findViewById(R.id.editTextNumero);
        dao = new ClienteDados(InserirClientes.this);
        BtnInf = findViewById(R.id.btn1);
        final Button button = findViewById(R.id.btn1);

        Intent intent = getIntent();
        if (intent.hasExtra("cliente")) {
            //cli = (Cliente) intent.getSerializableExtra("cliente");
            cli = intent.getExtras().getParcelable("cliente");
            edtNome.setText(cli.getNome());
            edtEmail.setText(cli.getEmail());
            edtCelular.setText(cli.getCelular());
            edtCep.setText(cli.getCep());
            edtN.setText(cli.getNumero());
        }
    }

    public void salvar(View view) {/*ACRESCENTAR OS CONTROLES - CEP E CELULAR SÓ ACEITAS Nº'S, ETC */

        FirebaseApp.initializeApp(InserirClientes.this);
        FirebaseDatabase bd = FirebaseDatabase.getInstance();
        DatabaseReference bdRef = bd.getReference();

        if (cli == null) {//salva o aluno no banco
            cli = new Cliente(); /*objeto aluno*/
            cli.setNome(edtNome.getText().toString());/*preencher com os campos do edit nome, cpf, etc.....*/
            cli.setEmail(edtEmail.getText().toString());
            cli.setCelular(edtCelular.getText().toString());
            cli.setCep(edtCep.getText().toString());
            cli.setNumero(edtN.getText().toString());
            bdRef.child("clientes").push().setValue(cli);
            long id = dao.insert(cli);/*salvar o aluno e seus dados*/
            Toast.makeText(InserirClientes.this, "Cliente salvo com o código: " + id, Toast.LENGTH_LONG).show();
            limparFocu();
        } else {
            cli.setNome(edtNome.getText().toString());/*preencher com os campos do edit nome, cpf, etc.....*/
            cli.setEmail(edtEmail.getText().toString());
            cli.setCelular(edtCelular.getText().toString());
            cli.setCep(edtCep.getText().toString());
            cli.setNumero(edtN.getText().toString());
            dao.atualizar(cli);
            bdRef.child("clientes").child(cli.codFireBase).setValue(cli);
            Toast.makeText(InserirClientes.this, "Atualização 'OK'", Toast.LENGTH_LONG).show();
            limparFocu();
        }

        Intent intent = new Intent(InserirClientes.this, DetalhesClientes.class);
        intent.putExtra("informacoes", cli);
        startActivity(intent);
    }

    private void limparFocu() {
        edtNome.setText("");
        edtEmail.setText("");
        edtCelular.setText("");
        edtCep.setText("");
        edtN.setText("");
    }
}