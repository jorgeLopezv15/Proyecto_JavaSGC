package controles;

import database.Agenda;
import modulos.Contacto;

public class AgendaControl {

    private Agenda agenda;

    public AgendaControl(Agenda agenda){
        this.agenda = agenda;
    }

    public void agregarContacto(String nombre, String telefono, String email){
        if (!nombre.isEmpty() && !telefono.isEmpty() && !email.isEmpty()) {
            agenda.agregar(new Contacto(nombre, email, telefono));
        }
    }
}