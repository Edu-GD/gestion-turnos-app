package turnopro.servlets;

import jakarta.servlet.RequestDispatcher;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 1. Obtener los parámetros del formulario
        String nombre = req.getParameter("nombre");
        String apellidos = req.getParameter("apellidos");
        String dni = req.getParameter("dni");
        String telefono = req.getParameter("telefono");
        String correo = req.getParameter("correoElectronico");

        // 2. Validación básica de datos
        if (nombre == null || nombre.trim().isEmpty() || apellidos == null || apellidos.trim().isEmpty() || dni == null || dni.trim().isEmpty() || telefono == null || telefono.trim().isEmpty() || correo == null || correo.trim().isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Faltan parámetros obligatorios.");
            return;
        }

        // 3. Crear el objeto Ciudadano
        Ciudadano nuevoCiudadano = new Ciudadano(nombre, apellidos, dni, telefono, correo);

        try {
            fachada.crearCiudadano(nuevoCiudadano);

            // Redirección de éxito
            resp.sendRedirect("agregarCiudadano.jsp?exito=true");
        } catch (RuntimeException e) {
            // Captura la excepción lanzada desde CiudadanoJPA
            String errorMessage = e.getMessage();

            if (errorMessage != null && (errorMessage.contains("Duplicate entry") || errorMessage.contains("UKgy889yq80kmpwyljp7xxovqht"))) {
                errorMessage = "El DNI proporcionado ya está registrado. Por favor, verifique el DNI e intente de nuevo.";
            } else if (errorMessage != null && errorMessage.contains("NOT NULL")) {
                errorMessage = "Faltan campos obligatorios.";
            } else {
                // Error inesperado o no manejado
                System.err.println("Error no controlado al crear Ciudadano: " + errorMessage);
                errorMessage = "Ocurrió un error inesperado al intentar registrar al ciudadano.";
            }

            // Establecer el mensaje de error como atributo
            req.setAttribute("error", errorMessage);

            // Usar RequestDispatcher para re-enviar la solicitud al JSP
            RequestDispatcher dispatcher = req.getRequestDispatcher("agregarCiudadano.jsp");
            dispatcher.forward(req, resp);

        } catch (Exception e) {
            // Para otros errores no controlados
            req.setAttribute("error", "Error inesperado del servidor.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("agregarCiudadano.jsp");
            dispatcher.forward(req, resp);
        }
    }

}
