package com.example.gestormei;


import android.os.Parcel;
import android.os.Parcelable;

public class Cliente implements Parcelable {

    public int codigo;
    public String nome;
    public String email;
    public String celular;
    public String cep;
    public String numero;


    public Cliente(String nome, String email, String celular, String cep, String numero/*int codigo*/) {

        this.codigo = codigo;
        this.nome = nome;
        this.email = email;
        this.celular = celular;
        this.cep = cep;
        this.numero = numero;


    }

    protected Cliente(Parcel in) {
        this.codigo =in.readInt();
        this.nome = in.readString();
        this.email = in.readString();
        this.celular = in.readString();
        this.cep = in.readString();
        this.numero = in.readString();


    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel source) {
            return new Cliente(source);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public Cliente() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(codigo);
        dest.writeString(nome);
        dest.writeString(email);
        dest.writeString(celular);
        dest.writeString(cep);
        dest.writeString(numero);


    }

   /* @Override
    public String toString(){
        return nome;
    }*/
}



