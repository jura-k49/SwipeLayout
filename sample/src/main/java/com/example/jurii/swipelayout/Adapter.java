package com.example.jurii.swipelayout;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.swipelayout.SwipeLayout;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<String> items;
    private RecyclerView recycler;

    public Adapter(List<String> it) {
        items = it;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh = new DataHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item, parent, false));
        return vh;
    }

    class DataHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt)
        TextView txt;
        @BindView(R.id.ll) TextView ll;
        @BindView(R.id.rr)
        LinearLayout rr;
        @BindView(R.id.zz) TextView zz;
        @BindView(R.id.swipeLayout) SwipeLayout swipeLayout;
        @BindView(R.id.swipe)
        RelativeLayout swipe;

        public DataHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DataHolder h = (DataHolder) holder;
        String st = items.get(position);
        h.txt.setText(st);
        h.swipeLayout.setOneSwipe(recycler);
        h.swipeLayout.setSwipeView(h.swipe);

        switch (Integer.valueOf(st.substring(0, 1))) {
            case 1 :
                h.swipeLayout.setSwipeLeft(SwipeLayout.TYPE_SWIPE.CURTAIN, h.ll);
                h.swipeLayout.setSwipeRight(SwipeLayout.TYPE_SWIPE.DRAG, h.zz);
                break;
            case 2 :
                h.swipeLayout.setSwipeLeft(SwipeLayout.TYPE_SWIPE.DRAG, h.ll);
                h.swipeLayout.setSwipeRight(SwipeLayout.TYPE_SWIPE.HARMONIC, h.rr);
                break;
            case 3 :
                h.swipeLayout.setSwipeLeft(SwipeLayout.TYPE_SWIPE.HARMONIC, h.rr);
                h.swipeLayout.setSwipeRight(SwipeLayout.TYPE_SWIPE.CURTAIN, h.zz);
                break;
            case 4 :
                h.swipeLayout.setSwipeLeft(SwipeLayout.TYPE_SWIPE.HARMONIC, h.rr);
                h.swipeLayout.setOnSwipeRemove(true, false, holder, listener);
                break;
        }
    }

    private SwipeLayout.OnSwipeRemove listener = new SwipeLayout.OnSwipeRemove() {
        @Override
        public void onRemove(SwipeLayout.DIRECT direct, int position) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    };

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        recycler = recyclerView;
    }
}
