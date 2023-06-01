package com.example.menurestaurante;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase db;
    private Cursor cursor;
    private ListView listView;
    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializa o banco de dados
        MenuDatabaseHelper dbHelper = new MenuDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        // Obtém os dados do banco de dados
        cursor = db.rawQuery("SELECT * FROM menu", null);

        // Verifica se existem dados disponíveis
        if (cursor.getCount() == 0) {
            // Dados não estão disponíveis, exibe mensagem
            Toast.makeText(this, "Dados indisponíveis", Toast.LENGTH_SHORT).show();
        }

        // Configura o adapter para exibir os dados no ListView
        listView = findViewById(R.id.listView);
        adapter = new SimpleCursorAdapter(this,
                R.layout.menu_item,
                cursor,
                new String[]{"nome", "descricao", "preco", "gluten", "calorias"},
                new int[]{R.id.nome, R.id.descricao, R.id.preco, R.id.gluten, R.id.calorias},
                0);
        listView.setAdapter(adapter);
    }

    // Classe auxiliar para criar e atualizar o banco de dados
    private static class MenuDatabaseHelper extends SQLiteOpenHelper {

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
}
