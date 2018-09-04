package com.example.hugov.appcurso.adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hugov.appcurso.R;
import com.example.hugov.appcurso.entidades.Curso;

import java.util.List;
//classe para criar adaptador do reciclerview
public class CursosAdapter extends RecyclerView.Adapter<CursosAdapter.CursosHolder> {
    //Lista que contera os itens dos cursos
    List<Curso> listaDeCursos;
    //construtor                                //recebe ojeto lista de cursos
    public CursosAdapter(List<Curso> listaDeCursos){
        //instnaciando e inicializando
        this.listaDeCursos = listaDeCursos;
    }

    @NonNull
    @Override
    //associação layout
    public CursosHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //faz a associação do do recycler view ao template lista_cursos
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cursos, parent,false);
        RecyclerView.LayoutParams layoutParams =
                new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new CursosHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull CursosHolder holder, int position) {
        holder.txtCodigo.setText(listaDeCursos.get(position).getCodigo().toString());
        holder.txtNome.setText(listaDeCursos.get(position).getNome().toString());
        holder.txtCategoria.setText(listaDeCursos.get(position).getCategoria().toString());
        holder.txtProfessor.setText(listaDeCursos.get(position).getProfessor().toString());
//d
    }

    @Override
    public int getItemCount() {
        return listaDeCursos.size();
    }

    public class CursosHolder extends RecyclerView.ViewHolder {
        TextView txtNome,txtCodigo,txtCategoria,txtProfessor;

        public CursosHolder(View itemView) {
            super(itemView);
            txtNome = itemView.findViewById(R.id.txtNome);
            txtCodigo = itemView.findViewById(R.id.txtCodigo);
            txtCategoria = itemView.findViewById(R.id.txtCategoria);
            txtProfessor = itemView.findViewById(R.id.txtProfessor);
        }
    }

}
