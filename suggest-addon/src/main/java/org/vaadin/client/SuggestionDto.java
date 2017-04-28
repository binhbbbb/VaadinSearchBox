package org.vaadin.client;

import java.io.Serializable;
import java.util.Objects;

public class SuggestionDto implements Serializable {

    private int itemId;
    private String displayString;

    public SuggestionDto() {
    }

    public SuggestionDto(int itemId, String displayString) {
        this.itemId = itemId;
        this.displayString = displayString;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SuggestionDto that = (SuggestionDto) o;
        return itemId == that.itemId &&
                Objects.equals(displayString, that.displayString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, displayString);
    }
}
