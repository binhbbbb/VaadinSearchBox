package org.vaadin.client;

import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;

import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(org.vaadin.SuggestBox.class)
public class SuggestBoxConnector extends AbstractComponentConnector {

    private final SuggestBoxServerRpc rpc = RpcProxy.create(SuggestBoxServerRpc.class, this);

    @Override
    protected Widget createWidget() {
        final SuggestOracle oracle = new org.vaadin.client.SuggestOracle(this);

        final com.google.gwt.user.client.ui.SuggestBox searchBoxWidget = new com.google.gwt.user.client.ui.SuggestBox(oracle);

        searchBoxWidget.addSelectionHandler(
                event -> {
                    if (!(event.getSelectedItem() instanceof ItemSuggestion)) {
                        return;
                    }

                    ItemSuggestion itemSuggestion = (ItemSuggestion) event.getSelectedItem();

                    if (itemSuggestion == null) {
                        return;
                    }

                    getRpc().selectionChanged(itemSuggestion.getItemId());

                    if (getState().clearInputAfterSelection) {
                        searchBoxWidget.setText("");
                    }
                }
        );

        return searchBoxWidget;
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        SuggestBoxState state = getState();

        getWidget().getValueBox().getElement().setAttribute("placeHolder", state.placeHolderText);
    }

    @Override
    public com.google.gwt.user.client.ui.SuggestBox getWidget() {
        return (com.google.gwt.user.client.ui.SuggestBox) super.getWidget();
    }

    @Override
    public SuggestBoxState getState() {
        return (SuggestBoxState) super.getState();
    }

    public void register(SuggestBoxClientRpc suggestBoxClientRpc) {
        registerRpc(SuggestBoxClientRpc.class, suggestBoxClientRpc);
    }

    public SuggestBoxServerRpc getRpc() {
        return rpc;
    }
}
