package com.example.farmaciagrupoa;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.farmaciagrupoa.DbHelper.ConexaoSQLite;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //CHAMA O MÉTODO ESTÁTICO CONEXAOSQLITE
        ConexaoSQLite.getInstancia(this);

        ImageView right_icon = findViewById(R.id.menu_icon);

        right_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( MainActivity.this, "MENU", Toast.LENGTH_SHORT).show();
                showMenu(v);
            }
        });
    }

    private void showMenu(View v){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this,v);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if(item.getItemId() == R.id.cadastrar){
                    Toast.makeText( MainActivity.this, "Cadastrar Produto", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, ActivityProduto.class);
                    startActivity(intent);
                    showMenu(v);
                }

                if(item.getItemId() == R.id.consultar){
                    Toast.makeText( MainActivity.this, "Consultar Produto", Toast.LENGTH_SHORT).show();

                    //ADICIONADO 08/11 14:43

                    Intent intent = new Intent(MainActivity.this, ListarProdutosActivity.class);
                    startActivity(intent);
                    showMenu(v);
                }

                if(item.getItemId() == R.id.sair){
                    Toast.makeText( MainActivity.this, "Até Mais!", Toast.LENGTH_SHORT).show();
                    finish();
                }
                return true;
            }
        });
        popupMenu.show();
    }
}