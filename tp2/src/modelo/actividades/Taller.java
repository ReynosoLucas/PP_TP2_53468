package modelo.actividades;
import certificacion.Certificable;
import modelo.Estudiante;

public class Taller extends Actividad implements Certificable {
    private boolean requiereNotebook;

    public Taller(int id, String titulo, int cupoMaximo, boolean requiereNotebook) {
        super(id, titulo, cupoMaximo);
        this.requiereNotebook = requiereNotebook;
    }

    @Override
    public double calcularCostoMateriales() { return requiereNotebook ? 2000.0 : 8000.0; }

    @Override
    public String getTipo() { return "Taller"; }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "El estudiante " + estudiante.getNombre() + " completó el Taller expedido por " + ENTIDAD_EMISORA;
    }
}