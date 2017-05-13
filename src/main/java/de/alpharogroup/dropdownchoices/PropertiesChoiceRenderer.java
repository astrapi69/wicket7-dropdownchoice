package de.alpharogroup.dropdownchoices;

import java.util.List;

import org.apache.wicket.Component;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;


/**
 * The Class {@link PropertiesChoiceRenderer}.
 *
 * @author Asterios Raptis
 */
public class PropertiesChoiceRenderer implements IChoiceRenderer<String>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The relative component used for lookups. */
	private final Component component;

	/** The component class. */
	private final Class<?> componentClass;

	/**
	 * Instantiates a new {@link PropertiesChoiceRenderer}.
	 *
	 * @param component
	 *            the component
	 * @param componentClass
	 *            the component class
	 */
	public PropertiesChoiceRenderer(final Component component, final Class<?> componentClass)
	{
		this.component = component;
		this.componentClass = componentClass;
	}

	public Class<?> getComponentClass()
	{
		return componentClass;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getDisplayValue(final String object)
	{
		final IModel<String> resourceModel = ResourceModelFactory.newResourceModel(
			ResourceBundleKey.builder().key(object).defaultValue("").build(), component);
		final String value = resourceModel.getObject();
		return value;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getIdValue(final String object, final int index)
	{
		return object;
	}

	/**
	 * Gets the object.
	 *
	 * @param id
	 *            the id
	 * @param choices
	 *            the choices
	 * @return the object
	 */
	@Override
	public String getObject(final String id, final IModel<? extends List<? extends String>> choices)
	{
		// override this method...
		return null;
	}
}
