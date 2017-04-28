package org.vaadin.client;

public class SuggestBoxState extends com.vaadin.shared.AbstractComponentState {
    public int delayMilis = 800;
    public int queryMinLength = 4;
    public String placeHolderText = "type query here";
    public boolean clearInputAfterSelection;
}