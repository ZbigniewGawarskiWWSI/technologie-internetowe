package pl.wwsi.gawarski.technologieinternetowe.gui.about;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.wwsi.gawarski.technologieinternetowe.gui.main.MainView;

@Route(value = "about", layout = MainView.class)
@PageTitle("About")
public class AboutView extends Div {

    public AboutView() {
        setId("about-view");
        add(new Text("Content placeholder"));
    }

}
