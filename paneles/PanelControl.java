package paneles;

import javax.swing.*;
import java.awt.*;

public class PanelControl extends JPanel  {

    public JTextField txtNombre = new JTextField(15);
    public JTextField txtEmail = new JTextField(15);
    public JTextField txtTelefono = new JTextField(15);

    public PanelControl(){
        setLayout(new GridLayout(3,2));

        add(new JLabel("Nombre"));
        add(txtNombre);

        add(new JLabel("Telefono"));
        add(txtTelefono);

        add(new JLabel("Email"));
        add(txtEmail);
    }

    public void limpiarCampos(){
        txtNombre.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
    }
}