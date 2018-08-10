package com.niksan.musicfomothers.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.niksan.musicfomothers.R;
import com.niksan.musicfomothers.model.Track;
import java.util.List;
import pl.droidsonroids.gif.GifImageView;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Track> tracks;
    private  Context context;
    private int currentPlayingPosition = -1;

    public static SparseBooleanArray itemDownloadState = new SparseBooleanArray();
    public static SparseBooleanArray itemPlayState = new SparseBooleanArray();
    public static SparseBooleanArray itemFileState = new SparseBooleanArray();


    public MyAdapterListener myOnClickListener;

    public Adapter(List<Track> tracks, Context context, MyAdapterListener listener) {
        this.tracks = tracks;
        this.context = context;
        myOnClickListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        holder.title.setText(tracks.get(position).getTitle());
        holder.duration.setText(String.valueOf(tracks.get(position).getDuration()));

        Glide.with(context)
                .load(tracks.get(position).getImgurl())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.trackImage);


        if (itemFileState.get(position) && !itemDownloadState.get(position)) {
            holder.btnDownload.setVisibility(View.INVISIBLE);
            holder.btnPlay.setVisibility(View.VISIBLE);
        } else {
            holder.btnDownload.setVisibility(View.VISIBLE);
            holder.btnPlay.setVisibility(View.INVISIBLE);
        }

        if (itemDownloadState.get(position)) {
            holder.downloading.setVisibility(View.VISIBLE);
        } else {
            holder.downloading.setVisibility(View.INVISIBLE);
        }

        if (itemPlayState.get(position)
                && position == currentPlayingPosition
                && itemFileState.get(position)
                && !itemDownloadState.get(position)
                ||
                itemPlayState.get(Track.playingItem)
                        && position == currentPlayingPosition
                        && itemFileState.get(position)
                        && !itemDownloadState.get(position)
                ) {

            holder.btnPlay.setVisibility(View.INVISIBLE);
            holder.btnPause.setVisibility(View.VISIBLE);
            holder.equalizer.setVisibility(View.VISIBLE);
        } else {
            holder.equalizer.setVisibility(View.INVISIBLE);
            holder.btnPause.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        private Button btnPlay, btnPause;
        private ImageButton btnDownload;
        private TextView title, duration;
        private ImageView trackImage;
        private GifImageView equalizer, downloading;
        private CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            trackImage = itemView.findViewById(R.id.trackImage);
            equalizer = itemView.findViewById(R.id.eq);
            cardView = itemView.findViewById(R.id.cardView);
            downloading = itemView.findViewById(R.id.loading);
            btnPlay = itemView.findViewById(R.id.play);
            btnPause = itemView.findViewById(R.id.pause);
            duration = itemView.findViewById(R.id.duration);
            btnDownload = itemView.findViewById(R.id.download);


            if (itemPlayState.get(Track.playingItem) && Track.referedToActivity) {
                itemPlayState.put(Track.playingItem, true);
                currentPlayingPosition = Track.playingItem;
            }


            Typeface face = Typeface.createFromAsset(context.getAssets(), "B Roya.ttf");
            duration.setTypeface(face);

            btnPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    int prev = currentPlayingPosition;
                    currentPlayingPosition = position;
                    if (prev >= 0)
                        notifyItemChanged(prev);  // refresh previously playing view
                    itemPlayState.put(currentPlayingPosition, true);

                    for (int i = 0; i < tracks.size(); i++) {
                        if (itemPlayState.get(i))
                            notifyItemChanged(i);
                    }

                    myOnClickListener.playOnClick(v, getAdapterPosition());
                }
            });

            btnPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Track.isPlaying = false;

                    int position = getAdapterPosition();
                    notifyItemChanged(position);
                    currentPlayingPosition = -1;
                    itemPlayState.put(currentPlayingPosition, false);
                    myOnClickListener.pauseOnClick(v, position);
                }
            });

            btnDownload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    currentPlayingPosition = position;
                    // refresh previously playing view
                    itemDownloadState.put(currentPlayingPosition, true);
                    myOnClickListener.downloadOnClick(v, currentPlayingPosition);
                }
            });
        }
    }


}