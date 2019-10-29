package com.example.gestormei;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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

       try {

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (cli == null) {//salva o aluno no banco
                        cli = new Cliente(); /*objeto aluno*/
                        cli.setNome(edtNome.getText().toString());/*preencher com os campos do edit nome, cpf, etc.....*/
                        cli.setEmail(edtEmail.getText().toString());
                        cli.setCelular(edtCelular.getText().toString());
                        cli.setCep(edtCep.getText().toString());
                        cli.setNumero(edtN.getText().toString());
                        long id = dao.insert(cli);/*salvar o aluno e seus dados*/
                        Toast.makeText(InserirClientes.this, "cliente salvo com o código: " + id, Toast.LENGTH_LONG).show();
                        limparFocu();
                    } else {
                        cli.setNome(edtNome.getText().toString());/*preencher com os campos do edit nome, cpf, etc.....*/
                        cli.setEmail(edtEmail.getText().toString());
                        cli.setCelular(edtCelular.getText().toString());
                        cli.setCep(edtCep.getText().toString());
                        cli.setNumero(edtN.getText().toString());
                        dao.atualizar(cli);
                        Toast.makeText(InserirClientes.this, "Atualização 'OK'", Toast.LENGTH_LONG).show();
                    }

                    /*Toast.makeText(InserirClientes.this, "Informações enviadas com Sucesso", Toast.LENGTH_LONG).show();
                    cli = new Cliente(edtNome.getText().toString(), edtEmail.getText().toString(), edtCelular.getText().toString(), edtCep.getText().toString(), edtN.getText().toString());
                    dao.insert(cli);*/

                    Intent intent = new Intent(InserirClientes.this, DetalhesClientes.class);
                    intent.putExtra("informacoes", cli);
                    startActivity(intent);
                }
            });
        } catch (NullPointerException e) {
            System.out.println(e);
        }


    }

    /*public void salvar(View view) {
         }*/

    private void limparFocu() {
        edtNome.setText("");
        edtEmail.setText("");
        edtCelular.setText("");
        edtCep.setText("");
        edtN.setText("");
    }


}






