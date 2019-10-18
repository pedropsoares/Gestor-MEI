package com.example.gestormei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ClienteDados extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "GestorMEI.db";
    private static final int DATABASE_VERSION = 2;

    public static final String CLIENTE_TABELA_NOME = "cliente";
    public static final String CLIENTE_COLUNA_NOME= "nome";
    public static final String CLIENTE_COLUNA_EMAIL = "email";
    public static final String CLIENTE_COLUNA_CELULAR = "celular";
    public static final String CLIENTE_COLUNA_CEP = "cep";
    public static final String CLIENTE_COLUNA_NUMERO = "numero";


    public ClienteDados(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + CLIENTE_TABELA_NOME +
                "(" + CLIENTE_COLUNA_NOME + "TEXT," +
                CLIENTE_COLUNA_EMAIL + " TEXT, " +
                CLIENTE_COLUNA_CELULAR + " TEXT, " +
                CLIENTE_COLUNA_CEP + " TEXT," +
                CLIENTE_COLUNA_NUMERO + " TEXT)"

        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CLIENTE_TABELA_NOME);
        onCreate(db);
    }

    public boolean insert (Cliente cli) {

        SQLiteDatabase banco = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(ClienteDados.CLIENTE_COLUNA_NOME, cli.nome);
        values.put(ClienteDados.CLIENTE_COLUNA_EMAIL, cli.email);
        values.put(ClienteDados.CLIENTE_COLUNA_CELULAR, cli.celular);
        values.put(ClienteDados.CLIENTE_COLUNA_CEP, cli.cep);
        values.put(ClienteDados.CLIENTE_COLUNA_NUMERO, cli.numero);

        banco.insert(CLIENTE_TABELA_NOME, null, values);

        return true;
    }
    public ArrayList<Cliente> ObterTodos() {
        ArrayList<Cliente> retorno = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + CLIENTE_TABELA_NOME, null );
        if (res.moveToFirst()) {
            do {
                Cliente cli = new Cliente();

                cli.nome = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_NOME));
                cli.email = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_EMAIL));
                cli.celular = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_CELULAR));
                cli.cep = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_CEP));
                cli.numero = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_NUMERO));

                retorno.add(cli);
            } while (res.moveToNext());
        }
        return retorno;
    }
}
