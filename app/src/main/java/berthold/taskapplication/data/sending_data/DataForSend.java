package berthold.taskapplication.data.sending_data;

import com.google.gson.annotations.Expose;

/**
 * Класс для оправки данных на сервер
 */
public class DataForSend {

    @Expose
    private Form form;

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}