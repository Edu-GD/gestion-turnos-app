package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;
import turnopro.entities.Ciudadano;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarCiudadanoServlet", urlPatterns = {"/listarCiudadanos"})
public class ListarCiudadanosServlet extends HttpServlet {
    // Fachada para acceder a la lógica de negocio relacionada con los ciudadanos
    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {// Obtener la lista completa de ciudadanos
            List<Ciudadano> ciudadanos = fachada.listarCiudadanos();
            req.setAttribute("ciudadanos", ciudadanos);

            // Reenvía a la nueva página JSP
            req.getRequestDispatcher("listarCiudadanos.jsp").forward(req, resp);
        } catch (Exception e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar los ciudadanos.");
        }
    }
}
