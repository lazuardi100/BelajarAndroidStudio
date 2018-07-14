package test.lazuardi.latihanmade;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.LoaderManager;
import android.content.Loader;
import android.nfc.Tag;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<FilmList>> {


    private static final String TAG = "Informasi";
    private  static  String urlImg = "http://image.tmdb.org/t/p/w185/";

    ListView lv;
    MovieAdapter adapter;
    EditText iptFilm;
    Button btnCari;

    static final String EXTRAS_MOVIE = "EXTRAS_MOVIE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new MovieAdapter(this);
        adapter.notifyDataSetChanged();
        lv = (ListView)findViewById(R.id.lv_film);

        lv.setAdapter(adapter);

        iptFilm = (EditText)findViewById(R.id.inFilm);
        btnCari = (Button)findViewById(R.id.btnCari);

        btnCari.setOnClickListener(myListener);

        String film = iptFilm.getText().toString();
        Bundle bundle  = new Bundle();
        bundle.putString(EXTRAS_MOVIE, film);

    }

    public Loader<ArrayList<FilmList>>onCreateLoader(int id, Bundle args){
        String kumpulanMovie = "";
        Log.i(TAG, "Loader dibuat");
        if (args != null){
            kumpulanMovie = args.getString(EXTRAS_MOVIE);
        }
        return new MyAsyncTaskLoader(this,kumpulanMovie);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<FilmList>> loader, ArrayList<FilmList> data) {

        Log.i(TAG, "Loader selesai");
        adapter.setData(data);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<FilmList>> loader) {
        Log.i(TAG, "Loader reset");
        adapter.setData(null);
    }

    View.OnClickListener myListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String film = iptFilm.getText().toString();
            Log.i(TAG, "Judul filmnya " +film);
            Log.i(TAG, "Tombol dipencet");

            if (TextUtils.isEmpty(film))return;

            Bundle bundle = new Bundle();
            bundle.putString(EXTRAS_MOVIE,film);
            getLoaderManager().restartLoader(0, bundle,MainActivity.this);
        }
    };
}
