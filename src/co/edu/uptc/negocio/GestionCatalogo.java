package co.edu.uptc.negocio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class GestionCatalogo {

    private Catalogo catalogo;
    private ManejoLibroJSON manejoLibroJSON;

    public GestionCatalogo() {
        manejoLibroJSON = new ManejoLibroJSON();
    }

    public ManejoLibroJSON getManejoLibroJSON() {
        return manejoLibroJSON;
    }

    public void setManejoLibroJSON(ManejoLibroJSON manejoLibroJSON) {
        this.manejoLibroJSON = manejoLibroJSON;
    }

    public Catalogo getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(Catalogo catalogo) {
        this.catalogo = catalogo;
    }

    public Map<String, ArrayList<Libro>> listarLibros() throws IOException {
        return manejoLibroJSON.leerLibro();
    }


}
