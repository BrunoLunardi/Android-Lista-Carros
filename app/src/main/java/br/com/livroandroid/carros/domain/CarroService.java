package br.com.livroandroid.carros.domain;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//biblioteca para leitura de arquivos
import br.com.livroandroid.carros.R;
import livroandroid.lib.utils.FileUtils;
//biblioteca para xml
import livroandroid.lib.utils.XMLUtils;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
//biblioteca para acessar webservice
import livroandroid.lib.utils.HttpHelper;

//Classe para criar uma lista de carros de forma fixa na memória
//o parãmetro tipo será enviado do fragment (CarrosFragment), para criar a lista correta de carros
public class CarroService {

    private static final boolean LOG_ON = false;

    private static final String TAG = "CarroService";

    //url para acessar webservice com JSON
    private static final String URL = "http://www.livroandroid.com.br/livro/carros/v2/carros_{tipo}.json";

    //Lista de carros
    //Tratamento de exceções será feito no java/fragments/CarrosFragment
    public static List<Carro> getCarros(Context context, int tipo) throws IOException {

        /*
        Leitura estática

        //tipo = clássicos, esportivos ou luxo
        String tipoString = context.getString(tipo);
        List<Carro> carros = new ArrayList<Carro>();

        for(int i = 0; i < 20; i++){
            Carro c = new Carro();
            c.nome = "Carro " + tipoString + ": " + i;//Nome dinâmico conforme o tipo
            c.desc = "Desc " + i;
            c.urlFoto = "http://www.livroandroid.com.br/livro/carros/esportivos/Ferrari_FF.png";
            carros.add(c);
        }

        return carros;
     */

        /*
            XML

        //leitura do arquivo xml do tipo do carro (res/raw/carros_tipo.xml)
        String xml = readFile(context, tipo);
        //lista de carros xml
        List<Carro> carros = parseXML(context, xml);
        //retorna lista de carros
        return carros;

         */

        /*
        //JSON
        String json = readFile(context, tipo);
        //lista de carros json
        List<Carro> carros = parserJSON(context, json);
        //retorna lista de carros
        return carros;
        */
        //acessar lista via webservice
        String tipoString = getTipo(tipo);
        String url = URL.replace("{tipo}", tipoString);
        //Faz a requisição HTTP no servidor e retorna a string com o conteúdo
        HttpHelper http = new HttpHelper();
        String json = http.doGet(url);
        List<Carro> carros = parserJSON(context, json);
        return carros;

    }

    //Converte a constante para string, para criar a URL do web service
    private static String getTipo(int tipo){
        if(tipo == R.string.classicos){
            return "classicos";
        }else if(tipo == R.string.esportivos){
            return "esportivos";
        }
        return "luxo";
    }

    /*

    //Faz a leitura do arquivo que está na pasta /res/raw
    private static String readFile(Context context, int tipo) throws IOException{
        if(tipo == R.string.classicos){
            return FileUtils.readRawFileString(context, R.raw.carros_classicos, "UTF-8");
        }else if(tipo == R.string.esportivos){
            return FileUtils.readRawFileString(context, R.raw.carros_esportivos, "UTF-8");
        }
        return FileUtils.readRawFileString(context, R.raw.carros_luxo, "UTF-8");
    }
*/
    /*
    //Faz o parser do XML e cria a lista de carros
    public static List<Carro> parseXML(Context context, String xml){
        List<Carro> carros = new ArrayList<>();
        Element root = XMLUtils.getRoot(xml, "UTF-8");
        //Lê todas as tags <carro>
        List<Node> nodeCarros = XMLUtils.getChildren(root, "carro");
        //Insere cada carro na lista
        for(Node node: nodeCarros){
            Carro c = new Carro();
            //Lê as informações de cada carro
            c.nome = XMLUtils.getText(node, "nome");
            c.desc = XMLUtils.getText(node, "desc");
            c.urlFoto = XMLUtils.getText(node, "url_foto");
            c.urlInfo = XMLUtils.getText(node, "url_info");
            c.urlVideo = XMLUtils.getText(node, "url_video");
            c.latitude = XMLUtils.getText(node, "latitude");
            c.longitude  = XMLUtils.getText(node, "longitude");

            if(LOG_ON){
                Log.d(TAG, "Carro " + c.nome + " > " + c.urlFoto);
            }

            carros.add(c);
        }
        if(LOG_ON){
            Log.d(TAG, carros.size() + " encontrados. ");
        }

        return carros;
    }
    */


    //Faz o parser do XML e cria a lista de carros
    public static List<Carro> parserJSON(Context context, String json) throws IOException{
        List<Carro> carros = new ArrayList<>();
        try{
            //Lê o array de carros do JSON
            JSONArray jsonCarros = new JSONArray(json);
            //Insere cada carro na lista
            for(int i = 0; i < jsonCarros.length(); i++){
                JSONObject jsonCarro = jsonCarros.getJSONObject(i);
                Carro c = new Carro();
                //Lê as informações de cada carro
                c.nome = jsonCarro.optString("nome");
                c.desc = jsonCarro.optString("desc");
                c.urlFoto = jsonCarro.optString("url_foto");
                c.urlInfo = jsonCarro.optString("url_info");
                c.urlVideo = jsonCarro.optString("url_video");
                c.latitude = jsonCarro.optString("latitude");
                c.longitude  = jsonCarro.optString("longitude");

                if(LOG_ON){
                    Log.d(TAG, "Carro " + c.nome + " > " + c.urlFoto);
                }
                carros.add(c);
            }
            if(LOG_ON){
                Log.d(TAG, carros.size() + " encontrados. ");
            }
        }catch (JSONException e){
            throw new IOException(e.getMessage(), e);
        }

        return carros;
    }

}
