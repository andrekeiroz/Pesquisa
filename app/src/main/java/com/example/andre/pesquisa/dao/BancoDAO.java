package com.example.andre.pesquisa.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andre on 28/05/2016.
 */
public class BancoDAO extends SQLiteOpenHelper{
    public BancoDAO(Context context) {
        super(context, "Banco", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Numero (id INTEGER PRIMARY KEY, number TEXT NOT NULL);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "";
        switch (oldVersion){
            case 1:
                sql = "ALTER TABLE Aluno ADD COLUMN caminhoFoto TEXT";
                db.execSQL(sql);
        }

    }

    public void insere(){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = new ContentValues();
        //dados.put("number", "um");
        //dados.put("number", "dois");
        //dados.put("number", "tres");

        db.insert("Numero", null, dados);

    }

    public List<String> busca(String number) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Numero WHERE number LIKE ?", new String[]{number});

        List<String> nums = new ArrayList<String>();
        while (c.moveToNext()) {
            nums.add(c.getString(c.getColumnIndex("number")));
        }
        c.close();

        return nums;
    }

}
