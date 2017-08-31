package yurinevenchenov1970.github.com.itunesfinder.bean;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Test file for {@link Track}
 *
 * @author Yuri Nevenchenov on 8/30/2017.
 */
public class TrackTest {

    public static final String TEST_FILE = "track_test_file.json";

    private Track mExpectedTrack;
    private ObjectMapper mObjectMapper;

    @Before
    public void setUp() throws Exception {
        mExpectedTrack = getTrack();
        mObjectMapper = new ObjectMapper();
    }

    @Test
    public void testParseJson() throws IOException {
        InputStream stream = this.getClass().getClassLoader().getResourceAsStream(TEST_FILE);
        Track actualTrack = mObjectMapper.readValue(stream, Track.class);
        assertThat(mExpectedTrack, is(actualTrack));
    }

    private Track getTrack() {
        Track track = new Track();
        track.setArtistName("Yes");
        track.setArtistViewUrl("https://itunes.apple.com/us/artist/yes/id154011?uo=4");
        track.setCoverUrl("http://is1.mzstatic.com/image/thumb/Music49/v4/7d/6f/e6/7d6fe6a5-a08f-c631-fc5d-1a113f7bd1be/source/100x100bb.jpg");
        track.setTrackName("Owner of a Lonely Heart");
        track.setTrackPreviewUrl("http://a865.phobos.apple.com/us/r30/Music69/v4/69/85/4d/69854de3-1fd2-cb7d-c333-e8674562db4c/mzaf_4789660816680776018.plus.aac.p.m4a");
        track.setTrackPrice(1.29);
        return track;
    }
}