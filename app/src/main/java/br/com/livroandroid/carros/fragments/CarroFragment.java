package br.com.livroandroid.carros.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.livroandroid.carros.R;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import br.com.livroandroid.carros.domain.Carro;

//Fragment que estará na activity carro
public class CarroFragment extends BaseFragment {
    private Carro carro;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle b){
        //Infla a view deste fragment (transforma o xml em um View)
            //R.layout.fragment_carro -> elemento a ser inflado
            //container -> pai deste layout
            //attachToRoot false -> Adapter será responsável por anexa itens na view
        View view = inflater.inflate(R.layout.fragment_carro, container, false);
        //Lê o objeto carro dos parâmentros
        carro = (Carro) getArguments().getSerializable("carro");
        //Atualiza a descrição do carro no TextView
        TextView tDesc = (TextView) view.findViewById(R.id.tDesc);
        tDesc.setText(carro.desc);
        //Mostra a foto do carro no ImageView
        //A lib Picasso está dando uma força aqui
        final ImageView imgView = (ImageView) view.findViewById(R.id.img);
        Picasso.with(getContext()).load(carro.urlFoto).fit().into(imgView);

        return view;
    }




}
