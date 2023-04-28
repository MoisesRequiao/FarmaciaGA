package com.example.farmaciagrupoa.Controller;

import android.widget.SearchView;

import com.example.farmaciagrupoa.DAO.ProdutoDAO;
import com.example.farmaciagrupoa.DbHelper.ConexaoSQLite;
import com.example.farmaciagrupoa.Produto;

import java.util.List;

public class ProdutoCtrl {

    private final ProdutoDAO produtoDAO;

    public ProdutoCtrl(ConexaoSQLite pConexaoSQLite){
        produtoDAO = new ProdutoDAO(pConexaoSQLite);
    }

    public long salvarProdutoCtrl(Produto pProduto){
        return this.produtoDAO.salvarProdutoDAO(pProduto);
    }
    public List<Produto> getListaProdutosCTrl(){
        return this.produtoDAO.getListaProdutosDAO();
    }
}
