package com.example.farmaciagrupoa.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.farmaciagrupoa.Produto;
import com.example.farmaciagrupoa.R;

import java.util.List;

public class AdapterListaProdutos extends BaseAdapter {

    private Context context;
    private List<Produto> produtoList;

    public AdapterListaProdutos(Context context, List<Produto> produtoList) {
        this.context = context;
        this.produtoList = produtoList;
    }

    @Override
    public int getCount() {
        return this.produtoList.size();
    }

    @Override
    public Object getItem(int posicao) {
        return this.produtoList.get(posicao);
    }

    @Override
    public long getItemId(int posicao) {
        return posicao;
    }

    //REMOVE O PRODUTO DA LISTA:

    public void removerProduto(int posicao){
        this.produtoList.remove(posicao);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int posicao, View convertView, ViewGroup parent) {

        View v = View.inflate(this.context, R.layout.layout_produto, null);

        TextView tvNomeProduto = (TextView) v.findViewById(R.id.tvNomeProduto);
        TextView tvPrecoProduto = (TextView) v.findViewById(R.id.tvPrecoProduto);
        TextView tvEstoqueProduto = (TextView) v.findViewById(R.id.tvEstoqueProduto);

        tvNomeProduto.setText(this.produtoList.get(posicao).getNome());
        tvPrecoProduto.setText(String.valueOf(this.produtoList.get(posicao).getPreco()));
        tvEstoqueProduto.setText(String.valueOf(this.produtoList.get(posicao).getQtdEstoque()));

        //ADICIONADO 10/11 :

        //sVBusca = v.findViewById(R.id.sv_buscar);

        //sVBusca.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            //@Override
            //public boolean onQueryTextSubmit(String query) {

                //return false;
            //}

            //@Override
            //public boolean onQueryTextChange(String newText) {
                //return false;
            //}
        //});
        //FIM

        return v;

    }
}
