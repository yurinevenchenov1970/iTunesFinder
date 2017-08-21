package yurinevenchenov1970.github.com.itunesfinder.bean;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.List;

/**
 * @author Yuri Nevenchenov on 8/21/2017.
 */

public class TracksResponse implements Serializable {

    private int mTrackCount;

    @NonNull
    private List<Track> mTracks;

    public TracksResponse() {
        // Empty constructor needed by Jackson
    }

    @JsonGetter("resultCount")
    public int getTrackCount() {
        return mTrackCount;
    }

    @JsonSetter("resultCount")
    public void setTrackCount(int trackCount) {
        mTrackCount = trackCount;
    }

    @NonNull
    @JsonGetter("results")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Track> getTracks() {
        return mTracks;
    }

    @JsonSetter("results")
    public void setTracks(@NonNull List<Track> tracks) {
        mTracks = tracks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TracksResponse that = (TracksResponse) o;
        return mTrackCount == that.mTrackCount &&
                Objects.equal(mTracks, that.mTracks);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(mTrackCount, mTracks);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mTrackCount", mTrackCount)
                .add("mTracks", mTracks)
                .toString();
    }
}
