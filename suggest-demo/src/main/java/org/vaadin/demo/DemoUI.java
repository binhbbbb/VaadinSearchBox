package org.vaadin.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import org.vaadin.SuggestBox;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("MyComponent Add-on Demo")
@SuppressWarnings("serial")
public class DemoUI extends UI
{

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        VerticalLayout layout = new VerticalLayout();

        layout.setSizeFull();

        final SuggestBox suggestBox = new CitySuggestBox();

        Layout suggestBoxLabelContainer = new HorizontalLayout();

        suggestBoxLabelContainer.addComponent(suggestBox);

        layout.addComponent(suggestBoxLabelContainer);
        layout.setComponentAlignment(suggestBoxLabelContainer, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
}
