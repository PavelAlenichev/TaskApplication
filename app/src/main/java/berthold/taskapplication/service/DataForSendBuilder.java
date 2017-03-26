package berthold.taskapplication.service;

import berthold.taskapplication.data.sending_data.DataForSend;
import berthold.taskapplication.data.sending_data.Form;

/**
 * Builder для отправки данных на сервер
 */
public class DataForSendBuilder {
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