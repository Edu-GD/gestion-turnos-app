package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;
import turnopro.entities.Ciudadano;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@WebServlet(name = "AgregarTurnoServlet", urlPatterns = {"/agregarTurno"})
public class AgregarTurnoServlet extends HttpServlet {

    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        try {
            // 1. Obtener la lista de todos los ciudadanos registrados
            List<Ciudadano> ciudadanos = fachada.listarCiudadanos();

            // 2. Adjuntar la lista al objeto de solicitud
            req.setAttribute("ciudadanos", ciudadanos);

            // Usamos el RequestDispatcher para cargar el contenido JSP
            req.getRequestDispatcher("agregarTurno.jsp").forward(req, resp);
        } catch (Exception e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar la lista de ciudadanos: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // 1. Obtener los párametros
        String fechaHoraParam = req.getParameter("fechaHora");
        String descripcion = req.getParameter("descripcion");
        String idCiudadanoParam = req.getParameter("idCiudadano");

        if (fechaHoraParam == null || idCiudadanoParam == null || descripcion == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros obligatorios (fechaHora, descripcion, idCiudadano).");
            return;
        }

        try {
            // 2. Convertir y validar datos
            LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraParam);
            Long idCiudadano = Long.parseLong(idCiudadanoParam);

            // 3. LLamar a la fachada para ejecutar la lógica
            // Esta llamada se encarga de: buscar el ciudadano, generar el ID progresivo y guardar el Turno
            fachada.registrarNuevoTurno(fechaHora, descripcion, idCiudadano);

            // Redirección de éxito: vuelve al formulario con un parámetro 'éxito'
            resp.sendRedirect("agregarTurno.jsp?exito=true");
        } catch (DateTimeParseException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Formato de fecha u hora incorrecto. Use YYYY-MM-DDTHH:MM.");
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del ciudadano debe ser un número válido.");
        } catch (IllegalArgumentException e){
            // Captura el error lanzado desde el servicio si el ciudadano no existe
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
        } catch (Exception e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error inesperado al registrar el turno: " + e.getMessage());
        }

    }
}
