package yurinevenchenov1970.github.com.itunesfinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import yurinevenchenov1970.github.com.itunesfinder.bean.Track;

/**
 * Activity to show details of track
 *
 * @author Yuri Nevenchenov on 8/28/2017.
 */
public class TrackDetailActivity extends AppCompatActivity {

    private static final String EXTRA_TRACK = "extra_track";

    @BindView(R.id.cover_image_view)
    ImageView mCoverImageView;

    @BindView(R.id.artist_name_text_view)
    TextView mArtistNameTextView;

    @BindView(R.id.price_text_view)
    TextView mPriceTextView;

    @BindView(R.id.track_name_text_view)
    TextView mTrackNameTextView;

    private FragmentManager mFragmentManager;
    private Track mTrack;

    public static Intent createExplicitIntent(Context context, Track track) {
        Intent intent = new Intent(context, TrackDetailActivity.class);
        intent.putExtra(EXTRA_TRACK, track);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_detail);
        ButterKnife.bind(this);
        getDataFromIntent();
        setDataToViews();
        if (savedInstanceState == null) {
            mFragmentManager = getSupportFragmentManager();
            fillMusicPlayerFragment();
            fillArtistProfileFragment();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        MusicPlayerFragment fragment =
                (MusicPlayerFragment) getSupportFragmentManager().findFragmentById(R.id.music_player_container);
        fragment.onBackPressed();
    }

    private void getDataFromIntent() {
        mTrack = getIntent().getExtras().getParcelable(EXTRA_TRACK);
    }

    private void setDataToViews() {
        Picasso.with(this).load(mTrack.getCoverUrl()).into(mCoverImageView);
        mArtistNameTextView.setText(mTrack.getArtistName());
        String price = "$" + mTrack.getTrackPrice();
        mPriceTextView.setText(price);
        mTrackNameTextView.setText(mTrack.getTrackName());
    }

    private void fillMusicPlayerFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.music_player_container,
                MusicPlayerFragment.newInstance(mTrack.getTrackPreviewUrl()), MusicPlayerFragment.TAG)
                .commit();
    }

    private void fillArtistProfileFragment() {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        transaction.replace(R.id.artist_view_container,
                ArtistProfileFragment.newInstance(mTrack.getArtistViewUrl()))
                .commit();
    }
}