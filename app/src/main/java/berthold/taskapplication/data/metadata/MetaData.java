
package berthold.taskapplication.data.metadata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Мета-данные на основе ответа сервера
 */
public class MetaData {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("fields")
    @Expose
    private List<Field> fields;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

}