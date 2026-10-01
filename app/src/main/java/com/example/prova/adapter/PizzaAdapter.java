package com.example.prova.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prova.R;
import com.example.prova.model.Pizza;

import java.util.List;
import java.util.Locale;

public class PizzaAdapter extends RecyclerView.Adapter<PizzaAdapter.PizzaViewHolder> {

    public interface OnPizzaClickListener {
        void onPizzaClick(Pizza pizza);
        void onSolicitarClick(Pizza pizza);
    }

    private final List<Pizza> pizzas;
    private final OnPizzaClickListener listener;

    public PizzaAdapter(List<Pizza> pizzas, OnPizzaClickListener listener) {
        this.pizzas = pizzas;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pizza, parent, false);
        return new PizzaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PizzaViewHolder holder, int position) {
        Pizza pizza = pizzas.get(position);
        holder.bind(pizza, listener);
    }

    @Override
    public int getItemCount() {
        return pizzas.size();
    }

    public static class PizzaViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtNome;
        private final TextView txtIngredientes;
        private final TextView txtPreco;
        private final ImageView imgPizza;
        private final Button btnSolicitar;

        public PizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNomePizza);
            txtIngredientes = itemView.findViewById(R.id.txtIngredientes);
            txtPreco = itemView.findViewById(R.id.txtPreco);
            imgPizza = itemView.findViewById(R.id.imgPizza);
            btnSolicitar = itemView.findViewById(R.id.btnSolicitar);
        }

        public void bind(final Pizza pizza, final OnPizzaClickListener listener) {
            txtNome.setText(pizza.getNome());
            txtIngredientes.setText(pizza.getIngredientes());
            txtPreco.setText(String.format(Locale.forLanguageTag("pt-BR"), "R$ %.2f", pizza.getPreco()));
            imgPizza.setImageResource(pizza.getImagemResId());

            itemView.setOnClickListener(v -> {
                if (listener != null) listener.onPizzaClick(pizza);
            });

            btnSolicitar.setOnClickListener(v -> {
                if (listener != null) listener.onSolicitarClick(pizza);
            });
        }
    }
}