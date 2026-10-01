package com.example.prova.model;
import java.io.Serializable;

public class Pizza implements Serializable {
    private int id;
    private String nome;
    private String ingredientes;
    private double preco;
    private int imagemResId;
    private String tempoPreparo;
    private String categoria;

    public Pizza(int id, String nome, String ingredientes, double preco, int imagemResId, String tempoPreparo, String categoria) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.preco = preco;
        this.imagemResId = imagemResId;
        this.tempoPreparo = tempoPreparo;
        this.categoria = categoria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setImagemResId(int imagemResId) {
        this.imagemResId = imagemResId;
    }

    public void setTempoPreparo(String tempoPreparo) {
        this.tempoPreparo = tempoPreparo;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
