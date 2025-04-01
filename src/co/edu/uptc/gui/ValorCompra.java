package co.edu.uptc.gui;

public class ValorCompra {

    private double subtotal;
    private double impuestos;
    private double total;

    public ValorCompra(double subtotal, double impuestos, double total) {
        this.subtotal = subtotal;
        this.impuestos = impuestos;
        this.total = total;
    }

    public ValorCompra() {}

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuestos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
