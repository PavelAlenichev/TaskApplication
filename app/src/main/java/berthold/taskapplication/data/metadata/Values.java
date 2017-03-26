
package berthold.taskapplication.data.metadata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Values {

    @SerializedName("empty")
    @Expose
    private String empty;
    @SerializedName("k1")
    @Expose
    private String k1;
    @SerializedName("k2")
    @Expose
    private String k2;
    @SerializedName("k3")
    @Expose
    private String k3;
    @SerializedName("k4")
    @Expose
    private String k4;

    public String getEmpty() {
        return empty;
    }

    public void setEmpty(String empty) {
        this.empty = empty;
    }

    public String getK1() {
        return k1;
    }

    public void setK1(String k1) {
        this.k1 = k1;
    }

    public String getK2() {
        return k2;
    }

    public void setK2(String k2) {
        this.k2 = k2;
    }

    public String getK3() {
        return k3;
    }

    public void setK3(String k3) {
        this.k3 = k3;
    }

    public String getK4() {
        return k4;
    }

    public void setK4(String k4) {
        this.k4 = k4;
    }

    public List<String> getAll() {
        ArrayList<String> wholeValues = new ArrayList<>();

        wholeValues.add(getEmpty());
        wholeValues.add(getK1());
        wholeValues.add(getK2());
        wholeValues.add(getK3());
        wholeValues.add(getK4());

        return wholeValues;
    }

}
