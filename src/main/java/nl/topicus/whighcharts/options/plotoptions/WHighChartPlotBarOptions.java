package nl.topicus.whighcharts.options.plotoptions;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
@JsonSerialize(include = Inclusion.NON_NULL)
public class WHighChartPlotBarOptions extends
		AbstractWHighChartPlotChartOptions<WHighChartPlotBarOptions>
{
	private static final long serialVersionUID = 1L;

	/**
	 * Allow this series' points to be selected by clicking on the markers, bars or pie
	 * slices. Defaults to false.
	 */
	// private Boolean allowPointSelect;

	/**
	 * Enable or disable the initial animation when a series is displayed. Since version
	 * 2.1, the animation can be set as a configuration object. Please note that this
	 * option only applies to the initial animation of the series itself. For other
	 * animations, see #chart => animation and the animation parameter under the API
	 * methods. The following properties are supported: duration The duration of the
	 * animation in milliseconds. easing When using jQuery as the general framework, the
	 * easing can be set to linear or swing. More easing functions are available with the
	 * use of jQuery plug-ins, most notably the jQuery UI suite. See the jQuery docs. When
	 * using MooToos as the general framework, use the property name transition instead of
	 * easing. Defaults to true.
	 */
	private Boolean animation;

	/**
	 * The main color or the series. In line type series it applies to the line and the
	 * point markers unless otherwise specified. In bar type series it applies to the bars
	 * unless a color is specified per point. The default value is pulled from the
	 * options.colors array.
	 */
	private String color;

	/**
	 * You can set the cursor to "pointer" if you have click events attached to the
	 * series, to signal to the user that the points and lines can be clicked. Defaults to
	 * ''.
	 */
	// private WHighChartPointerType cursor;

	/**
	 * Defines the appearance of the data labels, static labels for each point.
	 */
	private WHighChartPlotBarLabelsOptions dataLabels;

	/**
	 * Whether to display this particular series or series type in the legend. Since 2.1,
	 * pies are not shown in the legend by default. Defaults to false.
	 */
	private Boolean showInLegend;

	private Number minPointLength;

	public Boolean getAnimation()
	{
		return animation;
	}

	public WHighChartPlotBarOptions setAnimation(Boolean animation)
	{
		this.animation = animation;
		return this;
	}

	public String getColor()
	{
		return color;
	}

	public WHighChartPlotBarOptions setColor(String color)
	{
		this.color = color;
		return this;
	}

	public WHighChartPlotBarLabelsOptions getDataLabels()
	{
		if (dataLabels == null)
			dataLabels = new WHighChartPlotBarLabelsOptions();

		return dataLabels;
	}

	public WHighChartPlotBarOptions setDataLabels(WHighChartPlotBarLabelsOptions dataLabels)
	{
		this.dataLabels = dataLabels;
		return this;
	}

	public Boolean getShowInLegend()
	{
		return showInLegend;
	}

	public WHighChartPlotBarOptions setShowInLegend(Boolean showInLegend)
	{
		this.showInLegend = showInLegend;
		return this;
	}

	public Number getMinPointLength()
	{
		return minPointLength;
	}

	public WHighChartPlotBarOptions setMinPointLength(Number minPointLength)
	{
		this.minPointLength = minPointLength;
		return this;
	}

}
