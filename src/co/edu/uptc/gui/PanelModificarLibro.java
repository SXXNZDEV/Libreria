package co.edu.uptc.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;

public class PanelModificarLibro extends JDialog {

    private JLabel labelTitulo;
    private JLabel labelLibro;
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
    private JComboBox txtLibro;
    private JTextField txtNombre;
    private JTextField txtAutor;
    private JTextField txtAnoPublicacion;
    private JComboBox txtCategoria;
    private JTextField txtEditorial;
    private JTextField txtNumeroPaginas;
    private JTextField txtPrecio;
    private JTextField txtCantidad;
    private JComboBox txtFormato;
    private JButton botonModificar;
    private JButton botonCancelar;

    // Métodos GET (devuelven el texto ingresado)
    public String getISBN() {
        return txtISBN.getText();
    }

    public String getLibro() {
        return txtLibro.getSelectedItem().toString();
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getAutor() {
        return txtAutor.getText();
    }

    public String getAnoPublicacion() {
        return txtAnoPublicacion.getText();
    }

    public String getCategoria() {
        return txtCategoria.getSelectedItem().toString();
    }

    public String getEditorial() {
        return txtEditorial.getText();
    }

    public String getNumeroPaginas() {
        return txtNumeroPaginas.getText();
    }

    public String getPrecio() {
        return txtPrecio.getText();
    }

    public String getCantidad() {
        return txtCantidad.getText();
    }

    public String getFormato() {
        return txtFormato.getSelectedItem().toString();
    }

    // Métodos SET (asignan un nuevo valor)
    public void setISBN(String isbn) {
        txtISBN.setText(isbn);
    }

    public void setLibro(String libro) {
        txtLibro.setSelectedItem(libro);
    }

    public void setNombre(String nombre) {
        txtNombre.setText(nombre);
    }

    public void setAutor(String autor) {
        txtAutor.setText(autor);
    }

    public void setAnoPublicacion(String anoPublicacion) {
        txtAnoPublicacion.setText(anoPublicacion);
    }

    public void setCategoria(String categoria) {
        txtCategoria.setSelectedItem(categoria);
    }

    public void setEditorial(String editorial) {
        txtEditorial.setText(editorial);
    }

    public void setNumeroPaginas(String numeroPaginas) {
        txtNumeroPaginas.setText(numeroPaginas);
    }

    public void setPrecio(String precio) {
        txtPrecio.setText(precio);
    }

    public void setCantidad(String cantidad) {
        txtCantidad.setText(cantidad);
    }

    public void setFormato(String formato) {
        txtFormato.setSelectedItem(formato);
    }

    public void listarLibros(String[] titulosLibros) {
        txtLibro.removeAllItems();
        for (int i = 0; i < titulosLibros.length; i++) {
            txtLibro.addItem(titulosLibros[i]);
        }
    }


    public PanelModificarLibro (Eventos eventos, EventosItemListener eventosItemListener) {
        setTitle("Modificar Libro");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 10, 5, 10);

        inicializarAtributos();
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 30));

        botonModificar.addActionListener(eventos);
        botonModificar.setActionCommand(eventos.MODIFICAR_LIBRO);
        botonCancelar.addActionListener(eventos);
        botonCancelar.setActionCommand(eventos.CANCELAR_MODIFICACION_LIBRO);
        txtLibro.addItemListener(eventosItemListener);

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(labelTitulo, gbc);
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 1;
        add(labelLibro, gbc);
        gbc.gridx = 1;
        add(txtLibro, gbc);
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(labelISBN, gbc);
        gbc.gridx = 1;
        add(txtISBN, gbc);
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(labelNombre, gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);
        gbc.gridy = 4;
        gbc.gridx = 0;
        add(labelAutor, gbc);
        gbc.gridx = 1;
        add(txtAutor, gbc);
        gbc.gridy = 5;
        gbc.gridx = 0;
        add(labelAnoPublicacion, gbc);
        gbc.gridx = 1;
        add(txtAnoPublicacion, gbc);
        gbc.gridy = 6;
        gbc.gridx = 0;
        add(labelCategoria, gbc);
        gbc.gridx = 1;
        add(txtCategoria, gbc);
        gbc.gridy = 7;
        gbc.gridx = 0;
        add(labelEditorial, gbc);
        gbc.gridx = 1;
        add(txtEditorial, gbc);
        gbc.gridy = 8;
        gbc.gridx = 0;
        add(labelNumeroPaginas, gbc);
        gbc.gridx = 1;
        add(txtNumeroPaginas, gbc);
        gbc.gridy = 9;
        gbc.gridx = 0;
        add(labelPrecio, gbc);
        gbc.gridx = 1;
        add(txtPrecio, gbc);
        gbc.gridy = 10;
        gbc.gridx = 0;
        add(labelCantidad, gbc);
        gbc.gridx = 1;
        add(txtCantidad, gbc);
        gbc.gridy = 11;
        gbc.gridx = 0;
        add(labelFormato, gbc);
        gbc.gridx = 1;
        add(txtFormato, gbc);
        gbc.gridy = 12;
        gbc.gridx = 0;
        add(botonModificar, gbc);
        gbc.gridx = 1;
        add(botonCancelar, gbc);

        setResizable(false);
        setModal(true);
        setSize(450, 500);
        setLocationRelativeTo(null);
    }

    public void inicializarAtributos() {

        labelLibro = new JLabel("Libro: ");
        labelTitulo = new JLabel("Modificar Libro");
        labelISBN = new JLabel("ISBN*:");
        labelNombre = new JLabel("Nombre*:");
        labelAutor = new JLabel("Autor*:");
        labelAnoPublicacion = new JLabel("Año de Publicación:");
        labelCategoria = new JLabel("Categoría*:");
        labelEditorial = new JLabel("Editorial:");
        labelNumeroPaginas = new JLabel("Número de Páginas:");
        labelPrecio = new JLabel("Precio*:");
        labelCantidad = new JLabel("Cantidad*:");
        labelFormato = new JLabel("Formato*:");

        txtISBN = new JTextField(20);
        txtISBN.setEditable(false);
        txtISBN.setBackground(Color.WHITE);
        txtNombre = new JTextField(20);
        txtAutor = new JTextField(20);
        txtAnoPublicacion = new JTextField(4);
        txtEditorial = new JTextField(20);
        txtNumeroPaginas = new JTextField(5);
        txtPrecio = new JTextField(10);
        txtCantidad = new JTextField(5);

        txtLibro = new JComboBox<>(); //Lo agregue para el que se elija, los JTextField se llenen con la informacion de cada libro.

        String[] categorias = {"Ficción", "No Ficción", "Misterio", "Ciencia", "Historia"};
        txtCategoria = new JComboBox<>(categorias);

        String[] formatos = {"Tapa Dura", "Tapa Blanda", "Ebook"};
        txtFormato = new JComboBox<>(formatos);

        botonModificar = new JButton("Modificar");
        botonCancelar = new JButton("Salir");
    }
}
