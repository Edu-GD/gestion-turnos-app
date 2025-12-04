<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="turnopro.entities.Ciudadano"%>

<html>
<head>
    <title>Gestión de Ciudadanos</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="partials/logo_header.jsp"/>
    <h1 class="mb-4">Gestión de Ciudadanos</h1>
    <a href="index.jsp" class="btn btn-secondary">← Volver al Menú</a>
    <a href="agregarCiudadano.jsp" class=" btn btn-primary">➕ Registrar Nuevo Ciudadano</a>

    <%
    // Recuperar lista y mensajes de éxito/error de Modificar/Eliminar
    List<Ciudadano> ciudadanos = (List<Ciudadano>) request.getAttribute("ciudadanos");
    String mensaje = request.getParameter("mensaje");

    if("exito_eliminacion".equals(mensaje)) {
    %>
    <div class="alert alert-success mt-3">Ciudadano modificado correctamente!</div>
    <%
    } else if ("exito_modificacion".equals(mensaje)) {
    %>
    <div class="alert alert-success mt-3">Ciudadano modificado correctamente</div>
    <%
    }
    %>

    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <thead>
            <tr>
                <th>ID</th>
                <th>DNI</th>
                <th>Nombre completo</th>
                <th>Teléfono</th>
                <th>Acciones</th>
            </tr>
            </thead>
            <tbody>
            <% if (ciudadanos != null && !ciudadanos.isEmpty()) {
            for (Ciudadano c : ciudadanos) { %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getDni() %></td>
                <td><%= c.getNombre() %> <%= c.getApellidos() %></td>
                <td><%= c.getTelefono() %></td>
                <td><%= c.getCorreoElectronico() %></td>
                <td>
                    <a href="ModificarCiudadanoServlet?id=<%= c.getId() %>" class="btn btn-warning btn-sm">✏️ Editar</a>

                    <form action="EliminarCiudadanoServlet" method="POST" style="display:inline-block;">
                        <input type="hidden" name="idCiudadano" value="<%= c.getId() %>">
                        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('¿Está seguro de que desea eliminar a este ciudadano?');">🗑️ Eliminar</button>
                    </form>
                </td>
            </tr>
            <% } } else { %>
            <tr><td colspan="6" class="text-center">No hay ciudadanos registrados!</td></tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
</body>
<jsp:include page="partials/footer.jsp"/>
</html>