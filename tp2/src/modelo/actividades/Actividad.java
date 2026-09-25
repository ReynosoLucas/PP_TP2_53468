package modelo.actividades;
import excepciones.CupoExcedidoException;
import modelo.Estudiante;
import modelo.Inscripcion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Actividad implements Serializable {
    private int id;
    private String titulo;
    private int cupoMaximo;
    public static final int CUPO_MINIMO = 5;
    private List<Inscripcion> inscripciones;

    public Actividad(int id, String titulo, int cupoMaximo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMaximo = cupoMaximo;
        this.inscripciones = new ArrayList<>();
    }


    public Inscripcion inscribir(Estudiante estudiante) throws CupoExcedidoException {
        if (inscripciones.size() >= cupoMaximo) {
            throw new CupoExcedidoException("No hay más cupo en la actividad: " + titulo);
        }
        Inscripcion nueva = new Inscripcion(estudiante);
        inscripciones.add(nueva);
        return nueva;
    }

    public void mostrarInscripciones() {
        System.out.println("Inscripciones de " + titulo + ":");
        for (Inscripcion i : inscripciones) {
            System.out.println("- " + i.getEstudiante().getNombre() + " (" + i.getEstado() + ")");
        }
    }

    public final void mostrarIdentificacion() {
        System.out.println("Actividad ID " + id + ": " + titulo);
    }

    public abstract double calcularCostoMateriales();
    public abstract String getTipo();

    public List<Inscripcion> getInscripciones() { return inscripciones; }
}