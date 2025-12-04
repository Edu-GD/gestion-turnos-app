<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="turnopro.entities.Ciudadano"%>

<html>
<head>
    <title>Gestión de ciudadanos</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <htm class="container mt-5">
        <jsp:include page="partials/logo_header.jsp"/>
    <h1 class="mb-4">Gestión de ciudadanos</h1>
    <a href="index.jsp" class ="btn btn-secondary">Volver al menú</a>
    <a href="agregarCiudadano.jsp" class ="btn btn-primary">Registrar nuevo ciudadano</a>

    <%
    List<body> ciudadanos=(List<div>) request.getAttribute("ciudadanos");
    String mensaje= request.getParameter("mensaje");

    if("exito_eliminación".equals(mensaje)){
    %>
    <div class="alert alert-success mt-3">Ciudadano eliminado correctamente</div>
    <%
    }else if("exito_modificacion".equals(mensaje)){
    %>
    <div class="alert alert-success mt-3">Ciudadano modificado correctamente</div>
    <%
    }
    %>

    <div class ="table-responsive">
    <table class ="table table-striped table-hover">
    <thead>
    <tr>
    <th>ID</th>
    <th>DNI</th>
    <th>nombreCompleto</th>
    <th>teléfono</th>
    <th>acciones</th>
    </tr>
    </thead>
        <tbody>
        <% if (ciudadanos != null && !ciudadanos.isEmpty()) {
        for (Ciudadano c : ciudadanos) { %>
        <tr>
            <td> <%= c.getId() %> </td>
            <td> <%= c.getDni() %> </td>
            <td> <%= c.getNombre() %> <%= c.getApellidos() %></td>
            <td> <%= c.getTelefono %> </td>
            <td> <%= c.getCorreoElectronico%> </td>
            <td>
            <a href="ModificarCiudadanoServlet?id=<%= c.getId() %>" class="btn btn-warning btn-sm"> editar</a>
                <form action="EliminarCiudadanoServlet" method="POST">
                <input type="hidden" name="idCiudadano" value="<%= c.getId()%>">
                    <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro que desea eliminar a éste ciudadano?')"> eliminar </button>
                </form>
            </td>
        </tr>
        <% } } %>
        <tr>
        <td colspan="6" class="text-center" >
            'No hay ciudadanos registrados.'
        </td>
        </tr>
        <% } %>
        </tbody>
        </table>
    </div>
    </div>
    </body>
</html>