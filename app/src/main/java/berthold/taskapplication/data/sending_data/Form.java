package berthold.taskapplication.data.sending_data;

import com.google.gson.annotations.Expose;

/**
 * Значения полей данных
 */
public class Form {

    @Expose
    private String textEdit;

    @Expose
    private String numEdit;

    @Expose
    private String spinValue;

    public String getTextEdit() {
        return textEdit;
    }

    public void setTextEdit(String textEdit) {
        this.textEdit = textEdit;
    }

    public String getNumEdit() {
        return numEdit;
    }

    public void setNumEdit(String numEdit) {
        this.numEdit = numEdit;
    }

    public String getSpinValue() {
        return spinValue;
    }

    public void setSpinValue(String spinValue) {
        this.spinValue = spinValue;
    }
}