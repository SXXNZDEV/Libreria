package co.edu.uptc.gui;

import co.edu.uptc.negocio.TipoLibro;

import javax.swing.*;
import java.awt.*;

public class PanelRegistrarLibro extends JDialog {

    private JLabel labelTitulo;
    private JLabel labelISBN;
    private JLabel labelNombre;
    private JLabel labelAutor;
    private JLabel labelAnoPublicacion;
    private JLabel labelCategoria;
    private JLabel labelEditorial;
    private JLabel labelNumeroPaginas;
    private JLabel labelPrecio;
    private JLabel labelCantidad;
    private JLabel labelFormato;
    private JTextField txtISBN;
    private JTextField txtNombre;
    private JTextField txtAutor;
    private JTextField txtAnoPublicacion;
    private JComboBox txtCategoria;
    private JTextField txtEditorial;
    private JTextField txtNumeroPaginas;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JComboBox txtFormato;
    private JButton botonAgregar;
    private JButton botonCancelar;

    public String getTxtIsbn() {
        return txtISBN.getText();
    }

    public String getTxtNombre() {
        return txtNombre.getText();
    }

    public String getTxtAutor() {
        return txtAutor.getText();
    }

    public String getTxtAnoPublicacion() {
        return txtAnoPublicacion.getText();
    }

    public String getTxtCategoria() {
        return (String) txtCategoria.getSelectedItem();
    }

    public String getTxtEditorial() {
        return txtEditorial.getText();
    }

    public String getTxtNumeroPaginas() {
        return txtNumeroPaginas.getText();
    }

    public String getTxtPrecio() {
        return txtPrecio.getText();
    }

    public String getTxtCantidad() {
        return txtCantidad.getText();
    }

    public TipoLibro getTxtFormato() {
        return txtFormato.getSelectedItem().toString() == String.valueOf(TipoLibro.FISICO) ? TipoLibro.FISICO : TipoLibro.DIGITAL;
    }

    public void setTxtIsbn(String texto) {
        txtISBN.setText(texto);
    }

    public void setTxtNombre(String texto) {
        txtNombre.setText(texto);
    }

    public void setTxtAutor(String texto) {
        txtAutor.setText(texto);
    }

    public void setTxtAnoPublicacion(String texto) {
        txtAnoPublicacion.setText(texto);
    }

    public void setTxtCategoria(String index) {
        txtCategoria.setSelectedItem(index);
    }

    public void setTxtEditorial(String texto) {
        txtEditorial.setText(texto);
    }

    public void setTxtNumeroPaginas(String texto) {
        txtNumeroPaginas.setText(texto);
    }

    public void setTxtPrecio(String texto) {
        txtPrecio.setText(texto);
    }

    public void setTxtCantidad(String texto) {
        txtCantidad.setText(texto);
    }

    public void setTxtFormato(String index) {
        txtFormato.setSelectedItem(index);
    }

    public PanelRegistrarLibro(Eventos eventos) {
        setTitle("Registrar Libro");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10);

        inicializarAtributos();
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 30));

        botonAgregar.addActionListener(eventos);
        botonAgregar.setActionCommand(eventos.REGISTRAR_LIBRO);
        botonCancelar.addActionListener(eventos);
        botonCancelar.setActionCommand(eventos.CANCELAR_REGISTRO_LIBRO);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(labelTitulo, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        add(labelISBN, gbc);
        gbc.gridx = 1;
        add(txtISBN, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(labelNombre, gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(labelAutor, gbc);
        gbc.gridx = 1;
        add(txtAutor, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        add(labelAnoPublicacion, gbc);
        gbc.gridx = 1;
        add(txtAnoPublicacion, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        add(labelCategoria, gbc);
        gbc.gridx = 1;
        add(txtCategoria, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        add(labelEditorial, gbc);
        gbc.gridx = 1;
        add(txtEditorial, gbc);
        gbc.gridy = 7;
        gbc.gridx = 0;
        add(labelNumeroPaginas, gbc);
        gbc.gridx = 1;
        add(txtNumeroPaginas, gbc);
        gbc.gridy = 8;
        gbc.gridx = 0;
        add(labelPrecio, gbc);
        gbc.gridx = 1;
        add(txtPrecio, gbc);
        gbc.gridy = 9;
        gbc.gridx = 0;
        add(labelCantidad, gbc);
        gbc.gridx = 1;
        add(txtCantidad, gbc);
        gbc.gridy = 10;
        gbc.gridx = 0;
        add(labelFormato, gbc);
        gbc.gridx = 1;
        add(txtFormato, gbc);
        gbc.gridy = 11;
        gbc.gridx = 0;
        add(botonAgregar, gbc);
        gbc.gridx = 1;
        add(botonCancelar, gbc);

        setResizable(false);
        setModal(true);
        setSize(500, 500);
        setLocationRelativeTo(null);
    }

    public void inicializarAtributos() {

        labelTitulo = new JLabel("Registrar Libro");
        labelISBN = new JLabel("ISBN*:");
        labelNombre = new JLabel("Nombre*:");
        labelAutor = new JLabel("Autor*:");
        labelAnoPublicacion = new JLabel("Año de Publicación:");
        labelCategoria = new JLabel("Categoría*:");
        labelEditorial = new JLabel("Editorial:");
        labelNumeroPaginas = new JLabel("Número de Páginas*:");
        labelPrecio = new JLabel("Precio*:");
        labelCantidad = new JLabel("Cantidad*:");
        labelFormato = new JLabel("Formato*:");

        txtISBN = new JTextField(20);
        txtNombre = new JTextField(20);
        txtAutor = new JTextField(20);
        txtAnoPublicacion = new JTextField(4);
        txtEditorial = new JTextField(20);
        txtNumeroPaginas = new JTextField(5);
        txtPrecio = new JTextField(10);
        txtCantidad = new JTextField(5);

        String[] categorias = {"Ficción", "No Ficción", "Misterio", "Ciencia", "Historia"};
        txtCategoria = new JComboBox<>(categorias);

        String[] formatos = {String.valueOf(TipoLibro.DIGITAL), String.valueOf(TipoLibro.FISICO)};
        txtFormato = new JComboBox<>(formatos);

        botonAgregar = new JButton("Agregar");
        botonCancelar = new JButton("Cancelar");
    }
}
