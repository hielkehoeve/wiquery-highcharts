package nl.topicus.whighcharts.components;

import java.io.IOException;
import java.util.Collection;

import nl.topicus.whighcharts.components.modules.WHighChartsExportingJavaScriptResourceReference;
import nl.topicus.whighcharts.options.WHighChartOptions;
import nl.topicus.whighcharts.options.axis.IWHighChartAxisCategoriesProvider;
import nl.topicus.whighcharts.options.axis.WHighChartAxisOptions;
import nl.topicus.whighcharts.options.series.ISeries;
import nl.topicus.whighcharts.options.series.ISeriesEntry;

import org.apache.wicket.Application;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.model.IModel;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.odlabs.wiquery.core.IWiQueryPlugin;
import org.odlabs.wiquery.core.javascript.JsStatement;

public class WHighChart<V, E extends ISeriesEntry<V>> extends WebMarkupContainer implements
		IWiQueryPlugin
{
	private static final long serialVersionUID = 1L;

	private WHighChartOptions<V, E> options = new WHighChartOptions<V, E>(this);

	public WHighChart(String id)
	{
		this(id, null);
	}

	public WHighChart(String id, IModel< ? extends Collection< ? extends ISeries<V, E>>> model)
	{
		super(id, model);
		setOutputMarkupId(true);
	}

	public WHighChartOptions<V, E> getOptions()
	{
		return options;
	}

	@Override
	public void renderHead(IHeaderResponse response)
	{
		response.renderJavaScriptReference(WHighChartsJavaScriptResourceReference.get());
		response.renderJavaScriptReference(WHighChartsExtraJavaScriptResourceReference.get());

		if (getOptions().getExporting().getEnabled() != null
			&& getOptions().getExporting().getEnabled().booleanValue())
			response.renderJavaScriptReference(WHighChartsExportingJavaScriptResourceReference
				.get());
	}

	@Override
	public JsStatement statement()
	{
		ObjectMapper mapper = new ObjectMapper();
		mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);
		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);

		if (Application.exists()
			&& RuntimeConfigurationType.DEVELOPMENT
				.equals(Application.get().getConfigurationType()))
			mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);

		String optionsStr = "{}";
		try
		{
			if (getModel() != null)
			{
				getOptions().getSeries().clear();
				getOptions().getSeries().addAll(getModel().getObject());

				if (getModel() instanceof IWHighChartAxisCategoriesProvider)
				{
					IWHighChartAxisCategoriesProvider categoriesProvider =
						(IWHighChartAxisCategoriesProvider) getModel();

					for (WHighChartAxisOptions xAxis : getOptions().getxAxis())
					{
						if (xAxis.getCategories() == null || xAxis.getCategories().isEmpty())
						{
							xAxis.setCategories(categoriesProvider.getxAxisCategories());
						}
					}

					for (WHighChartAxisOptions yAxis : getOptions().getyAxis())
					{
						if (yAxis.getCategories() == null || yAxis.getCategories().isEmpty())
						{
							yAxis.setCategories(categoriesProvider.getyAxisCategories());
						}
					}
				}
			}

			optionsStr = mapper.writeValueAsString(options);
		}
		catch (JsonGenerationException e)
		{
			e.printStackTrace();
		}
		catch (JsonMappingException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		JsStatement jsStatement =
			new JsStatement().append("var " + getMarkupId() + " = new Highcharts.Chart( "
				+ optionsStr + " );\n");

		return jsStatement;
	}

	@SuppressWarnings("unchecked")
	public IModel< ? extends Collection< ? extends ISeries<V, E>>> getModel()
	{
		return (IModel< ? extends Collection< ? extends ISeries<V, E>>>) getDefaultModel();
	}
}
