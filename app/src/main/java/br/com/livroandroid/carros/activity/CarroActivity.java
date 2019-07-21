package br.com.livroandroid.carros.activity;

import android.os.Bundle;
import android.widget.ImageView;

//import org.parceler.Parcels;

import com.squareup.picasso.Picasso;

import br.com.livroandroid.carros.R;
import br.com.livroandroid.carros.domain.Carro;
import br.com.livroandroid.carros.fragments.CarroFragment;
import br.com.livroandroid.carros.fragments.CarrosFragment;

//Recebe objeto carro, pelo Bundle
//Fragment recupera os dados do carro selecionado
public class CarroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_carro);

        //Configura a Toolbar como a action bar
        setUpToolbar();
        //Titulo da Toolbar e botão up navigation
        //Carro c = (Carro) getIntent().getSerializableExtra("carro");//esta é para serializable
        //lê objeto Parcelable e transforma em um objeto Carro (Biblioteca Parceler importada no gradle)
        //Carro c = (Carro) Parcels.unwrap(getIntent().getParcelableExtra("carro"));//esta é para parcelable da biblioteca
        Carro c = (Carro) getIntent().getParcelableExtra("carro");//esta é para parcelable
        getSupportActionBar().setTitle(c.nome);
        //Liga o botão up navigation para voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Imagem de cabeçalho na AppBar
        ImageView appBarImg = (ImageView) findViewById(R.id.appBarImg);
        Picasso.with(getContext()).load(c.urlFoto).into(appBarImg);


        //verifica se o Bundle é nulo
        if(b == null){
            //Cria o fragment com o mesmo Bundle (args) da intent
            CarroFragment frag = new CarroFragment();
            frag.setArguments(getIntent().getExtras());
            //Adiciona o fragment no layout
            getSupportFragmentManager().beginTransaction().add(R.id.CarroFragment, frag).commit();

        }
    }
}
