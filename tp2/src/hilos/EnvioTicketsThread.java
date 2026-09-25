package hilos;
import modelo.EventoUniversitario;
import modelo.Inscripcion;
import modelo.actividades.Actividad;

public class EnvioTicketsThread extends Thread {
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento) {
        this.evento = evento;
    }

    @Override
    public void run() {
        System.out.println("[HILO DE TICKETS INICIADO]");
        for (Actividad act : evento.getActividades()) {
            for (Inscripcion ins : act.getInscripciones()) {
                if ("Confirmada".equalsIgnoreCase(ins.getEstado()) && ins.getTicket() != null) {
                    ins.getTicket().enviarTicket(); //llama al metodo de la clase anidada
                    try {
                        sleep(1500); //pausa
                    } catch (InterruptedException e) {
                        System.out.println("Envío interrumpido.");
                    }
                }
            }
        }
        System.out.println("[HILO DE TICKETS FINALIZADO]");
    }
}