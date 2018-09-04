package com.example.hugov.appcurso.fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hugov.appcurso.R;
import com.example.hugov.appcurso.adaptador.CursosAdapter;
import com.example.hugov.appcurso.entidades.Curso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link consultarListaCursosFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link consultarListaCursosFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */
public class consultarListaCursosFragmento extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //Objetos
    RecyclerView recyclerView;
    ArrayList<Curso> listaCursos;
    ProgressDialog progresso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public consultarListaCursosFragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment consultarListaCursosFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static consultarListaCursosFragmento newInstance(String param1, String param2) {
        consultarListaCursosFragmento fragment = new consultarListaCursosFragmento();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragment_consultar_lista_cursos_fragmento, container, false);

        listaCursos = new ArrayList<>();
        //atribui o nosso recycler view
        recyclerView = vista.findViewById(R.id.idRecycler);
        //define layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        //mantem o recycer view em um tamanho fixo
        recyclerView.setHasFixedSize(true);
        //requisição volley
        request = Volley.newRequestQueue(getContext());

        carregaWebService();

        // Inflate the layout for this fragment
        return vista;
    }

    public void carregaWebService(){
        progresso = new ProgressDialog(getContext());
        progresso.setMessage("Buscando...");
        progresso.show();



        String url = "http://192.168.2.162/webservices/consultarLista.php";
        //url = url.replace(" ", "%20");
        //teste
        //campoNome.setText(campoCodigo.getText().toString());

        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
        request.add(jsonObjectRequest);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    //implementação da interface
    @Override
    public void onErrorResponse(VolleyError error) {
        progresso.hide();
        Toast.makeText(getContext(),"Não foi possivel realizar aconsulta",Toast.LENGTH_SHORT).show();
        Log.i("ErroConsultaLista",error.toString());

    }
    //implementação da interface
    @Override
    public void onResponse(JSONObject response) {
        progresso.hide();
        //Toast.makeText(getContext(), "Mensagem: "+response, Toast.LENGTH_SHORT).show();
        Curso curso = null;
        JSONArray json = response.optJSONArray("curso");
        //JSONObject jsonObject;
        try {//percorre tabela
            for (int i = 0; i < json.length(); i++){
                curso = new Curso();
                JSONObject jsonObject;
                //Passa as informações do curso para um jsonbject na posicao i
                    jsonObject = json.getJSONObject(i);
                    curso.setCodigo(jsonObject.optInt("codigo"));
                    curso.setNome(jsonObject.optString("nome"));
                    curso.setProfessor(jsonObject.optString("professor"));
                    curso.setCategoria(jsonObject.optString("categoria"));

                    //Adiciona os itens a lista de cursos
                    listaCursos.add(curso);

            }
            progresso.hide();
            //alimentado com dados
            CursosAdapter adapter = new CursosAdapter(listaCursos);
            //recycle recebe as informações do adaptador
            recyclerView.setAdapter(adapter);
//            tabCurso.setDado(jsonObject.optString("imagem"));
        }catch (JSONException e){
            e.printStackTrace();
            progresso.hide();
            Toast.makeText(getContext(),"Erro ao consultar lista de cursos" + response ,Toast.LENGTH_SHORT).show();

        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
