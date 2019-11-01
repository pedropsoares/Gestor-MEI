package com.example.gestormei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;


public class ClienteDados {
    private Conexao conexao;
    private SQLiteDatabase banco;

    public ClienteDados (Context context){/*preciso do context para criar a conexão*/
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();/*iniciar o banco e escrever com a conexão*/
    }

    public long insert(Cliente cli){
        ContentValues values = new ContentValues();  /*criar objeto value com os valores que serão inseridos na tabela aluno*/
        values.put("nome", cli.getNome());  /*o value nome irá receber o valor q será inserido na variável nome o mesmo ocorre abaixo*/
        values.put("email", cli.getEmail());
        values.put("celular", cli.getCelular());
        values.put("cep", cli.getCep());
        values.put("numero", cli.getNumero());
        return banco.insert("cliente", null, values);/*retrun para retornar o método inserir*/
    }

    public List<Cliente> obterTodos(){/*consultar no banco*/
        List<Cliente> clientes = new ArrayList<>();
        Cursor cursor = banco.query("cliente", new String[]{"codigo", "nome", "email", "celular", "cep", "numero"},
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Cliente cli = new Cliente();
            cli.setCodigo(cursor.getInt(0));
            cli.setNome(cursor.getString(1));
            cli.setEmail(cursor.getString(2));
            cli.setCelular(cursor.getString(3));
            cli.setCep(cursor.getString(4)) ;
            cli.setNumero(cursor.getString(5));
            clientes.add(cli);
        }
        return clientes;
    }

    public void excluir(Cliente cli){
        banco.delete("cliente","codigo = ?", new String[]{String.valueOf(cli.getCodigo())});
    }

    public void atualizar(Cliente cli){
        ContentValues values = new ContentValues();  /*criar objeto value com os valores que serão inseridos na tabela aluno*/
        values.put("nome", cli.getNome());  /*o value nome irá receber o valor q será inserido na variável nome o mesmo ocorre abaixo*/
        values.put("email", cli.getEmail());
        values.put("celular", cli.getCelular());
        values.put("cep", cli.getCep());
        values.put("numero", cli.getNumero());
        banco.update("cliente", values, "codigo = ?", new String[]{String.valueOf(cli.getCodigo())});
    }
}
