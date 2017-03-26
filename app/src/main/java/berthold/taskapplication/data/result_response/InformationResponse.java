package berthold.taskapplication.data.result_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Результат, приходящий с сервера после отправки туда значений полей
 * На основе ответа сервера
 */
public class InformationResponse {


    @SerializedName("result")
    @Expose
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

}