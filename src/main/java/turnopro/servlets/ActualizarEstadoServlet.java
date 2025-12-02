package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;

import java.io.IOException;

@WebServlet(name = "ActualizarEstadoServlet", urlPatterns = {"/actualizarEstado"})
public class ActualizarEstadoServlet extends HttpServlet {

    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String idParam = req.getParameter("idTurno");

        if (idParam == null || idParam.isEmpty()){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Se requiere el parámetro 'idTurno' para actualizar el estado.");
            return;
        }

        try {
            Long idTurno = Long.parseLong(idParam);

            // Llama a la fachada, que delega al TurnoService para actualizar el estado a YA_ATENDIDO
            fachada.actualizarEstadoTurno(idTurno);

            // Respuesta de éxito
            resp.sendRedirect("listarTurnos?exitoAtender=true");
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "El ID del turno debe ser un número válido.");
        } catch (Exception e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar es estado del turno: " + e.getMessage());
        }

    }

}
