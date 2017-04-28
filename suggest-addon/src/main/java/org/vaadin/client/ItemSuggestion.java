package org.vaadin.client;

import com.google.gwt.user.client.ui.SuggestOracle;

import java.io.Serializable;

public class ItemSuggestion implements SuggestOracle.Suggestion, Serializable {
    private String displayString;
    private int itemId;

    public ItemSuggestion() {
    }
    public ItemSuggestion(SuggestionDto suggestionDto) {
        this.displayString = suggestionDto.getDisplayString();
        this.itemId = suggestionDto.getItemId();
    }

    @Override
    public String getDisplayString() {
        return displayString;
    }

    public void setDisplayString(String displayString) {
        this.displayString = displayString;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public String getReplacementString() {
        return displayString;
    }
}
