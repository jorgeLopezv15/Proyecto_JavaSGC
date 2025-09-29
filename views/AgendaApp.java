package views;

import javax.swing.*;
import java.awt.*;

import controles.AgendaControl;
import database.Agenda;
import modulos.Contacto;
import paneles.PanelControl;

public class AgendaApp extends JFrame {
    private Agenda agenda;
    private AgendaControl control;
    private JList<Contacto> listaContactos;

    public AgendaApp(Agenda agenda) {
        this.agenda = agenda;
        this.control = new AgendaControl(agenda);

        setTitle("Agenda de Contactos");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        PanelControl panelContacto = new PanelControl();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        // Mejora de la lista de contactos
        listaContactos = new JList<>();
        listaContactos.setBackground(new Color(245, 245, 245)); // gris claro
        listaContactos.setSelectionBackground(new Color(173, 216, 230)); // azul claro al seleccionar
        listaContactos.setFont(new Font("Arial", Font.PLAIN, 14));

        JScrollPane scrollLista = new JScrollPane(listaContactos);
        scrollLista.setBorder(BorderFactory.createTitledBorder("Contactos"));
        scrollLista.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Panel de botones
        JPanel panelBoton = new JPanel();
        panelBoton.setBackground(new Color(220, 220, 220)); // gris claro
        btnAgregar.setBackground(new Color(144, 238, 144)); // verde claro
        btnEliminar.setBackground(new Color(255, 182, 193)); // rosa claro
        panelBoton.add(btnAgregar);
        panelBoton.add(btnEliminar);

        // Layout principal
        setLayout(new BorderLayout());
        add(panelContacto, BorderLayout.NORTH);
        add(panelBoton, BorderLayout.CENTER);
        add(scrollLista, BorderLayout.SOUTH);

        // Acción agregar
        btnAgregar.addActionListener(e -> {
            String nombre = panelContacto.txtNombre.getText();
            String telefono = panelContacto.txtTelefono.getText();
            String email = panelContacto.txtEmail.getText();
            control.agregarContacto(nombre, telefono, email);
            refrescarLista();
            panelContacto.limpiarCampos();
        });

        // Acción eliminar
        btnEliminar.addActionListener(e -> {
            int index = listaContactos.getSelectedIndex();
            if (index >= 0) {
                agenda.geContactos().remove(index);
                refrescarLista();
            }
        });

        refrescarLista();
    }

    private void refrescarLista() {
        Contacto[] datos = agenda.geContactos().toArray(new Contacto[0]);
        listaContactos.setListData(datos);
    }
}
