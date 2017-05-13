package de.alpharogroup.dropdownchoices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.wicket.util.lang.Args;

import lombok.Getter;
import lombok.Setter;

/**
 * The class {@link TwoDropDownChoicesBean}.
 *
 * @param <T>
 *            the generic type of the model object
 */
public class TwoDropDownChoicesBean<T> implements Serializable
{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The selected root option. */
	@Getter
	@Setter
	private T selectedRootOption;

	/** The selected child option. */
	@Getter
	@Setter
	private T selectedChildOption;

	/** The models map. */
	@Getter
	@Setter
	private Map<T, List<T>> modelsMap = new HashMap<>();

	/** The root choices. */
	@Getter
	private final List<T> rootChoices;

	/** The child choices. */
	@SuppressWarnings("unused")
	private List<T> childChoices;

	/**
	 * Instantiates a new {@link TwoDropDownChoicesBean}.
	 *
	 * @param selectedOption
	 *            the selected option
	 * @param modelsMap
	 *            the models map
	 */
	public TwoDropDownChoicesBean(final T selectedOption, final Map<T, List<T>> modelsMap)
	{
		this.modelsMap = Args.notNull(modelsMap, "modelsMap");

		rootChoices = new ArrayList<>(modelsMap.keySet());

		this.selectedRootOption = Args.notNull(selectedOption, "selectedOption");

		this.childChoices = getChildChoices();

	}

	/**
	 * Gets the child choices.
	 *
	 * @return the child choices
	 */
	public List<T> getChildChoices()
	{
		final List<T> childChoices = getModelsMap().get(getSelectedRootOption());
		if (childChoices == null || childChoices.isEmpty())
		{
			return Collections.emptyList();
		}
		return childChoices;
	}

}
