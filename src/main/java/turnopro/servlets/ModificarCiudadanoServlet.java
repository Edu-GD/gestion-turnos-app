package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;
import turnopro.entities.Ciudadano;

import java.io.IOException;

@WebServlet(name = "ModificarCiudadanoServlet", urlPatterns = {"/ModificarCiudadanoServlet"})
public class ModificarCiudadanoServlet extends HttpServlet {

    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Long id = Long.parseLong(req.getParameter("id"));
        Ciudadano ciudadano = fachada.obtenerCiudadano(id);

        req.setAttribute("ciudadanoAEditar", ciudadano);
        req.getRequestDispatcher("modificarCiudadano.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Long id = Long.parseLong(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String apellidos = req.getParameter("apellidos");
        String dni = req.getParameter("dni");
        String telefono = req.getParameter("telefono");
        String email = req.getParameter("correoElectronico");

        Ciudadano ciudadano = fachada.obtenerCiudadano(id);
        if (ciudadano != null) {
            ciudadano.setNombre(nombre);
            ciudadano.setApellidos(apellidos);
            ciudadano.setDni(dni);
            ciudadano.setTelefono(telefono);
            ciudadano.setCorreoElectronico(email);

            fachada.modificarCiudadano(ciudadano);

            resp.sendRedirect("listarCiudadanos?mensaje=exito_modificacion");
        }else {
            resp.sendRedirect("listarCiudadanos=?error=ciudadano_no_encontrado");
        }
    }

}
