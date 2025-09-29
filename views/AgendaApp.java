package views;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import controles.AgendaControl;
import database.Agenda;
import modulos.Contacto;
import paneles.PanelControl;

public class AgendaApp extends JFrame {
    private Agenda agenda;
    private AgendaControl control;

    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;

    public AgendaApp(Agenda agenda) {
        this.agenda = agenda;
        this.control = new AgendaControl(agenda);

        setTitle("Agenda de Contactos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        
        PanelControl panelContacto = new PanelControl();

        
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");

        
        btnAgregar.setBackground(new Color(0, 153, 0)); 
        btnAgregar.setForeground(Color.WHITE);

        btnEliminar.setBackground(new Color(204, 0, 0)); 
        btnEliminar.setForeground(Color.WHITE);

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnAgregar);
        panelBoton.add(btnEliminar);

        // Crear tabla con columnas Nombre, Teléfono y Email
        String[] columnas = {"Nombre", "Teléfono", "Email"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setFillsViewportHeight(true);
        JScrollPane scrollTabla = new JScrollPane(tablaContactos);

        // Panel central para botones y tabla
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.add(panelBoton, BorderLayout.NORTH);
        panelCentro.add(scrollTabla, BorderLayout.CENTER);

        // Layout principal
        setLayout(new BorderLayout());
        add(panelContacto, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

        // Acción agregar
        btnAgregar.addActionListener(e -> {
            String nombre = panelContacto.txtNombre.getText();
            String telefono = panelContacto.txtTelefono.getText();
            String email = panelContacto.txtEmail.getText();

            control.agregarContacto(nombre, telefono, email);
            refrescarTabla();
            panelContacto.limpiarCampos();
        });

    
        btnEliminar.addActionListener(e -> {
            int fila = tablaContactos.getSelectedRow();
            if (fila >= 0) {
                agenda.geContactos().remove(fila);
                refrescarTabla();
            }
        });

        refrescarTabla();
    }

    private void refrescarTabla() {
        modeloTabla.setRowCount(0); 
        for (Contacto c : agenda.geContactos()) {
            modeloTabla.addRow(new Object[]{c.getNombre(), c.getTelefono(), c.getEmail()});
        }
    }
}