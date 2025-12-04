package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;

import java.io.IOException;

@WebServlet(name = "EliminarCiudadanoServlet", urlPatterns = {"/EliminarCiudadanoServlet"})
public class EliminarCiudadanoServlet extends HttpServlet {

    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        try {
            Long idCiudadano = Long.parseLong(req.getParameter("idCiudadano"));

            fachada.eliminarCiudadano(idCiudadano);

            resp.sendRedirect("listarCiudadanos?mensaje=exito_eliminacion");
        }catch (NumberFormatException e){
            resp.sendRedirect("listarCiudadanos?error=ID_INVALIDO");
        }catch (Exception e){
            resp.sendRedirect("listarCiudadanos?error=Error al eliminar (posiblemente tiene turnos asociados).");
        }
    }

}
