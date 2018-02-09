package com.butter.dinnerssc.net;


import com.butter.dinnerssc.model.response.BaseResp;
import com.butter.dinnerssc.model.response.CaiPiaoResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by zgyi on 2017-12-28.
 */

public interface Api {
    @GET("{url}")
    Call<BaseResp<CaiPiaoResponse>> requestUrl(@Path("url") String url);
}
