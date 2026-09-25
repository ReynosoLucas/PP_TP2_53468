package modelo;
import modelo.actividades.Actividad;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EventoUniversitario implements Serializable {
    private final String id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos = 0;

    private Sala sala;
    private List<Actividad> actividades;

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.id = id;
        this.titulo = titulo;
        this.costoBase = costoBase;
        this.gratuito = gratuito;
        this.actividades = new ArrayList<>();
        cantidadEventos++;
    }

    public void asignarSala(Sala sala) {
        this.sala = sala;
    }

    public void agregarActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public double calcularCostoEstimado() {
        return gratuito ? 0 : costoBase + calcularCostoMateriales(actividades);
    }

    // Uso de Wildcards
    public double calcularCostoMateriales(List<? extends Actividad> listaActividades) {
        double total = 0;
        for (Actividad a : listaActividades) {
            total += a.calcularCostoMateriales();
        }
        return total;
    }

    //metodo parametrizado acotado para filtrar
    public <T extends Actividad> List<T> filtrarActividadesPorTipo(Class<T> tipo) {
        List<T> filtradas = new ArrayList<>();
        for (Actividad a : actividades) {
            if (tipo.isInstance(a)) {
                filtradas.add(tipo.cast(a));
            }
        }
        return filtradas;
    }

    public void mostrarDatos() {
        System.out.println("\nEvento: " + titulo + " (ID: " + id + ") ");
        System.out.println("Sala: " + (sala != null ? sala.toString() : "Sin asignar"));
        System.out.println("Costo Estimado Total: $" + calcularCostoEstimado());
        System.out.println("Cantidad total de eventos históricos: " + cantidadEventos);
    }

    //persistencia y excepciones
    public boolean persistirEvento() {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(this.id + ".dat");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            return true;
        } catch (FileNotFoundException e) {
            System.err.println("Error: Ruta de archivo no encontrada al persistir.");
        } catch (IOException e) {
            System.err.println("Error de Entrada/Salida al persistir el evento: " + e.getMessage());
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar los flujos de escritura.");
            }
        }
        return false;
    }

    public static EventoUniversitario recuperarEvento(String id) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        EventoUniversitario evento = null;
        try {
            fis = new FileInputStream(id + ".dat");
            ois = new ObjectInputStream(fis);
            evento = (EventoUniversitario) ois.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("Error: No se encontró el archivo del evento " + id);
        } catch (IOException e) {
            System.err.println("Error de Entrada/Salida al recuperar el evento: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Clase EventoUniversitario no encontrada.");
        } finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar los flujos de lectura.");
            }
        }
        return evento;
    }

    public List<Actividad> getActividades() { return actividades; }
}