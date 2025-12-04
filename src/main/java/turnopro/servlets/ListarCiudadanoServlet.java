package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;
import turnopro.entities.Ciudadano;

import java.util.List;
import java.io.IOException;

@WebServlet(name = "ListarCiudadanoServlet", urlPatterns = {"/listarCiudadano"})
public class ListarCiudadanoServlet extends HttpServlet {

    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Ciudadano> ciudadanos = fachada.listarCiudadanos();
            req.setAttribute("ciudadanos", ciudadanos);

            req.getRequestDispatcher("listarCiudadanos.jsp").forward(req, resp);
        }catch (Exception e){
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al listar los ciudadanos");
        }
    }



}
