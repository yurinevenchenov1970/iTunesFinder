package yurinevenchenov1970.github.com.itunesfinder;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import yurinevenchenov1970.github.com.itunesfinder.bean.Track;
import yurinevenchenov1970.github.com.itunesfinder.bean.TracksResponse;
import yurinevenchenov1970.github.com.itunesfinder.net.ApiClient;
import yurinevenchenov1970.github.com.itunesfinder.net.TrackService;
import yurinevenchenov1970.github.com.itunesfinder.utils.Utils;

/**
 * Main Activity contains search view and container for data
 *
 * @author Yuri Nevenchenov on 8/20/2017.
 */
public class MainActivity extends AppCompatActivity implements MainFragment.OnItemClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.search)
    SearchView mSearchView;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setToolbar();
        createSearchView();
    }

    @Override
    public void onItemClick(Track track) {
        startActivity(TrackDetailActivity.createExplicitIntent(this, track));
    }

    private void createSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Utils.isNetworkConnected(getApplicationContext())) {
                    showProgressBar();
                    getDataFromServer(query);
                    mSearchView.clearFocus();
                } else {
                    showConnectionMessage();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void setToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void getDataFromServer(String query) {
        TrackService service = ApiClient.getClient().create(TrackService.class);
        Call<TracksResponse> responseCall = service.getTracks(query);

        responseCall.enqueue(new Callback<TracksResponse>() {
            @Override
            public void onResponse(Call<TracksResponse> call, Response<TracksResponse> response) {
                TracksResponse tracksResponse = response.body();
                if (tracksResponse != null) {
                    startFragment(tracksResponse);
                    hideProgressBar();
                }
            }

            @Override
            public void onFailure(Call<TracksResponse> call, Throwable t) {
                Log.e(TAG, "failure" + t.getMessage());
            }
        });
    }

    private void startFragment(TracksResponse response) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, MainFragment.newInstance(response))
                .addToBackStack(MainFragment.TAG)
                .commit();
    }

    private void showConnectionMessage() {
        Toast.makeText(this, getString(R.string.no_connection), Toast.LENGTH_LONG).show();
    }

    private void showProgressBar() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}