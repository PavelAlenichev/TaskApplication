package berthold.taskapplication.service;

import berthold.taskapplication.metadata.InformationResponse;
import berthold.taskapplication.metadata.MetaData;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by User on 23.03.2017.
 */

public interface ClevertecApi {

    @POST("meta")
    Call<MetaData> getMetaData();

    @POST("data")
    Call<InformationResponse> getAnswer(@Query("json")String json);
}
