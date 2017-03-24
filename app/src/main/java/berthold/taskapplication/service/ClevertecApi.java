package berthold.taskapplication.service;

import berthold.taskapplication.metadata.MetaData;
import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by User on 23.03.2017.
 */

public interface ClevertecApi {

    @POST("meta")
    Call<MetaData> getMetaData();
}
