package com.example.gestormei;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Conexao extends SQLiteOpenHelper { /*SQLitOpenHelpr auxilia abrir o banco de dados, criar, atualizar, etc*/

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexao(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {/*ao abrir o banco é criado as tabelas abaixo*/

        db.execSQL("create table cliente(codigo integer primary key autoincrement, "+
                "nome varchar(50), email varchar(50), celular varchar(50), cep varchar(50),numero varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

//**************************************

