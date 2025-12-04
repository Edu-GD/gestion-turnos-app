<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="turnopro.entities.Ciudadano"%>

<% Ciudadano c = (Ciudadano) request.getAttribute("ciudadanoAEditar"); %>
<html>
<head>
    <title>Modificar Ciudadano</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container mt-5">
    <jsp:include page="partials/logo_header.jsp"/>
    <h1 class="mb-4">Modificar Ciudadano: <%= c.getDni() %></h1>
    <a href="listarCiudadanos" class="btn btn-secondary mb-3">← Volver a la Lista</a>

    <form action="ModificarCiudadanoServlet" method="POST">
        <input type="hidden" name="id" value="<%= c.getId() %>">

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" value="<%= c.getNombre() %>" required>
        </div>
        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos:</label>
            <input type="text" class="form-control" id="apellidos" name="apellidos" value="<%= c.getApellidos() %>" required>
        </div>

        <div class="mb-3">
            <label for="dni" class="form-label">DNI:</label>
            <input type="text" class="form-control" id="dni" name="dni" value="<%= c.getDni() %>" required>
        </div>

        <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono:</label>
            <input type="text" class="form-control" id="telefono" name="telefono" value="<%= c.getTelefono() %>" required>
        </div>

        <div class="mb-3">
            <label for="correoElectronico" class="form-label">Email:</label>
            <input type="email" class="form-control" id="correoElectronico" name="correoElectronico" value="<%= c.getCorreoElectronico() %>" required>
        </div>

        <button type="submit" class="btn btn-warning">Guardar Cambios</button>
    </form>
</div>
</body>
<jsp:include page="partials/footer.jsp"/>
</html>