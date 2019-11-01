package com.example.gestormei;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetalhesClientes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_clientes);

        //TextView editCodigo = findViewById(R.id.editTextcodigo2);
        TextView editNome = findViewById(R.id.textViewNome2);
        TextView editEmail = findViewById(R.id.textViewEmail2);
        TextView editCelular = findViewById(R.id.textViewCelular2);
        TextView editCep = findViewById(R.id.textViewCep2);
        TextView editNumero = findViewById(R.id.textViewN2);


        Cliente cli = getIntent().getExtras().getParcelable("informacoes");

       // int codigo = cli.codigo;
        String nome = cli.nome;
        String email = cli.email;
        String celular = cli.celular;
        String cep = cli.cep;
        String numero = cli.numero;

       // editCodigo.setText(codigo);
        editNome.setText(nome);
        editEmail.setText(email);
        editCelular.setText(celular);
        editCep.setText(cep);
        editNumero.setText(numero);
    }
}
