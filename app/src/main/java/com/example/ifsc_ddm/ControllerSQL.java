package com.example.ifsc_ddm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class ControllerSQL {

    private DAO_SQL daoSql;
    private SQLiteDatabase bancoDeDados;

    public ControllerSQL(Context contexto) {
        bancoDeDados = contexto.openOrCreateDatabase("banco.db", Context.MODE_PRIVATE, null);
        daoSql = new DAO_SQL(bancoDeDados);
        daoSql.criarTabela();
    }

    public void salvarNota(String textoDaNota) {
        if (textoDaNota != null && !textoDaNota.trim().isEmpty()) {
            Nota novaNota = new Nota(textoDaNota, "");
            daoSql.inserir(novaNota);
        }
    }

    public void limparNotas() {
        daoSql.deletarTodas();
    }

    public ArrayList<String> listarTitulos() {
        return daoSql.buscarTitulos();
    }

    public ArrayList<Nota> listarTodasNotas() {
        return daoSql.buscarTodas();
    }
}