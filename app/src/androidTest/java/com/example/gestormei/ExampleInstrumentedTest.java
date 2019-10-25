package com.example.gestormei;

import android.content.Context;
import android.util.Log;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        try {
            Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Cliente cli = new Cliente();
            cli.codigo = 1;
            cli.nome = "Melo";
            cli.numero = "1";
            cli.celular = "111111";
            cli.email = "eu@eu";
            cli.cep = "34656";
            ClienteDados dados = new ClienteDados(appContext);
            dados.insert(cli);

            List<Cliente> clientes = dados.select();
            Log.d("","Cadastrou");
        }catch(Exception ex){
            Log.d("",ex.getMessage());
        }
    }
}
