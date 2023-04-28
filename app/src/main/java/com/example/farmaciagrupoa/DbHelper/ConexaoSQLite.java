package com.example.farmaciagrupoa.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexaoSQLite extends SQLiteOpenHelper {

    private static ConexaoSQLite INSTANCIA_CONEXAO;
    private static final int VERSAO_DB = 1;
    private static final String NOME_DB = "bl_produtos_app";

    public ConexaoSQLite(@Nullable Context context) {
        super(context, NOME_DB, null, VERSAO_DB);
    }

    //SE A INSTÂNCIA FOR NULA, SERÁ INSTANCIADA, CASO CONTRÁRIO, RETORNA A EXISTENTE:
    public static ConexaoSQLite getInstancia(Context context){
        if(INSTANCIA_CONEXAO == null){
            INSTANCIA_CONEXAO = new ConexaoSQLite(context);
        }
        return INSTANCIA_CONEXAO;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTabelaProduto =
                "CREATE TABLE IF NOT EXISTS produtos" +
                        "(" +
                        "id INTEGER PRIMARY KEY," +
                        "nome TEXT," +
                        "qtd_estoque INTEGER," +
                        "preco REAL" +
                        ")";
        sqLiteDatabase.execSQL(sqlTabelaProduto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
