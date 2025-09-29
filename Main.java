import database.Agenda;
import views.AgendaApp;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        AgendaApp agendaApp = new AgendaApp(agenda);
        agendaApp.setVisible(true);
    }
}