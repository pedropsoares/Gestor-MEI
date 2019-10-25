package com.example.gestormei;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Melo on 19/12/2017.
 */

public class ClienteDados extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "GestorMEI.db";
    private static final int DATABASE_VERSION = 4;

    public static final String CLIENTE_TABELA_NOME = "cliente";
    public static final String CLIENTE_COLUNA_CODIGO = "codigo";
    public static final String CLIENTE_COLUNA_NOME = "nome";
    public static final String CLIENTE_COLUNA_EMAIL = "email";
    public static final String CLIENTE_COLUNA_CELULAR = "celular";
    public static final String CLIENTE_COLUNA_CEP = "cep";
    public static final String CLIENTE_COLUNA_NUMERO = "numero";

    public ClienteDados(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        onCreate(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "CREATE TABLE IF NOT EXISTS " + CLIENTE_TABELA_NOME +
                        "(" + CLIENTE_COLUNA_CODIGO + " INTEGER  PRIMARY KEY , " +
                        CLIENTE_COLUNA_NOME + " TEXT, " +
                        CLIENTE_COLUNA_EMAIL + " TEXT, " +
                        CLIENTE_COLUNA_CELULAR + " TEXT, " +
                        CLIENTE_COLUNA_CEP + " TEXT, " +
                        CLIENTE_COLUNA_NUMERO + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CLIENTE_TABELA_NOME);
        onCreate(db);
    }


    public ArrayList<Cliente> select() {
        ArrayList<Cliente> retorno = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CLIENTE_TABELA_NOME, null);
        if (res.moveToFirst()) {
            do {
                Cliente a = new Cliente();
                a.codigo = res.getInt(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_CODIGO));
                a.nome = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_NOME));
                a.email = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_EMAIL));
                a.celular = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_CELULAR));
                a.cep = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_CEP));
                a.numero = res.getString(res.getColumnIndex(ClienteDados.CLIENTE_COLUNA_CODIGO));

                retorno.add(a);
            } while (res.moveToNext());
        }
        return retorno;
    }

    /* public Aluno getAluno(int id) {
         SQLiteDatabase db = this.getReadableDatabase();
         Cursor res =  db.rawQuery(String.format("SELECT * FROM %s WHERE %s = ? ", ALUNO_TABELA_NOME, ALUNO_COLUNA_MATRICULA), new String[]{Integer.toString(id)});
         Aluno retorno = new Aluno();
         if (res.moveToFirst()) {
             do {

                 retorno.matricula = res.getInt(res.getColumnIndex(ClienteDados.ALUNO_COLUNA_MATRICULA));
                 retorno.nome = res.getString(res.getColumnIndex(ClienteDados.ALUNO_COLUNA_NOME));
                 retorno.cpf = res.getString(res.getColumnIndex(ClienteDados.ALUNO_COLUNA_CPF));
                 retorno.relevancia = res.getFloat(res.getColumnIndex(ClienteDados.ALUNO_COLUNA_RELEVANCIA));


             } while (res.moveToNext());
         }
         return retorno;
     }*/
    public boolean insert(Cliente a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(CLIENTE_COLUNA_NOME, a.nome);
        contentValues.put(CLIENTE_COLUNA_EMAIL, a.email);
        contentValues.put(CLIENTE_COLUNA_CELULAR, a.celular);
        contentValues.put(CLIENTE_COLUNA_CEP, a.cep);
        contentValues.put(CLIENTE_COLUNA_NUMERO, a.numero);

        db.insertOrThrow(CLIENTE_TABELA_NOME, null, contentValues);
        return true;
    }
    /*public boolean update(Aluno a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ALUNO_COLUNA_NOME, a.nome);
        contentValues.put(ALUNO_COLUNA_CPF, a.cpf);
        contentValues.put(ALUNO_COLUNA_RELEVANCIA, a.relevancia);
        db.update(ALUNO_TABELA_NOME, contentValues, ALUNO_COLUNA_MATRICULA + " = ? ", new String[] { Integer.toString(a.matricula)} );
        return true;
    }
    public Integer delete(Aluno a) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ALUNO_TABELA_NOME,
                ALUNO_COLUNA_MATRICULA + " = ? ",
                new String[] { Integer.toString(a.matricula) });
    }
    public Integer deleteAllPerson() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ALUNO_TABELA_NOME,
                ALUNO_COLUNA_MATRICULA + " > ? ",
                new String[] { Integer.toString(0) });
    }*/


}
