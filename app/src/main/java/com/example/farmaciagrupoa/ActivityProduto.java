package com.example.farmaciagrupoa;

import static com.example.farmaciagrupoa.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmaciagrupoa.Controller.ProdutoCtrl;
import com.example.farmaciagrupoa.DbHelper.ConexaoSQLite;

public class MAActivityProduto extends AppCompatActivity {

    //CÓDIGO DE BARRAS DO PRODUTO
    private EditText edtIdProduto;
    private EditText edtNomeProduto;
    private EditText edtQuantidadeProduto;
    private EditText edtPrecoProduto;

    private Button btnSalvarProduto;

    //CRIA OBJETO PRODUTO PARA RECEBER OS DADOS:
    private Produto produto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_produto);

        edtIdProduto = (EditText) findViewById(id.edtIdProduto);
        edtNomeProduto = (EditText) findViewById(R.id.edtNomeProduto);
        edtQuantidadeProduto = (EditText) findViewById(id.edtQuantidadeProduto);
        edtPrecoProduto = (EditText) findViewById(id.edtPrecoProduto);

        btnSalvarProduto = (Button) findViewById(id.btnSalvarProduto);

        this.clickNoBotaoSalvarListener();
    }
    private void clickNoBotaoSalvarListener(){

        this.btnSalvarProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Produto produtoACadastrar = getDadosProdutoDoFormulario();

                if(produtoACadastrar != null){

                    //O CONTROLE SALVA O PRODUTO.
                    ProdutoCtrl produtoCtrl = new ProdutoCtrl(ConexaoSQLite.getInstancia(ActivityProduto.this));
                    long idProduto = produtoCtrl.salvarProdutoCtrl(produtoACadastrar);

                    if(idProduto > 0){
                        Toast.makeText(ActivityProduto.this, "PRODUTO SALVO COM SUCESSO!", Toast.LENGTH_LONG).show();
                        System.out.println("PRODUTO CADASTRADO: " + produto); //IMPRIMI O PRODUTO EM TELA.

                        //LIMPA OS CAMPOS DE TEXTO APÓS SALVAR.
                        edtIdProduto.setText("");
                        edtNomeProduto.setText("");
                        edtPrecoProduto.setText("");
                        edtQuantidadeProduto.setText("");

                    }else{
                        Toast.makeText(ActivityProduto.this, "ATENÇÃO! ERRO AO SALVAR PRODUTO", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(ActivityProduto.this, "PREENCHA TODOS OS CAMPOS!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    //MÉTODO QUE RECEBE OS DADOS DO PRODUTO:

    private Produto getDadosProdutoDoFormulario(){

        this.produto = new Produto();

        if(this.edtIdProduto.getText().toString().isEmpty() == false){
            this.produto.setId(Long.parseLong(this.edtIdProduto.getText().toString()));
        }else{
            return null;
        }

        if (this.edtNomeProduto.getText().toString().isEmpty() == false){
            this.produto.setNome(this.edtNomeProduto.getText().toString());
        }else{
            return null;
        }
        if(this.edtQuantidadeProduto.getText().toString().isEmpty() == false){

            //CONVERTE A QUANTIDADE PARA INTEIRO E JOGA NA VARIAVEL: quantidadeProduto:

            int quantidadeProduto = Integer.parseInt(this.edtQuantidadeProduto.getText().toString());
            this.produto.setQtdEstoque(quantidadeProduto);
        }else{
            return null;
        }

        //CONVERTE O PREÇO PARA DOUBLE E JOGA NA VARIÁVEL: precoProduto:

        if(edtPrecoProduto.getText().toString().isEmpty() == false){
            double precoProduto = Double.parseDouble(this.edtPrecoProduto.getText().toString());
            this.produto.setPreco(precoProduto);
        }else{
            return null;
        }
        {
        return produto;
        }
    }
}