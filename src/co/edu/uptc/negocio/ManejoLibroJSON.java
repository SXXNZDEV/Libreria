package co.edu.uptc.negocio;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManejoLibroJSON {
    private Map<String, ArrayList<Libro>> mapLibros;
    private File file;
    private String ruta;
    private ObjectMapper objectMapper;

    public ManejoLibroJSON() {
        mapLibros = new HashMap<>();
        ruta = "C:\\Users\\Usuario\\OneDrive\\Proyectos\\Libreria\\src\\co\\edu\\uptc\\persistencia\\libro.json";
        file = new File(ruta);
        objectMapper = new ObjectMapper();
    }

    public Map<String, ArrayList<Libro>> getMapLibros() {
        leerLibro();
        return mapLibros;
    }

    public void setMapLibros(Map<String, ArrayList<Libro>> mapLibros) {
        this.mapLibros = mapLibros;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public File getFile() {
        return file;
    }

    public String getRuta() {
        return ruta;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void crearLibro(Libro libroIngresado) throws IllegalArgumentException, IOException {
        try {
            file.createNewFile();
            mapLibros = objectMapper.readValue(file, new TypeReference<Map<String, ArrayList<Libro>>>() {
            });
            if (isRegistrado(libroIngresado)) {
                throw new IllegalArgumentException("El libro con ISBN " + libroIngresado.getIsbn() + " ya existe.");
            }
            ArrayList<Libro> libros = buscarLibro(libroIngresado);
            if (libros == null) {
                ArrayList<Libro> libroNuevo = new ArrayList<>();
                libroNuevo.add(libroIngresado);
                mapLibros.put(libroIngresado.getCategoria(), libroNuevo);
            } else {
                libros.add(libroIngresado);
            }
            objectMapper.writeValue(file, mapLibros);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, ArrayList<Libro>> leerLibro() {
        try {
            mapLibros = objectMapper.readValue(file, new TypeReference<HashMap<String, ArrayList<Libro>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapLibros;
    }

    public void modificarLibro(Libro libro) throws IOException {
        mapLibros = leerLibro();
        ArrayList<Libro> categoria = buscarLibro(libro);
        for (Libro libroArray : categoria) {
            if (libro.getIsbn().equals(libroArray.getIsbn())) {
                libroArray.setIsbn(libro.getIsbn());
                libroArray.setTitulo(libro.getTitulo());
                libroArray.setEditorial(libro.getEditorial());
                libroArray.setPrecioVenta(libro.getPrecioVenta());
                libroArray.setNumeroPaginas(libro.getNumeroPaginas());
                libroArray.setTipoLibro(libro.getTipoLibro());
                libroArray.setAutor(libro.getAutor());
                libroArray.setAnioPublicacion(libro.getAnioPublicacion());
                libroArray.setCategoria(libro.getCategoria());
                libroArray.aumentarCantidad(libro.getCantidadDisponible());
                break;
            }
        }
        objectMapper.writeValue(file, mapLibros);
    }

    public boolean isRegistrado(Libro libroBuscado) {
        for (ArrayList<Libro> Categoria : mapLibros.values()) {
            for (Libro libro : Categoria) {
                if (libro.getIsbn().equals(libroBuscado.getIsbn())) {
                    return true;
                }
            }
        }
        return false;
    }

    public ArrayList<Libro> buscarLibro(Libro libroBuscado) {
        for (ArrayList<Libro> categoria : mapLibros.values()) {
            for (Libro libro : categoria) {
                if (libroBuscado.getCategoria().equals(libro.getCategoria())) {
                    return categoria;
                }
            }
        }
        return null;
    }
}
