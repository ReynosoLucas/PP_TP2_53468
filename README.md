# Sistema de Gestión de Eventos Universitarios - TP2

Proyecto desarrollado para la cátedra de Paradigmas de Programación.

## Funcionalidades Implementadas

* **Manejo de Excepciones y Persistencia (Ejercicio 1):** Control de cupos en las actividades mediante excepciones chequeadas (CupoExcedidoException). Serialización y deserialización de objetos (EventoUniversitario) con control granular de errores de entrada/salida.
* **Interfaces (Ejercicio 2):** Implementación de la interfaz Certificable para la emisión de certificados en actividades específicas (Talleres y Cursos), excluyendo Charlas.
* **Clases Parametrizadas y Wildcards (Ejercicio 3):** Filtrado dinámico de listas de actividades según su tipo exacto utilizando <T extends Actividad>. Cálculo de costos usando List<? extends Actividad>.
* **Clases Anidadas e Hilos (Ejercicio 4):** Generación de TicketDeAcceso modelado como clase anidada miembro de Inscripcion. Despacho concurrente de tickets mediante la clase EnvioTicketsThread que hereda de Thread, operando en paralelo al hilo principal.

## Ejecución del Programa
El punto de entrada del programa es la clase App.java. Al ejecutarla, se simula el flujo completo de los 4 ejercicios, demostrando casos exitosos, capturas controladas de fallos, impresión de certificados, cálculos paramétricos y la ejecución del envío de tickets.
Para ejecutar el programa deberá seleccionar la carpeta entregada desde el IntelliJ IDEA.

Se incluye en la raíz del repositorio unas capturas de pantalla con la salida de ejecución solicitada en los requisitos del TP.
