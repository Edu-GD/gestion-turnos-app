package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;
import turnopro.entities.Turno;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarTurnoServlet", urlPatterns = {"/listarTurnos"})
public class ListarTurnosServlet extends HttpServlet {

    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        try {
            // 1. Obtenemos todos los turnos
            List<Turno> turnos = fachada.listarTurnos();

            // 2. Colocamos la lista
            req.setAttribute("turnos", turnos);

            // 3. Redirigimos a lista_turnos.jsp
            req.getRequestDispatcher("lista_turnos.jsp").forward(req, resp);
        } catch (Exception e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener la lista de turnos: " + e.getMessage());
        }
    }
}
