package com.example.melo.crud_sql_lite_prof_melo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Melo on 19/12/2017.
 */

public class AlunoDados extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "TeAmoProfessorMelo.db";
    private static final int DATABASE_VERSION = 2;

    public static final String ALUNO_TABELA_NOME = "aluno";
    public static final String ALUNO_COLUNA_MATRICULA = "matricula";
    public static final String ALUNO_COLUNA_NOME = "nome";
    public static final String ALUNO_COLUNA_CPF = "cpf";
    public static final String ALUNO_COLUNA_RELEVANCIA = "relevancia";

    public AlunoDados(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + ALUNO_TABELA_NOME +
                        "(" + ALUNO_COLUNA_MATRICULA + " INTEGER PRIMARY KEY, " +
                        ALUNO_COLUNA_NOME + " TEXT, " +
                        ALUNO_COLUNA_CPF + " TEXT, " +
                        ALUNO_COLUNA_RELEVANCIA + " FLOAT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ALUNO_TABELA_NOME);
        onCreate(db);
    }

    
    public ArrayList<Aluno> select() {
        ArrayList<Aluno> retorno = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + ALUNO_TABELA_NOME, null );
        if (res.moveToFirst()) {
            do {
                Aluno a = new Aluno();
                a.matricula = res.getInt(res.getColumnIndex(AlunoDados.ALUNO_COLUNA_MATRICULA));
                a.nome = res.getString(res.getColumnIndex(AlunoDados.ALUNO_COLUNA_NOME));
                a.cpf = res.getString(res.getColumnIndex(AlunoDados.ALUNO_COLUNA_CPF));
                a.relevancia = res.getFloat(res.getColumnIndex(AlunoDados.ALUNO_COLUNA_RELEVANCIA));

                retorno.add(a);
            } while (res.moveToNext());
        }
        return retorno;
    }
    public Aluno getAluno(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery(String.format("SELECT * FROM %s WHERE %s = ? ", ALUNO_TABELA_NOME, ALUNO_COLUNA_MATRICULA), new String[]{Integer.toString(id)});
        Aluno retorno = new Aluno();
        if (res.moveToFirst()) {
            do {

                retorno.matricula = res.getInt(res.getColumnIndex(AlunoDados.ALUNO_COLUNA_MATRICULA));
                retorno.nome = res.getString(res.getColumnIndex(AlunoDados.ALUNO_COLUNA_NOME));
                retorno.cpf = res.getString(res.getColumnIndex(AlunoDados.ALUNO_COLUNA_CPF));
                retorno.relevancia = res.getFloat(res.getColumnIndex(AlunoDados.ALUNO_COLUNA_RELEVANCIA));


            } while (res.moveToNext());
        }
        return retorno;
    }
    public boolean insert(Aluno a) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(ALUNO_COLUNA_NOME, a.nome);
        contentValues.put(ALUNO_COLUNA_CPF, a.cpf);
        contentValues.put(ALUNO_COLUNA_RELEVANCIA, a.relevancia);

        db.insert(ALUNO_TABELA_NOME, null, contentValues);
        return true;
    }
    public boolean update(Aluno a) {
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
    }


}
