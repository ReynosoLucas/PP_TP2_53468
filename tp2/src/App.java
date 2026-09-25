import excepciones.CupoExcedidoException;
import hilos.EnvioTicketsThread;
import modelo.*;
import modelo.actividades.*;
import certificacion.Certificable;
import java.util.List;

public class App {
    public static void main(String[] args) {

        Estudiante e1 = new Estudiante("50268", "Juan Perez");
        Estudiante e2 = new Estudiante("50269", "María Lopez");
        Estudiante e3 = new Estudiante("50270", "Abby Grenon");
        Estudiante e4 = new Estudiante("50271", "Lautaro Cornejo");

        EventoUniversitario evento = new EventoUniversitario("EV-001", "Jornadas Tecnológicas UTN", 10000, false);
        evento.asignarSala(new Sala(101, "Auditorio Principal"));

        Charla charla = new Charla(1, "IA en Videojuegos", 50, "Experto Local");
        Taller taller = new Taller(2, "Armado de PC y Hardware", 1, true);
        Curso curso = new Curso(3, "Introducción a Java", 20, 1);

        evento.agregarActividad(charla);
        evento.agregarActividad(taller);
        evento.agregarActividad(curso);


        System.out.println("PRUEBA DE INSCRIPCIONES Y MANEJO DE EXCEPCIONES ");
        try {
            Inscripcion i1 = taller.inscribir(e1);
            i1.confirmarInscripcion();
            System.out.println("Inscripción exitosa: " + e1.getNombre() + " en Taller.");

            System.out.println("Intentando inscribir a " + e2.getNombre() + " en el mismo Taller (cupo lleno)...");

            taller.inscribir(e2);
        } catch (CupoExcedidoException e) {
            System.err.println("FALLO CONTROLADO: " + e.getMessage());
        } finally {
            System.out.println("Intentando guardar el evento en archivo (Persistencia)...");
            if (evento.persistirEvento()) {
                System.out.println("Evento serializado y guardado correctamente.");
            }
        }


        try {
            curso.inscribir(e2).confirmarInscripcion();
            curso.inscribir(e3).confirmarInscripcion();
            charla.inscribir(e4).confirmarInscripcion();
        } catch (Exception ignored) {}


        System.out.println("\nEMISIÓN DE CERTIFICADOS ");
        for (Actividad act : evento.getActividades()) {
            if (act instanceof Certificable) {
                Certificable cert = (Certificable) act;
                for (Inscripcion ins : act.getInscripciones()) {
                    System.out.println(cert.generarCertificado(ins.getEstudiante()));
                }
            }
        }


        System.out.println("\nFILTROS Y CÁLCULOS POR TIPO ");
        List<Curso> listaCursos = evento.filtrarActividadesPorTipo(Curso.class);
        List<Charla> listaCharlas = evento.filtrarActividadesPorTipo(Charla.class);

        System.out.println("Cantidad de Cursos: " + listaCursos.size());
        System.out.println("Cantidad de Charlas: " + listaCharlas.size());
        System.out.println("Costo de Materiales (solo Cursos): $" + evento.calcularCostoMateriales(listaCursos));


        System.out.println("\nINICIANDO PROCESO CONCURRENTE DE TICKETS ");
        EnvioTicketsThread hiloTickets = new EnvioTicketsThread(evento);
        hiloTickets.start();


        evento.mostrarDatos();
        System.out.println("\n[HILO PRINCIPAL] Mostrando identificaciones de las actividades:");
        for(Actividad a : evento.getActividades()) {
            a.mostrarIdentificacion();
            a.mostrarInscripciones();
        }

        try {
            //join espera a que el hilo termine
            hiloTickets.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}