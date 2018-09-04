package com.example.hugov.appcurso.fragmentos;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.hugov.appcurso.R;

import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link registrarCursoFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link registrarCursoFragmento#newInstance} factory method to
 * create an instance of this fragment.
 */                                                 //Implementando interface do volley
public class registrarCursoFragmento extends Fragment implements Response.Listener<JSONObject> , Response.ErrorListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    //OBJETOS VIEW
    EditText campoNome, campoCodigo, campoCategoria, campoProfessor;
    Button botaoRegistrar;
    ImageView imgFoto;
    ProgressDialog progresso;
    RelativeLayout layoutRegistrar;
    RequestQueue request ; //Para solicitar as requisições via json
    JsonObjectRequest jsonObjectReq;//objeto para ser requisitado

    private OnFragmentInteractionListener mListener;

    public registrarCursoFragmento() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment registrarCursoFragmento.
     */
    // TODO: Rename and change types and number of parameters
    public static registrarCursoFragmento newInstance(String param1, String param2) {
        registrarCursoFragmento fragment = new registrarCursoFragmento();
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
        // Inflate the layout for this fragment
        //objeto view
        View vista= inflater.inflate(R.layout.fragment_registrar_curso_fragmento, container, false);
        campoCodigo = vista.findViewById(R.id.campoCodigo);
        campoNome = vista.findViewById(R.id.campoNome);
        campoCategoria = vista.findViewById (R.id.campoCategoria);
        campoProfessor = vista.findViewById(R.id.campoProfessor);
        botaoRegistrar = vista.findViewById(R.id.btnRegistrar);

        //Intância um objeto request que vai receber requisoções tipo volley
        request = Volley.newRequestQueue(getContext());

        botaoRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registraWEBService();
            }
        });
        return vista;
    }

    private void registraWEBService(){
        if (campoCodigo.getText().toString().equals("") || campoNome.getText().toString().equals("")
                || campoCategoria.getText().toString().equals("") || campoProfessor.getText().toString().equals("")){
            Toast.makeText(getContext(),"Todos os campos devem estar preenchidos",Toast.LENGTH_SHORT).show();
        }else {
            //barra de progresso
            progresso = new ProgressDialog(getContext());
            progresso.setMessage("Carregando...");
            progresso.show();

            String ipPC = "192.168.2.162";//para testes e uso do servidor local

            //String para armazenar a url do banco com suas determinadas variaveis capturadas dos RditsTextes
            String url = "http://192.168.2.162/webservices/registro.php?codigo=" + campoCodigo.getText().toString() + "&nome=" + campoNome.getText().toString() +
                    "&categoria=" + campoCategoria.getText().toString() + "&professor=" + campoProfessor.getText().toString() + "";
            //Retira os espaços da url com método replace
            url = url.replace(" ", "%20");//@param 1 campo que quer "substituir" @param2 "substituto" %20 que seria sem espaço
            //Ler e processar url com JsonObjectRequest
            jsonObjectReq = new JsonObjectRequest(Request.Method.GET, url, null, this, this);
            request.add(jsonObjectReq);
        }
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

    //MÉTODOS VOLLEY
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    //em caso de erro do request
    @Override
    public void onErrorResponse(VolleyError error) {
        //caso ocorra erro dispensaremos o progress
        progresso.hide();
        Log.v("ERRO",""+error);
        Toast.makeText(getContext(),"Não foi possivel conectar ao servidor",Toast.LENGTH_SHORT).show();
    }
    //sucesoo do resquest
    @Override
    public void onResponse(JSONObject response) {
        progresso.hide();//se er certo vai esconder seu objeto
        Toast.makeText(getContext(),"Cadastrado com sucesso",Toast.LENGTH_SHORT).show();
        campoCodigo.setText("");
        campoNome.setText("");
        campoCategoria.setText("");
        campoProfessor.setText("");

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
