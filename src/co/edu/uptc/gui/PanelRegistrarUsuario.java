package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;

public class PanelRegistrarUsuario extends JDialog {
    private JLabel labelNombre;
    private JLabel labelCorreo;
    private JLabel labelContrasena;
    private JLabel labelDireccion;
    private JLabel labelTelefono;
    private JLabel labelTipoCliente;
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JTextField txtContrasena;
    private JTextField txtDireccion;
    private JTextField txtTelefono;
    private JComboBox cbTipoCliente;
    private JLabel labelTitulo;
    private JButton botonRegistrar;
    private JButton botonCancelar;

    public String getTxtNombre() {
        return txtNombre.getText();
    }

    public String getTxtCorreo() {
        return txtCorreo.getText();
    }

    public String  getTxtContrasena() {
        return txtContrasena.getText();
    }

    public String getTxtDireccion() {
        return txtDireccion.getText();
    }

    public String getTxtTelefono() {
        return txtTelefono.getText();
    }

    public String getCbTipoCliente() {
        return (String) cbTipoCliente.getSelectedItem();
    }

    public void setTxtNombre(String nombre) {
        this.txtNombre.setText(nombre);
    }

    public void setTxtCorreo(String correo) {
        this.txtCorreo.setText(correo);
    }

    public void setTxtContrasena(String contrasena) {
        this.txtContrasena.setText(contrasena);
    }

    public void setTxtDireccion(String direccion) {
        this.txtDireccion.setText(direccion);
    }

    public void setTxtTelefono(String telefono) {
        this.txtTelefono.setText(telefono);
    }

    public PanelRegistrarUsuario(Eventos eventos) {
        setTitle("Registrar Persona");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10);
        inicializarAtributos();
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 20));

        botonRegistrar.addActionListener(eventos);
        botonRegistrar.setActionCommand(eventos.REGISTRAR_USUARIO);
        botonCancelar.addActionListener(eventos);
        botonCancelar.setActionCommand(eventos.CANCELAR_REGISTRO_USUARIO);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(labelTitulo, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        add(labelNombre, gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(labelDireccion, gbc);
        gbc.gridx = 1;
        add(txtDireccion, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(labelTelefono, gbc);
        gbc.gridx = 1;
        add(txtTelefono, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        add(labelTipoCliente, gbc);
        gbc.gridx = 1;
        add(cbTipoCliente, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        add(labelCorreo, gbc);
        gbc.gridx = 1;
        add(txtCorreo, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        add(labelContrasena, gbc);
        gbc.gridx = 1;
        add(txtContrasena, gbc);
        gbc.gridy = 7;
        gbc.gridx = 0;
        add(botonRegistrar, gbc);
        gbc.gridx = 1;
        add(botonCancelar, gbc);

        setResizable(false);
        setModal(true);
        setSize(600, 350);
        setLocationRelativeTo(null);
    }

    public void inicializarAtributos() {
        labelNombre = new JLabel("Nombre*:");
        labelCorreo = new JLabel("Correo Electrónico*:");
        labelContrasena = new JLabel("Contraseña*: ");
        labelDireccion = new JLabel("Dirección*:");
        labelTelefono = new JLabel("Teléfono*:");
        labelTipoCliente = new JLabel("Tipo de Cliente*:");
        txtNombre = new JTextField(20);
        txtCorreo = new JTextField(20);
        txtContrasena = new JTextField(20);
        txtDireccion = new JTextField(20);
        txtTelefono = new JTextField(20);
        String[] tiposCliente = {"Regular", "Premium"};
        cbTipoCliente = new JComboBox<>(tiposCliente);
        labelTitulo = new JLabel("Registrar Usuario");
        botonRegistrar = new JButton("Registrar");
        botonCancelar = new JButton("Cancelar");
    }
}
