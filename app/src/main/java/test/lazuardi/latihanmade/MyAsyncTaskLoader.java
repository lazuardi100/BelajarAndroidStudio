package test.lazuardi.latihanmade;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static android.content.ContentValues.TAG;

public class MyAsyncTaskLoader extends AsyncTaskLoader<ArrayList<FilmList>> {
    private ArrayList<FilmList> mData;
    private boolean mHasResutlt = false;
    private String mKumpulanMovie;

    public MyAsyncTaskLoader(final Context context, String kumpulanMovie){
        super(context);
        Log.i(TAG, "Data masuk ke Async" );
        this.mKumpulanMovie = kumpulanMovie;
        onContentChanged();

    }
    protected void OnStartLoading(){
        Log.i(TAG, "AsyncDimulai" );
        if(takeContentChanged())

            forceLoad();
        else if (mHasResutlt)

            deliverResult(mData);

    }
    public void deliverResult(final ArrayList<FilmList> data){
        mData = data;
        mHasResutlt = true;
        Log.i (TAG, "deliver result");
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        Log.i (TAG, "onReset");
        if(mHasResutlt){
            onReleaseResources(mData);
            mData = null;
            mHasResutlt = false;
        }
    }

    private void onReleaseResources(ArrayList<FilmList> data) {
        Log.i (TAG, "onReleaseReosource");
    }

    @Override
    public ArrayList<FilmList> loadInBackground() {
        Log.i (TAG, "masih loading");
        SyncHttpClient client = new SyncHttpClient();
        final ArrayList<FilmList> listFilm = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/search/movie?api_key=d5cbd56b05981c7db5c5fd9ed951bfcf&language=en-US&query="
                +mKumpulanMovie;
        client.get(url, new AsyncHttpResponseHandler() {

            public void onStart(){
                super.onStart();
                Log.i (TAG, "ngambil data");
                setUseSynchronousMode(true);
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("list");
                    Log.i (TAG, "yey sukses");
                    for (int i= 0; i<list.length();i++){
                        JSONObject film = list.getJSONObject(i);
                        FilmList filmList = new FilmList(film);
                        listFilm.add(filmList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.i (TAG, "yaahh gagal :(");
            }
        });
        return listFilm;

    }

}
