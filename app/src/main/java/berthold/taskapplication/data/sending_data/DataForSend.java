package berthold.taskapplication.data.sending_data;

import com.google.gson.annotations.Expose;

/**
 * Created by User on 26.03.2017.
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
