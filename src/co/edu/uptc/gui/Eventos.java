package co.edu.uptc.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Eventos implements ActionListener {

    public final static String INICIAR_SESION = "Iniciar Sesion";
    public final static String GESTIONAR_LIBROS = "Gestionar Libros";
    public final static String REGRESAR = "Regresar";
    public final static String REGISTRAR_LIBRO = "Registrar Libros";
    public final static String MODIFICAR_LIBRO = "Modificar Libros";
    public final static String REGISTRAR_USUARIO = "Registrar Usuarios";
    public final static String ELIMINAR_LIBRO = "EliminarL Libros";
    public final static String CANCELAR_REGISTRO_USUARIO = "Cancelar Usuario";
    public final static String CANCELAR_REGISTRO_LIBRO = "Cancelar Libro";
    public final static String CANCELAR_MODIFICACION_LIBRO = "Cancelar Modificacion Libro";
    public final static String CONTINUAR_INICIAR_SESION = "Continuar";
    public final static String CATALOGO = "Catalogo";
    public final static String CERRAR_SESION = "Cerrar Sesion";
    public final static String PERFIL = "Perfil";
    public final static String CARRITO = "Carrito";
    public final static String COMPRAS = "Compras";
    public final static String ACTUALIZAR_DATOS_USUARIO = "Actualizar Datos Usuario";
    public final static String AUMENTAR_CANTIDAD_CARRITO = "Aumentar Cantidad";
    public final static String DISMINUIR_CANTIDAD_CARRITO = "Disminuir Cantidad";;

    public VentanaPrincipal ventana;

    public Eventos(VentanaPrincipal ventana) {
        this.ventana = ventana;
    }

    public void actionPerformed(ActionEvent e) {

        String eventoStr = e.getActionCommand();

        switch (eventoStr) {
            case INICIAR_SESION -> ventana.activarPanelInicioSesion();
            case GESTIONAR_LIBROS -> ventana.activarPanelGestionLibros();
            case REGRESAR -> ventana.activarFuncionRegresar();
            case REGISTRAR_LIBRO -> ventana.activarPanelRegistrarLibros();
            case MODIFICAR_LIBRO -> ventana.activarPanelModificarLibro();
            case REGISTRAR_USUARIO -> ventana.activarPanelRegistrarUsuario();
            case CANCELAR_REGISTRO_LIBRO -> ventana.activarCancelarRegistroLibro();
            case CANCELAR_REGISTRO_USUARIO -> ventana.activarCancelarRegistroUsuario();
            case CANCELAR_MODIFICACION_LIBRO -> ventana.activarCancelarModificacionLibro();
            case CONTINUAR_INICIAR_SESION -> ventana.activarPanelVenta();
            case CATALOGO -> ventana.activarPanelCatalogo();
            case CERRAR_SESION -> ventana.activarCerrarSesion();
            case PERFIL -> ventana.activarPanelPerfil();
            case CARRITO -> ventana.activarCarrito();
            case COMPRAS -> ventana.activarPanelCompras();
            case ELIMINAR_LIBRO -> ventana.activarEliminarLibros();
            case ACTUALIZAR_DATOS_USUARIO -> ventana.activarActualizarDatosUsuario();
        }
    }
}
