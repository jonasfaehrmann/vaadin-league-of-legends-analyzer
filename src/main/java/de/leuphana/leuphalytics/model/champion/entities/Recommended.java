
package de.leuphana.leuphalytics.model.champion.entities;

import java.util.List;

public class Recommended {

    private String champion;
    private String title;
    private String type;
    private String map;
    private String mode;
    private Boolean priority;
    private List<Block> blocks = null;

    public String getChampion() {
        return champion;
    }

    public void setChampion(String champion) {
        this.champion = champion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Boolean getPriority() {
        return priority;
    }

    public void setPriority(Boolean priority) {
        this.priority = priority;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

}
