package com.example.farmaciagrupoa.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.farmaciagrupoa.DbHelper.ConexaoSQLite;
import com.example.farmaciagrupoa.Produto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private final ConexaoSQLite conexaoSQLite;

    public ProdutoDAO(ConexaoSQLite conexaoSQLite) {
        this.conexaoSQLite = conexaoSQLite;
    }

    //MÉTODO PARA SALVAR O PRODUTO NO BANCO DE DADOS:

    public long salvarProdutoDAO(Produto pProduto){

        SQLiteDatabase db = conexaoSQLite.getWritableDatabase();

        try {
            ContentValues values = new ContentValues();
            values.put("id", pProduto.getId());
            values.put("nome", pProduto.getNome());
            values.put("qtd_estoque", pProduto.getQtdEstoque());
            values.put("preco", pProduto.getPreco());

            long idProdutoInserido = db.insert("produtos", null, values);

            return idProdutoInserido;

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(db != null){
                db.close();
            }
        }
        return 0;
    }

    //ADICIONADO 10/11:

    public List<Produto> getListaProdutosDAO(){
        List<Produto> listaProdutos = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor;

        //String query = "SELECT * FROM produtos WHERE nome LIKE '%?%';";
        String query = "SELECT * FROM produtos;";

        try{

            db = this.conexaoSQLite.getReadableDatabase();

            cursor = db.rawQuery(query, null);

            //TESTA SE TEM REGISTRO NA TABELA:

            if (cursor.moveToFirst()){

                Produto produto = null;

                do{
                    produto = new Produto();
                    produto.setId(cursor.getLong(0));
                    produto.setNome(cursor.getString(1));
                    produto.setQtdEstoque(cursor.getInt(2));
                    produto.setPreco(cursor.getDouble(3));

                    listaProdutos.add(produto);

                }while (cursor.moveToNext());
            }
        }catch (Exception e){
            Log.d("ERRO LISTA PRODUTOS", "ERRO AO CUNSULTAR");
            return null;
        }finally {
            if (db != null){
                db.close();
            }
        }
        return listaProdutos;
    }
}
