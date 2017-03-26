package berthold.taskapplication.service;

import berthold.taskapplication.data.metadata.MetaData;
import berthold.taskapplication.data.result_response.InformationResponse;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by User on 23.03.2017.
 */

public interface ClevertecApi {

    @POST("meta")
    Observable<MetaData> getMetaData();

    @POST("data")
    Observable<InformationResponse> getAnswer(@Query("json")String json);
}
