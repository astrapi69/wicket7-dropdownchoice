package de.alpharogroup.dropdownchoices;

import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.FormComponentPanel;
import org.apache.wicket.markup.html.form.IChoiceRenderer;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.lang.Args;

import lombok.Getter;

public class DoubleDropDownPanel<T> extends FormComponentPanel<TwoDropDownChoicesBean<T>>
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant ROOT_CHOICE_ID. */
	public static final String ROOT_CHOICE_ID = "rootChoice";

	/** The Constant CHILD_CHOICE_ID. */
	public static final String CHILD_CHOICE_ID = "childChoice";

	/** The root choice. */
	@Getter
	private DropDownChoice<T> rootChoice;

	/** The child choice. */
	@Getter
	private DropDownChoice<T> childChoice;

	/** The root renderer. */
	@Getter
	private final IChoiceRenderer<T> rootRenderer;

	/** The child renderer. */
	@Getter
	private final IChoiceRenderer<T> childRenderer;

	public DoubleDropDownPanel(String id, IModel<TwoDropDownChoicesBean<T>> model,
		final IChoiceRenderer<T> rootRenderer, final IChoiceRenderer<T> childRenderer)
	{
		super(id, Args.notNull(model, "model"));
		this.rootRenderer = Args.notNull(rootRenderer, "rootRenderer");
		this.childRenderer = Args.notNull(childRenderer, "childRenderer");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onInitialize()
	{
		super.onInitialize();
		add(rootChoice = newRootChoice(ROOT_CHOICE_ID, getModel()));
		add(childChoice = newChildChoice(CHILD_CHOICE_ID, getModel()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertInput()
	{
		final TwoDropDownChoicesBean<T> modelObject = getModelObject();
		setConvertedInput(modelObject);
	}

	/**
	 * Factory method for creating the new child {@link DropDownChoice}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new child {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new child {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newChildChoice(final String id,
		final IModel<TwoDropDownChoicesBean<T>> model)
	{
		final IModel<T> selectedChildOptionModel = new PropertyModel<>(model,
			"selectedChildOption");
		final IModel<List<T>> childChoicesModel = PropertyModel.of(model, "childChoices");
		final DropDownChoice<T> cc = new DropDownChoice<T>(id, selectedChildOptionModel,
			childChoicesModel, this.childRenderer)
		{
			@Override
			protected void onSelectionChanged(Object newSelection)
			{
				DoubleDropDownPanel.this.onChildSelectionChanged(newSelection);
			}

			@Override
			protected boolean wantOnSelectionChangedNotifications()
			{
				return true;
			}
		};
		cc.setOutputMarkupId(true);
		cc.add(new AjaxFormComponentUpdatingBehavior("change")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, RuntimeException e)
			{
				DoubleDropDownPanel.this.onChildChoiceError(target, e);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				DoubleDropDownPanel.this.onChildChoiceUpdate(target);
			}
		});
		return cc;
	}

	/**
	 * Factory method for creating the new root {@link DropDownChoice}. This method is invoked in
	 * the constructor from the derived classes and can be overridden so users can provide their own
	 * version of a new root {@link DropDownChoice}.
	 *
	 * @param id
	 *            the id
	 * @param model
	 *            the model
	 * @return the new root {@link DropDownChoice}.
	 */
	protected DropDownChoice<T> newRootChoice(final String id,
		final IModel<TwoDropDownChoicesBean<T>> model)
	{
		final IModel<T> selectedRootOptionModel = PropertyModel.of(model, "selectedRootOption");
		final IModel<List<T>> rootChoicesModel = PropertyModel.of(model, "rootChoices");

		final DropDownChoice<T> rc = new DropDownChoice<T>(id, selectedRootOptionModel,
			rootChoicesModel, this.rootRenderer)
		{
			@Override
			protected void onSelectionChanged(Object newSelection)
			{
				DoubleDropDownPanel.this.onRootSelectionChanged(newSelection);
			}

			@Override
			protected boolean wantOnSelectionChangedNotifications()
			{
				return true;
			}
		};
		rc.add(new AjaxFormComponentUpdatingBehavior("change")
		{
			/** The Constant serialVersionUID. */
			private static final long serialVersionUID = 1L;

			@Override
			protected void onError(AjaxRequestTarget target, RuntimeException e)
			{
				DoubleDropDownPanel.this.onRootChoiceError(target, e);
			}

			/**
			 * {@inheritDoc}
			 */
			@Override
			protected void onUpdate(final AjaxRequestTarget target)
			{
				DoubleDropDownPanel.this.onRootChoiceUpdate(target);
			}
		});
		return rc;
	}

	protected void onChildChoiceError(AjaxRequestTarget target, RuntimeException e)
	{
		System.err.println("onChildChoiceError:");
	}

	protected void onChildChoiceUpdate(final AjaxRequestTarget target)
	{
		target.add(DoubleDropDownPanel.this.childChoice);
	}

	protected void onChildSelectionChanged(Object newSelection)
	{
		System.err.println("onChildSelectionChanged:" + newSelection);
	}

	protected void onRootChoiceError(AjaxRequestTarget target, RuntimeException e)
	{
		System.err.println("onRootChoiceError:");
	}

	protected void onRootChoiceUpdate(final AjaxRequestTarget target)
	{
		target.add(DoubleDropDownPanel.this.childChoice);
		System.err.println("onRootChoiceUpdate:"+getModelObject().getSelectedRootOption());
	}

	protected void onRootSelectionChanged(Object newSelection)
	{
		System.err.println("onRootSelectionChanged:" + newSelection);
	}
}
