package de.alpharogroup.dropdownchoices;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

	public HomePage(final PageParameters parameters) {
		super(parameters);

		final Link<String> doubleDropDownChoicesLink = new Link<String>("doubleDropDownChoicesLink")
		{

			/**
			 * The serialVersionUID.
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick()
			{
				final DoubleDropDownChoicesPage doubleDropDownChoicesPage = new DoubleDropDownChoicesPage(
					null);
				setResponsePage(doubleDropDownChoicesPage);
			}
		};
		add(doubleDropDownChoicesLink);
		final Label doubleDropDownChoicesLbl = new Label("doubleDropDownChoicesLbl",
			"Show two DoubleDropDownChoices page");
		doubleDropDownChoicesLink.add(doubleDropDownChoicesLbl);

    }
}
