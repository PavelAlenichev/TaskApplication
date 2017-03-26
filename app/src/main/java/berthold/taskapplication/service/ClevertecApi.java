package berthold.taskapplication.service;

import berthold.taskapplication.data.metadata.MetaData;
import berthold.taskapplication.data.result_response.InformationResponse;
import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * POST-запросы на адреса http://test.clevertec.ru/tt/meta и http://test.clevertec.ru/tt/data
 */
public interface ClevertecApi {

    /**
     * получение мета-данных с сервера
     *
     * @return Observable<MetaData> с информацией о форме
     */
    @POST("meta")
    Observable<MetaData> getMetaData();

    /**
     * @param json - поля данных
     * @return Observable<InformationResponse> - с информацией с сервера
     */
    @POST("data")
    Observable<InformationResponse> getAnswer(@Query("json") String json);
}