package org.vaadin.client;

import com.vaadin.shared.communication.ServerRpc;

public interface SuggestBoxServerRpc extends ServerRpc {
    void suggestFor(String query);

    void selectionChanged(int itemId);
}
