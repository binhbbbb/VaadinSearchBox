package org.vaadin.client;

import com.vaadin.shared.communication.ClientRpc;

public interface SuggestBoxClientRpc extends ClientRpc {
    void showSuggestions(String originalQuery, SuggestionDto[] suggestions);
}