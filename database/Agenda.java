package database;

import java.util.ArrayList;
import modulos.Contacto;

public class Agenda {

    private ArrayList<Contacto> contactos = new ArrayList<>();

    public void agregar(Contacto c){
        contactos.add(c);
    }

    public ArrayList<Contacto> geContactos(){
        return contactos;
    }
}