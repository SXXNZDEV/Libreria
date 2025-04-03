package co.edu.uptc.gui;

import co.edu.uptc.negocio.Libro;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.text.NumberFormat;

public class PanelLibroEliminar extends JPanel {

    private JLabel labelTitulo;
    private JLabel labelAutor;
    private JLabel labelPrecio;
    private JCheckBox checkBox;
    private NumberFormat format;
    private GridBagConstraints gbc;
    private Libro libro;

    public Libro getLibro() {
        return libro;
    }

    public boolean isSelected() {
        boolean isSelected = checkBox.isSelected();
        return isSelected;
    }

    public PanelLibroEliminar(VentanaPrincipal ventanaPrincipal, Libro libro) {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        setBorder(new LineBorder(Color.WHITE));
        setBorder(new LineBorder(Color.black));

        initAtributos(libro);
        personalizar();

        gbc.fill = GridBagConstraints.CENTER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(2, 0, 2, 0);
        add(labelTitulo, gbc);

        gbc.gridy = 1;
        add(labelAutor, gbc);

        gbc.gridy = 2;
        add(labelPrecio, gbc);

        gbc.gridy = 3;
        add(checkBox, gbc);
    }

    private void initAtributos(Libro libro) {
        format = NumberFormat.getCurrencyInstance();
        format.setMinimumFractionDigits(0);
        labelTitulo = new JLabel(libro.getTitulo());
        labelAutor = new JLabel(libro.getAutor());
        labelPrecio = new JLabel(String.valueOf(format.format(libro.getPrecioVenta())));
        checkBox = new JCheckBox();
        this.libro = libro;
    }

    public void personalizar() {
        labelTitulo.setFont(new Font("Sunglasses", Font.BOLD, 10));
        labelPrecio.setFont(new Font("Sunglasses", Font.BOLD, 10));
        labelAutor.setFont(new Font("Sunglasses", Font.BOLD, 10));
    }
}
