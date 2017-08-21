package yurinevenchenov1970.github.com.itunesfinder.net;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;
import yurinevenchenov1970.github.com.itunesfinder.bean.TracksResponse;

/**
 * @author Yuri Nevenchenov on 8/21/2017.
 */

public interface TrackService {
    @POST("search")
    Call<TracksResponse> getTracks(@Query("term") String keyWord);
}
