package com.niksan.musicfomothers.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.niksan.musicfomothers.R;
import com.niksan.musicfomothers.model.Week;
import com.niksan.musicfomothers.webservice.WeekContent;

import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    private List<Week> weeks;
    private Context context;

    public CalendarAdapter(List<Week> weeks, Context context) {
        this.weeks = weeks;
        this.context = context;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_item, parent, false);
        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, final int position) {
        holder.title.setText(weeks.get(position).getTitle());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( context, WeekContent.class);
                intent.putExtra("id", position);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return weeks.size();
    }



    public class CalendarViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private CardView cardView;
        public CalendarViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.weektitle);
            cardView = itemView.findViewById(R.id.weekItemCardView);

            Typeface font = Typeface.createFromAsset(context.getAssets(), "B Roya.ttf");
            title.setTypeface(font);
        }
    }
}
