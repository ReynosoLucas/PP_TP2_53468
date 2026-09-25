package modelo;
import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TicketDeAcceso ticket; //nulo hasta confirmar

    public Inscripcion(Estudiante estudiante) {
        this.fecha = LocalDate.now();
        this.estado = "Pendiente";
        this.estudiante = estudiante;
    }

    public void confirmarInscripcion() {
        this.estado = "Confirmada";
        this.ticket = new TicketDeAcceso();
    }

    public String getEstado() { return estado; }
    public Estudiante getEstudiante() { return estudiante; }
    public TicketDeAcceso getTicket() { return ticket; }

    //clase anidada
    public class TicketDeAcceso implements Serializable {
        private String idTicket;

        public TicketDeAcceso() {
            // Generador clásico con Math.random() como en la teoría
            int numeroAleatorio = (int)(Math.random() * 90000) + 10000;
            this.idTicket = "TKT-" + numeroAleatorio;
        }

        public void enviarTicket() {
            System.out.println(">>> [TICKET ENVIADO] Ticket ID: " + idTicket + " al estudiante " + estudiante.getNombre());
        }
    }
}