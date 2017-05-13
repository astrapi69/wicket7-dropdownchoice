package de.alpharogroup.dropdownchoices;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;


/**
 * The class {@link DoubleDropDownChoicesPage}.
 */
public class DoubleDropDownChoicesPage extends WebPage
{

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	private TwoDropDownChoicesBean<String> twoDropDownChoicesBean;

	private DoubleDropDownPanel<String> doubleDropDownPanel;

	public DoubleDropDownChoicesPage(final PageParameters pageParameters)
	{
		super(pageParameters);
		this.twoDropDownChoicesBean = new TwoDropDownChoicesBean<>("trademark.audi",
			DatabaseManager.initializeModelMap());
	}

	@Override
	protected void onBeforeRender()
	{
		final AttributeModifier sam = new AttributeModifier("style",
			"width: 200px; margin-bottom: 20px;");
		final AttributeModifier samClass = new AttributeModifier("class", "nowrap");

		final AttributeModifier samSize = new AttributeModifier("size", "3");

		doubleDropDownPanel.getRootChoice().add(sam);
		doubleDropDownPanel.getRootChoice().add(samSize);
		doubleDropDownPanel.getRootChoice().add(samClass);

		doubleDropDownPanel.getChildChoice().add(sam);
		doubleDropDownPanel.getChildChoice().add(new AttributeModifier("size", "4"));
		super.onBeforeRender();
	}

	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		final IModel<TwoDropDownChoicesBean<String>> boundOptionModel = new PropertyModel<>(this,
			"twoDropDownChoicesBean");

		final Form<TwoDropDownChoicesBean<String>> selectOptionForm = new Form<>("selectOptionForm",
			boundOptionModel);

		add(selectOptionForm);
		doubleDropDownPanel = new DoubleDropDownPanel<>("doubleDropDownPanel", boundOptionModel,
			new PropertiesChoiceRenderer(this, this.getClass()),
			new PropertiesChoiceRenderer(this, this.getClass()));

		selectOptionForm.add(doubleDropDownPanel);

		// Create submit button for the form
		final Button entryButton = new AjaxButton("entryButton", selectOptionForm)
		{
			@Override
			protected void onSubmit(AjaxRequestTarget target, Form<?> form)
			{
				String selected = doubleDropDownPanel.getRootChoice().getModelObject();
				super.onSubmit(target, form);
				System.out.println(selected);
			}
		};

		selectOptionForm.add(entryButton);
	}

}
