package pl.wwsi.gawarski.technologieinternetowe.gui.about;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import pl.wwsi.gawarski.technologieinternetowe.gui.main.MainView;

@Route(value = "", layout = MainView.class)
@PageTitle("About")
public class AboutView extends Div {

    private static final String aboutPartOne = "Projekt zaliczeniowy na przedmiot 'Technologie Internetowe', przy starcie aplikacji załadowane są dane testowe, dla wygody baza danych zapisana do pliku, z każdym uruchomieniem aplikacji baza jest tworzona na nowo ";
    private static final String aboutPartTwo = ", login:null password:null";


    public AboutView() {
        setId("about-view");
        Anchor anchor = new Anchor("http://localhost:8080/h2-console", "link do bazy danych");
        Text textPartOne = new Text(aboutPartOne);
        Text textPartTwo = new Text(aboutPartTwo);
        add(textPartOne, anchor, textPartTwo);
    }

}
