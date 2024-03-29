package br.com.livroandroid.carros.domain;

import android.os.Parcel;
import android.os.Parcelable;
//Parcelable sem a biblioteca Parceler importada
public class Carro implements Parcelable {

    private static final long serialVersionUID = 6601006766832473959L;

    public long id;

    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
    public String urlVideo;
    public String latitude;
    public String longitude;

    @Override
    public String toString() {
        return "Carro{" + "nome='" + nome + '\'' + '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //Escreve os dados para serem serializados
        dest.writeLong(id);
        dest.writeString(this.tipo);
        dest.writeString(this.nome);
        dest.writeString(this.desc);
        dest.writeString(this.urlFoto);
        dest.writeString(this.urlInfo);
        dest.writeString(this.urlVideo);
        dest.writeString(this.latitude);
        dest.writeString(this.longitude);
    }

    public void readFromParcel(Parcel parcel) {
        //Lê os dados na mesma ordem em que foram escritos
        this.id = parcel.readLong();
        this.tipo = parcel.readString();
        this.nome = parcel.readString();
        this.desc = parcel.readString();
        this.urlFoto = parcel.readString();
        this.urlInfo = parcel.readString();
        this.urlVideo = parcel.readString();
        this.latitude = parcel.readString();
        this.longitude = parcel.readString();
    }

    public static final Parcelable.Creator<Carro> CREATOR = new Parcelable.Creator<Carro>() {
        @Override
        public Carro createFromParcel(Parcel p) {
            Carro c = new Carro();
            c.readFromParcel(p);
            return c;
        }

        @Override
        public Carro[] newArray(int size) {
            return new Carro[size];
        }
    };

}

/*
//anotação para usar a biblioteca para facilitar o uso do Parcelable (adicionado no app/build.gradle)
//@org.parceler.Parcel
public class Carro {

    private static final long serialVersionUID = 6601006766832473959L;

    public long id;

    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
    public String urlVideo;
    public String latitude;
    public String longitude;

    @Override
    public String toString() {
        return "Carro{" + "nome='" + nome + '\'' + '}';
    }

}
*/

//import java.io.Serializable;
/*
//classe serializada para passar dados pela intent, através de putExtra()
public class Carro implements Serializable{
    private static final long serialVersionUID = 6601006766832473959L;

    public long id;

    public String tipo;
    public String nome;
    public String desc;
    public String urlFoto;
    public String urlInfo;
    public String urlVideo;
    public String latitude;
    public String longitude;

    @Override
    public String toString(){
        return "Carro{" + "nome='" + nome + '\'' + '}';
    }

}
*/