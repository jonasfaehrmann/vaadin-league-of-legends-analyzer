
package de.leuphana.leuphalytics.model.champion.entities;

import java.util.List;

public class Block {

    private String type;
    private List<Item> items = null;
    private Boolean recMath;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Boolean getRecMath() {
        return recMath;
    }

    public void setRecMath(Boolean recMath) {
        this.recMath = recMath;
    }

}
