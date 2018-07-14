package test.lazuardi.latihanmade;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class MovieAdapter extends BaseAdapter{
    private ArrayList<FilmList> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;

    public  MovieAdapter(Context context){
        Log.i(TAG, "masuk movie adapter");
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
    }
    public void setData(ArrayList<FilmList> items){
        mData = items;
        notifyDataSetChanged();
    }
    public void addItem(final FilmList item) {
        mData.add(item);
        notifyDataSetChanged();
    }
    public void clearData(){
        mData.clear();
    }
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
    @Override
    public int getViewTypeCount() {
        return 1;
    }
    @Override
    public int getCount() {
        if (mData == null) return 0;
        return mData.size();
    }
    @Override
    public FilmList getItem(int position) {
        return mData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.movie_item, null);
            holder.textViewJudul= (TextView)convertView.findViewById(R.id.textJudul);
            holder.textViewDeskripsi = (TextView)convertView.findViewById(R.id.textDeskrip);
            holder.textViewRilis = (TextView)convertView.findViewById(R.id.textRilis);
         //   holder.ImgPstr = (ImageView)convertView.findViewById(R.id.imgPoster);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewJudul.setText(mData.get(position).getJudul());
        holder.textViewDeskripsi.setText(mData.get(position).getDeskripsi());
        holder.textViewRilis.setText(mData.get(position).getRilis());
        return convertView;
    }
    private static class ViewHolder {
        TextView textViewJudul;
        TextView textViewDeskripsi;
        TextView textViewRilis;
       // ImageView ImgPstr;
    }
}
