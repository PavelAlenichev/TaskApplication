package berthold.taskapplication.service;

import berthold.taskapplication.data.sending_data.DataForSend;
import berthold.taskapplication.data.sending_data.Form;

/**
 * Created by User on 26.03.2017.
 */

public class DataForSendBouilder {
    public DataForSend build() {

        DataForSend dataForSend = new DataForSend();
        Form form = new Form();
        form.setTextEdit("");
        form.setNumEdit("");
        form.setSpinValue("");
        dataForSend.setForm(form);

        return dataForSend;
    }
}
