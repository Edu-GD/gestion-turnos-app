<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="turnopro.entities.Ciudadano"%>

<html xmlns:jsp="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Solicitar Turno</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="partials/logo_header.jsp"/>
    <h1 class="mb-4">Solicitar Nuevo Turno</h1>
    <a href="index.jsp" class="btn btn-secondary mb-3">← Volver al Menú</a>

    <%-- Lógica para mostrar mensajes de éxito/error (del paso anterior) --%>
    <%
    String exito = request.getParameter("exito");
    if ("true".equals(exito)) {
    %>
    <div class="alert alert-success" role="alert">
        ¡Turno registrado con éxito!
    </div>
    <%
    }
    %>

    <form action="agregarTurno" method="POST">

        <div class="mb-3">
            <label for="idCiudadano" class="form-label">Seleccionar Ciudadano:</label>

            <select class="form-select" id="idCiudadano" name="idCiudadano" required>
                <option value="" disabled selected>Selecciona un ciudadano...</option>

                <%
                // 1. Obtener la lista de ciudadanos que adjuntó el doGet()
                List
                <Ciudadano> ciudadanos = (List
                    <Ciudadano>) request.getAttribute("ciudadanos");
                        if (ciudadanos != null) {
                        for (Ciudadano c : ciudadanos) {
                        %>
                        <option value="<%= c.getId() %>">
                            <%= c.getNombre() %> <%= c.getApellidos() %> (DNI: <%= c.getDni() %>)
                        </option>
                        <%
                        }
                        }
                        %>
            </select>
        </div>

        <div class="mb-3">
            <label for="fechaHora" class="form-label">Fecha y Hora:</label>
            <input type="datetime-local" class="form-control" id="fechaHora" name="fechaHora" required>
        </div>

        <div class="mb-3">
            <label for="descripcion" class="form-label">Descripción del Asunto:</label>
            <textarea class="form-control" id="descripcion" name="descripcion" rows="3" required></textarea>
        </div>

        <button type="submit" class="btn btn-success">Solicitar Turno</button>
    </form>
</div>
</body>
<jsp:include page="partials/footer.jsp"/>
</html>