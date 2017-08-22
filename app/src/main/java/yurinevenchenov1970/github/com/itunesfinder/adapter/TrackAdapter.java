package yurinevenchenov1970.github.com.itunesfinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import yurinevenchenov1970.github.com.itunesfinder.R;
import yurinevenchenov1970.github.com.itunesfinder.bean.Track;
import yurinevenchenov1970.github.com.itunesfinder.bean.TracksResponse;

/**
 * @author Yuri Nevenchenov on 8/21/2017.
 */

public class TrackAdapter extends RecyclerView.Adapter<TrackViewHolder> {

    private TracksResponse mTracksResponse;
    private TrackClickListener mClickListener;

    public TrackAdapter(TracksResponse tracksResponse, TrackClickListener listener) {
        mTracksResponse = tracksResponse;
        mClickListener = listener;
    }

    @Override
    public TrackViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.track_item, parent, false);
        return new TrackViewHolder(view, mClickListener);
    }

    @Override
    public void onBindViewHolder(TrackViewHolder holder, int position) {
        Track track = mTracksResponse.getTracks().get(position);
        holder.mArtistNameTextView.setText(track.getArtistName());
        holder.mTrackNameTextView.setText(track.getTrackName());
        Picasso.with(holder.itemView.getContext())
                .load(track.getCoverUrl())
                .into(holder.mCoverImageView);
    }

    @Override
    public int getItemCount() {
        return mTracksResponse.getTrackCount();
    }
}