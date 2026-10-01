package com.example.prova.model;
import java.io.Serializable;

public class Pizza implements Serializable {
    private int id;
    private String nome;
    private String ingredientes;
    private double preco;
    private int imagemResId;
    private String tempoPreparo;

    public Pizza(int id, String nome, String ingredientes, double preco, int imagemResId, String tempoPreparo) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
        this.preco = preco;
        this.imagemResId = imagemResId;
        this.tempoPreparo = tempoPreparo;
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


    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public double getPreco() {
        return preco;
    }

    public int getImagemResId() {
        return imagemResId;
    }

    public String getTempoPreparo() {
        return tempoPreparo;
    }

}
