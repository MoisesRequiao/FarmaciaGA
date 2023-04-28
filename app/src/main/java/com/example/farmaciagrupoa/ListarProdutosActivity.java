package com.example.farmaciagrupoa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.farmaciagrupoa.Adapters.AdapterListaProdutos;
import com.example.farmaciagrupoa.Controller.ProdutoCtrl;
import com.example.farmaciagrupoa.DbHelper.ConexaoSQLite;

import java.util.ArrayList;
import java.util.List;

public class ListarProdutosActivity extends AppCompatActivity {

    private ListView lsvProdutos;
    private List<Produto>produtoList;
    private AdapterListaProdutos adapterListaProdutos;
    private SearchView sv_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_listar_produtos);

        ProdutoCtrl produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(ListarProdutosActivity.this));
        produtoList = produtoCtrl.getListaProdutosCTrl();

        this.lsvProdutos = (ListView) findViewById(R.id.lsvProdutos);

        this.adapterListaProdutos = new AdapterListaProdutos(ListarProdutosActivity.this, this.produtoList);

        this.lsvProdutos.setAdapter(this.adapterListaProdutos);

    }
}