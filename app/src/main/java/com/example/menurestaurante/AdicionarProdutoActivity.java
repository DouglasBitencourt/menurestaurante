package com.example.menurestaurante;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdicionarProdutoActivity extends AppCompatActivity {

    private EditText etNome, etDescricao, etPreco, etGluten, etCalorias;
    private Button btnAdicionar;

    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_produto);

        // Inicializa o banco de dados
        MenuDatabaseHelper dbHelper = new MenuDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        // Referencia os elementos de UI
        etNome = findViewById(R.id.etNome);
        etDescricao = findViewById(R.id.etDescricao);
        etPreco = findViewById(R.id.etPreco);
        etGluten = findViewById(R.id.etGluten);
        etCalorias = findViewById(R.id.etCalorias);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        // Configura o clique do botão "Adicionar"
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarItemAoCardapio();
            }
        });
    }

    private void adicionarItemAoCardapio() {
        String nome = etNome.getText().toString();
        String descricao = etDescricao.getText().toString();
        double preco = Double.parseDouble(etPreco.getText().toString());
        String gluten = etGluten.getText().toString();
        int calorias = Integer.parseInt(etCalorias.getText().toString());

        // Cria um objeto ContentValues para inserir os valores no banco de dados
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("descricao", descricao);
        values.put("preco", preco);
        values.put("gluten", gluten);
        values.put("calorias", calorias);

        // Insere os dados na tabela do menu
        long resultado = db.insert("menu", null, values);

        if (resultado != -1) {
            Toast.makeText(this, "Item adicionado ao cardápio", Toast.LENGTH_SHORT).show();
            limparCampos();
        } else {
            Toast.makeText(this, "Falha ao adicionar item ao cardápio", Toast.LENGTH_SHORT).show();
        }
    }

    private void limparCampos() {
        etNome.setText("");
        etDescricao.setText("");
        etPreco.setText("");
        etGluten.setText("");
        etCalorias.setText("");
    }
}
