package com.example.menurestaurante;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MenuDatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "menu.db";
    private static final int DB_VERSION = 1;

    MenuDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Cria a tabela menu
        db.execSQL("CREATE TABLE menu (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, descricao TEXT, preco REAL, gluten TEXT, calorias INTEGER);");

        // Insere alguns exemplos de dados no banco de dados
        db.execSQL("INSERT INTO menu (nome, descricao, preco, gluten, calorias) " +
                "VALUES ('Item 1', 'Descrição do Item 1', 10.0, 'Sim', 200);");
        db.execSQL("INSERT INTO menu (nome, descricao, preco, gluten, calorias) " +
                "VALUES ('Item 2', 'Descrição do Item 2', 15.0, 'Não', 150);");
        db.execSQL("INSERT INTO menu (nome, descricao, preco, gluten, calorias) " +
                "VALUES ('Item 3', 'Descrição do Item 3', 20.0, 'Não', 180);");
        db.execSQL("INSERT INTO menu (nome, descricao, preco, gluten, calorias) " +
                "VALUES ('Item 4', 'Descrição do Item 4', 12.0, 'Sim', 220);");
        db.execSQL("INSERT INTO menu (nome, descricao, preco, gluten, calorias) " +
                "VALUES ('Item 5', 'Descrição do Item 5', 18.0, 'Sim', 190);");
        db.execSQL("INSERT INTO menu (nome, descricao, preco, gluten, calorias) " +
                "VALUES ('Item 6', 'Descrição do Item 6', 14.0, 'Não', 160);");
        db.execSQL("INSERT INTO menu (nome, descricao, preco, gluten, calorias) " +
                "VALUES ('Item 7', 'Descrição do Item 7', 22.0, 'Sim', 250);");
        db.execSQL("INSERT INTO menu (nome, descricao, preco, gluten, calorias) " +
                "VALUES ('Item 8', 'Descrição do Item 8', 16.0, 'Não', 170);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Atualizações futuras do banco de dados
    }
}
