package yurinevenchenov1970.github.com.itunesfinder.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test for {@link TracksResponse}
 *
 * @author Yuri Nevenchenov on 8/30/2017.
 */
public class TracksResponseTest {

    public static final String TEST_FILE = "tracks_responce_test_file.json";
    private TracksResponse mExpected;
    private ObjectMapper mObjectMapper;

    @Before
    public void setUp() {
        mObjectMapper = new ObjectMapper();
        mExpected = getExpectedObject();
    }

    @Test
    public void testParseObject() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE);
        TracksResponse actual = mObjectMapper.readValue(stream, TracksResponse.class);
        assertThat(mExpected, is(actual));
    }

    private TracksResponse getExpectedObject() {
        TracksResponse response = new TracksResponse();
        response.setTrackCount(2);
        response.setTracks(getTracks());
        return response;
    }

    private List<Track> getTracks() {
        return Arrays.asList(getFirstTrack(), getSecondTrack());
    }

    private Track getFirstTrack() {
        return getTrack(
                "Yes",
                "Owner of a Lonely Heart",
                "https://itunes.apple.com/us/artist/yes/id154011?uo=4",
                "http://a865.phobos.apple.com/us/r30/Music69/v4/69/85/4d/69854de3-1fd2-cb7d-c333-e8674562db4c/mzaf_4789660816680776018.plus.aac.p.m4a",
                "http://is1.mzstatic.com/image/thumb/Music49/v4/7d/6f/e6/7d6fe6a5-a08f-c631-fc5d-1a113f7bd1be/source/100x100bb.jpg",
                1.29);
    }

    private Track getSecondTrack() {
        return getTrack(
                "Yes",
                "Roundabout",
                "https://itunes.apple.com/us/artist/yes/id154011?uo=4",
                "http://a457.phobos.apple.com/us/r30/Music/85/4e/b0/mzm.kmkbgxgj.aac.p.m4a",
                "http://is2.mzstatic.com/image/thumb/Music2/v4/66/c5/f9/66c5f9b2-ce4d-81ef-d6b1-8cf21808d8e7/source/100x100bb.jpg",
                1.29);
    }

    private Track getTrack(String artistName,
                           String trackName,
                           String artistViewUrl,
                           String previewUrl,
                           String coverUrl,
                           double price) {
        Track track = new Track();
        track.setArtistName(artistName);
        track.setTrackName(trackName);
        track.setArtistViewUrl(artistViewUrl);
        track.setTrackPreviewUrl(previewUrl);
        track.setCoverUrl(coverUrl);
        track.setTrackPrice(price);
        return track;
    }
}