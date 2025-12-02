package turnopro.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import turnopro.controllers.FachadaControllers;
import turnopro.entities.Ciudadano;

import java.io.IOException;

@WebServlet(name = "AgregarCiudadanoServlet", urlPatterns = {"/agregarCiudadano"})
public class AgregarCiudadanoServlet extends HttpServlet {

    private final FachadaControllers fachada = new FachadaControllers();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        // 1. Obtener los parámetros del formulario
        String nombre = req.getParameter("nombre");
        String apellidos = req.getParameter("apellidos");
        String dni = req.getParameter("dni");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correoElectronico");

        // 2. Validación básica de datos
        if (nombre == null || nombre.trim().isEmpty() || apellidos == null || apellidos.trim().isEmpty() || dni == null || dni.trim().isEmpty() || telefono == null || telefono.trim().isEmpty() || correo == null || correo.trim().isEmpty()){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros obligatorios.");
            return;
        }

        // 3. Crear el objeto Ciudadano
        Ciudadano nuevoCiudadano = new Ciudadano(nombre, apellidos, dni, telefono, correo);

        try {
            fachada.crearCiudadano(nuevoCiudadano);

            // Redirección de éxito
            resp.sendRedirect("agregarCiudadano.jsp?exito=true");
        } catch (RuntimeException e){
            // Captura la excepción lanzada desde CiudadanoJPA
            String errorMessage = e.getMessage();

            if (errorMessage.contains("DNI duplicado")){
                errorMessage = "El DNI proporcionado ya está registrado.";
            } else if (errorMessage.contains("NOT NULL")){
                errorMessage = "Faltan campos obligatorios.";
            }

            // Redirección de error
            resp.sendRedirect("agregarCiudadano.jsp?error=" + errorMessage);

        } catch (Exception e){
            // Para otros errores no controlados
            resp.sendRedirect("agregarCiudadano.jsp?error=Error inesperado del servidor.");
        }
    }

}
