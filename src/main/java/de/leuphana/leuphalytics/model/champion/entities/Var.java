
package de.leuphana.leuphalytics.model.champion.entities;

import java.util.List;

public class Var {

    private String key;
    private String link;
    private List<Double> coeff = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Double> getCoeff() {
        return coeff;
    }

    public void setCoeff(List<Double> coeff) {
        this.coeff = coeff;
    }

}
