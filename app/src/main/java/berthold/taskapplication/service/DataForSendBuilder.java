package berthold.taskapplication.service;

import berthold.taskapplication.data.sending_data.DataForSend;
import berthold.taskapplication.data.sending_data.Form;
import berthold.taskapplication.service.factories.Factory;

/**
 * Builder для отправки данных на сервер
 */
public class DataForSendBuilder {

    private Factory factory;

    public DataForSendBuilder(Factory factory) {
        this.factory = factory;
    }

    /**
     * Создает объект класса для сериализации
     *
     * @return возвращает объект для сериализации
     */
    public DataForSend build() {

        DataForSend dataForSend = new DataForSend();
        Form form = new Form();
        form.setTextEdit(factory.getValues().get(0));
        form.setNumEdit(factory.getValues().get(1));
        form.setSpinValue(factory.getValues().get(2));
        dataForSend.setForm(form);

        return dataForSend;
    }
}