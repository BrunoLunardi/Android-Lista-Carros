package br.com.livroandroid.carros.activity;

import android.os.Bundle;
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
        Carro c = (Carro) getIntent().getSerializableExtra("carro");
        getSupportActionBar().setTitle(c.nome);
        //Liga o botão up navigation para voltar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
