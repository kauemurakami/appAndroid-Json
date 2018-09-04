package com.example.hugov.appcurso.entidades;

import android.graphics.Bitmap;

//Cada tabela do banco é uma entidades
public class Curso {
    private Integer codigo;
    private String nome;
    private String professor;
    private String categoria;
    private Bitmap imagem;

    public Bitmap getImagem() {
        return imagem;
    }
    public void setImaem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
