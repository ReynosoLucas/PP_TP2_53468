package modelo.actividades;
import certificacion.Certificable;
import modelo.Estudiante;

public class Curso extends Actividad implements Certificable {
    private int nivel;

    public Curso(int id, String titulo, int cupoMaximo, int nivel) {
        super(id, titulo, cupoMaximo);
        this.nivel = nivel;
    }

    @Override
    public double calcularCostoMateriales() { return 15000.0 * nivel; }

    @Override
    public String getTipo() { return "Curso"; }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "El estudiante " + estudiante.getNombre() + " aprobó el Curso de nivel " + nivel + " expedido por " + ENTIDAD_EMISORA;
    }
}