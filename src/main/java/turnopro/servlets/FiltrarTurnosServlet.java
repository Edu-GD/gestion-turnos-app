package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;
import turnopro.entities.EstadoTurno;
import turnopro.entities.Turno;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(name = "FiltrarTurnoServlet", urlPatterns = {"/filtrarTurnos"})
public class FiltrarTurnosServlet extends HttpServlet {

    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fechaParam = req.getParameter("fecha");
        String estadoParam = req.getParameter("estado");
        List<Turno> turnos;

        try {
            if (fechaParam != null && !fechaParam.isEmpty()) {
                // Filtrar por fecha
                LocalDateTime fecha = LocalDateTime.parse(fechaParam);
                turnos = fachada.filtrarPorFecha(fecha);
            } else if (estadoParam != null && !estadoParam.isEmpty()) {
                // Filtrar por estado
                EstadoTurno estado = EstadoTurno.valueOf(estadoParam.toUpperCase());
                turnos = fachada.filtrarPorEstado(estado);
            } else {
                // Si no hay parámetros, listar todos (por defecto)
                turnos = fachada.listarTurnos();
            }

            req.setAttribute("turnos", turnos);
            req.getRequestDispatcher("lista_turnos.jsp").forward(req, resp);
        } catch (DateTimeParseException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha u hora incorrecto. Use YYYY-MM-DDTHH:MM.");
        } catch (IllegalArgumentException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El estado proporcionado no es válido. Debe ser EN_ESPERA o YA_ATENDIDO.");
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar el filtro de turnos: " + e.getMessage());
        }
    }
}
