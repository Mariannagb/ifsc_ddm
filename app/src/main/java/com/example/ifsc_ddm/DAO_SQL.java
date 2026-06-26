package com.example.ifsc_ddm;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DAO_SQL {
    private SQLiteDatabase db;

    public DAO_SQL(SQLiteDatabase db) {
        this.db = db;
    }

    public void criarTabela() {
        db.execSQL("CREATE TABLE IF NOT EXISTS notas (id INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, txt TEXT)");
    }

    public void inserir(Nota nota) {
        db.execSQL("INSERT INTO notas (titulo, txt) VALUES ('" + nota.getTitulo() + "', '" + nota.getTexto() + "')");
    }

    public void deletarTodas() {
        db.execSQL("DELETE FROM notas");
    }

    public ArrayList<Nota> buscarTodas() {
        ArrayList<Nota> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM notas", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String titulo = cursor.getString(1);
                String texto = cursor.getString(2);
                lista.add(new Nota(id, titulo, texto));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public ArrayList<String> buscarTitulos() {
        ArrayList<String> lista = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT titulo FROM notas", null);

        if (cursor.moveToFirst()) {
            do {
                lista.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }
}