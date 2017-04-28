package org.vaadin.client;

import com.google.gwt.user.client.Timer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SuggestOracle extends com.google.gwt.user.client.ui.SuggestOracle implements SuggestBoxClientRpc {

    private final SuggestBoxConnector suggestBoxConnector;
    private final Map<String, CallbackAndRequest> callbackCache = new HashMap<String, CallbackAndRequest>();

    public SuggestOracle(SuggestBoxConnector suggestBoxConnector) {
        this.suggestBoxConnector = suggestBoxConnector;
        suggestBoxConnector.register(this);
    }

    @Override
    public void requestSuggestions(final Request request, final Callback callback) {
        final String query = request.getQuery();

        if (query.length() < suggestBoxConnector.getState().queryMinLength) {
            return;
        }

        if (!query.equals(suggestBoxConnector.getWidget().getText())) {
            return;
        }

        final Runnable runnable = () -> {
            callbackCache.put(query, new CallbackAndRequest(callback, request));
            suggestBoxConnector.getRpc().suggestFor(query);
        };

        int delayMilis = suggestBoxConnector.getState().delayMilis;

        if (delayMilis > 0) {
            new Timer() {
                @Override
                public void run() {
                    if (query.equals(suggestBoxConnector.getWidget().getText())) {
                        runnable.run();
                    }
                }
            }.schedule(delayMilis);
        } else {
            runnable.run();
        }
    }

    @Override
    public void showSuggestions(String originalQuery, SuggestionDto[] suggestionDtos) {
        CallbackAndRequest callback = callbackCache.get(originalQuery);

        if (callback == null) {
            return;
        }

        callbackCache.clear();

        if (!suggestBoxConnector.getWidget().getText().equals(originalQuery)) {
            return;
        }

        Set<Suggestion> suggestions = new HashSet<>(suggestionDtos.length);

        for (SuggestionDto suggestionDto : suggestionDtos) {
            suggestions.add(new ItemSuggestion(suggestionDto));
        }

        callback.getCallback().onSuggestionsReady(callback.getRequest(), new Response(suggestions));
    }

    private static class CallbackAndRequest {
        private Callback callback;
        private Request request;

        private CallbackAndRequest(Callback callback, Request request) {
            this.callback = callback;
            this.request = request;
        }

        public Callback getCallback() {
            return callback;
        }

        public Request getRequest() {
            return request;
        }
    }
}
