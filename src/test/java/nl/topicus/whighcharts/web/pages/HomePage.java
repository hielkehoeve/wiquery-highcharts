package nl.topicus.whighcharts.web.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.util.ListModel;

import nl.topicus.whighcharts.web.pages.examples.AjaxBasicPage;
import nl.topicus.whighcharts.web.pages.examples.AreaBasicPage;
import nl.topicus.whighcharts.web.pages.examples.AreaInvertedPage;
import nl.topicus.whighcharts.web.pages.examples.AreaMissingPage;
import nl.topicus.whighcharts.web.pages.examples.AreaNegativePage;
import nl.topicus.whighcharts.web.pages.examples.AreasplinePage;

public class HomePage extends BasePage
{
	private static final long serialVersionUID = 1L;

	public HomePage()
	{
		List<Class< ? extends Page>> pages = new ArrayList<Class< ? extends Page>>();

		/*
		 * Examples
		 */
		pages.add(AreaBasicPage.class);
		pages.add(AreaInvertedPage.class);
		pages.add(AreaMissingPage.class);
		pages.add(AreaNegativePage.class);
		pages.add(AreasplinePage.class);
		pages.add(AjaxBasicPage.class);

		ListView<Class< ? extends Page>> links =
			new ListView<Class< ? extends Page>>("links", new ListModel<Class< ? extends Page>>(
				pages))
			{
				private static final long serialVersionUID = 1L;

				@Override
				protected void populateItem(ListItem<Class< ? extends Page>> item)
				{
					BookmarkablePageLink<Void> link =
						new BookmarkablePageLink<Void>("link", item.getModelObject());
					item.add(link);
					link.add(new Label("label", item.getModelObject().getName()));
				}
			};
		add(links);
	}
}
