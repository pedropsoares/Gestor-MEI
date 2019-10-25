package com.example.gestormei;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

public class ClienteListAdapter01 extends BaseAdapter {

    private Context mContext;
    private List<Cliente> mClienteList;

    public ClienteListAdapter01(Context mContext, List<Cliente> mClienteList) {
        this.mContext = mContext;
        this.mClienteList = mClienteList;
    }

    @Override
    public int getCount() {
        return mClienteList.size();
    }

    @Override
    public Object getItem(int position) {
        return mClienteList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.activity_lista_clientes, null);
        ListView listView = v.findViewById(R.id.listarClientes);
        listView.setAdapter((ListAdapter) mClienteList.get(position));
        v.setTag(mClienteList.get(position));
        return v;
    }
}