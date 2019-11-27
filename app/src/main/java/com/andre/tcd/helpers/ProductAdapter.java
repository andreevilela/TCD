package com.andre.tcd.helpers;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andre.tcd.activities.AlteraProduto;
import com.andre.tcd.R;
import com.andre.tcd.activities.ListaProduto;
import com.andre.tcd.dto.DtoProduct;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {
    private LayoutInflater mInflater; //objeto que "infla" o layout do item de lista do recyclerview
    private Context context; //activity que está exibindo o recyclerview
    private List<DtoProduct> lista; //fonte dos dados da lista a ser exibida
    private DtoProduct mRecentlyDeleteItem;
    private int mRecentlyDeleteItemPosition;

    public ProductAdapter(Context context, List<DtoProduct> lista) {
        this.context = context;
        this.lista = lista;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_produto, parent, false);
        return new ProductHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        String nome = lista.get(position).getName();
        holder.nome.setText(nome);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    //swipe
    public void deleteItem(int position) {
        Toast.makeText(context, "Excluindo item", Toast.LENGTH_SHORT).show();
        mRecentlyDeleteItem = lista.get(position);
        mRecentlyDeleteItemPosition = position;
        lista.remove(position);
        notifyItemRemoved(position);
        showAlertDialogButtonClicked();
    }

    //swipe
    private void showAlertDialogButtonClicked() {
        //setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Exclusão do Produto");
        builder.setMessage("Você confirma a exclusão?");
        //add the buttons
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                excluir();
            }
        });
        //create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    //swipe
    private void excluir() {
        ((ListaProduto) context).excluirItem(mRecentlyDeleteItem.getId());
    }

    //swipe
    private void undoDelete(){
        lista.add(mRecentlyDeleteItemPosition, mRecentlyDeleteItem);
        notifyItemInserted(mRecentlyDeleteItemPosition);
    }

    public Context getContext() {
        return context;
    }

    public class ProductHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ProductAdapter productAdapter;
        public final TextView nome;

        public ProductHolder(@NonNull View itemView, ProductAdapter productAdapter) {
            super(itemView);
            this.productAdapter = productAdapter;
            nome = itemView.findViewById(R.id.tv_recyclerview_nome_produto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DtoProduct product = lista.get(getLayoutPosition());
            String name = product.getName();
            int id = product.getId();
            String description = product.getDescription();
            String imgUrl = product.getImgUrl();
            int price = product.getPrice();
            Intent intent = new Intent(context, AlteraProduto.class);
            intent.putExtra("id", id);
            intent.putExtra("name", name);
            intent.putExtra("description", description);
            intent.putExtra("imgUrl", imgUrl);
            intent.putExtra("price", price);
            context.startActivity(intent);
        }
    }
}