package com.example.melo.crud_sql_lite_prof_melo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Melo on 19/12/2017.
 */

public class Aluno implements Parcelable {

    public int matricula;
    public String nome;
    public String cpf;
    public float relevancia;


    protected Aluno(Parcel in) {
        matricula = in.readInt();
        nome = in.readString();
        cpf = in.readString();
        relevancia = in.readFloat();
    }

    public static final Creator<Aluno> CREATOR = new Creator<Aluno>() {
        @Override
        public Aluno createFromParcel(Parcel in) {
            return new Aluno(in);
        }

        @Override
        public Aluno[] newArray(int size) {
            return new Aluno[size];
        }
    };

    public Aluno() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(matricula);
        parcel.writeString(nome);
        parcel.writeString(cpf);
        parcel.writeFloat(relevancia);
    }
}
