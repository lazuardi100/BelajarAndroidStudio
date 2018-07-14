package test.lazuardi.latihanmade;


import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class FilmList {

    private int id;
    private String judul;
    private String Deskripsi;
    private String Rilis;
    private String PstrSrc;

    public FilmList(JSONObject object) {
        try {
            Log.i(TAG,"Masih ngambil data nih");
            int id = object.getInt("id");
            String judul = object.getString("original_title");
            String Deskripsi = object.getString("overview");
            String Rilis = object.getString("release_date");
            //String PstrSrc = object.getString("poster_path");

            this.id = id;
            this.judul = judul;
            this.Deskripsi = Deskripsi;
            this.Rilis=Rilis;
            Log.i (TAG, "data diset");
            //this.PstrSrc=PstrSrc;

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public String getRilis() {
        return Rilis;
    }

    public void setRilis(String rilis) {
        Rilis = rilis;
    }

    /*public String getPstrSrc() {
        return PstrSrc;
    }

    public void setPstrSrc(String pstrSrc) {
        PstrSrc = pstrSrc;
    }*/
}
