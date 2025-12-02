<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registro de Ciudadano</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container mt-5">
    <h1 class="mb-4">Registrar Nuevo Ciudadano</h1>
    <%
    String exito = request.getParameter("exito");
    if ("true".equals(exito)) {
    %>
    <div class="alert alert-success" role="alert">
        ¡Registro exitoso! Puedes añadir otro ciudadano.
    </div>
    <%
    }
    // Puedes añadir lógica similar para los parámetros 'error'
    %>
    <a href="index.jsp" class="btn btn-secondary mb-3">← Volver al Menú</a>

    <form action="agregarCiudadano" method="POST">

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>

        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos:</label>
            <input type="text" class="form-control" id="apellidos" name="apellidos" required>
        </div>

        <div class="mb-3">
            <label for="dni" class="form-label">DNI:</label>
            <input type="text" class="form-control" id="dni" name="dni" required>
        </div>

        <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono:</label>
            <input type="tel" class="form-control" id="telefono" name="telefono">
        </div>

        <div class="mb-3">
            <label for="correoElectronico" class="form-label">Email:</label>
            <input type="email" class="form-control" id="correoElectronico" name="correoElectronico">
        </div>

        <button type="submit" class="btn btn-primary">Registrar</button>
    </form>
</div>
</body>
</html>