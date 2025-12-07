<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="turnopro.entities.Turno"%>
<%@page import="turnopro.entities.EstadoTurno"%>

<html xmlns:jsp="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Lista de Turnos</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="container-fluid mt-5">
    <jsp:include page="partials/logo_header.jsp"/>
    <h1 class="mb-4">Gestión de Turnos</h1>
    <%-- Bloque para mensajes de éxito y error al Atender Turno --%>
    <%
    String exitoAtender = request.getParameter("exitoAtender");
    String errorAtender = request.getParameter("errorAtender");

    if ("true".equals(exitoAtender)) {
    %>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ✅ ¡Turno atendido! El estado ha sido actualizado correctamente.
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
    } else if (errorAtender != null) {
    %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        ❌ **Error al atender el turno:** <%= errorAtender %>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <%
    }
    %>
    <a href="index.jsp" class="btn btn-secondary mb-3">← Volver al Menú</a>

    <div class="row mb-4">
        <div class="col-md-6">
            <h3>Filtro por Estado</h3>
            <form action="filtrarTurnos" method="GET" class="d-flex">
                <select name="estado" class="form-select me-2" required>
                    <option value="">Seleccione Estado</option>
                    <option value="EN_ESPERA">EN ESPERA</option>
                    <option value="YA_ATENDIDO">YA ATENDIDO</option>
                </select>
                <button type="submit" class="btn btn-info">Filtrar</button>
            </form>
        </div>
        <div class="col-md-6">
            <h3>Filtro por Fecha</h3>
            <form action="filtrarTurnos" method="GET" class="d-flex">
                <input type="datetime-local" class="form-control me-2" name="fecha" required>
                <button type="submit" class="btn btn-info">Filtrar</button>
            </form>
        </div>
    </div>

    <a href="listarTurnos" class="btn btn-warning mb-3">Mostrar Todos los Turnos</a>

    <hr/>

    <h2 class="mt-4">Turnos Encontrados</h2>

    <%
    // Obtener la lista de turnos del request, enviada por el servlet
    List
    <Turno> turnos = (List
        <Turno>) request.getAttribute("turnos");
            if (turnos == null || turnos.isEmpty()) {
            %>
            <p class="alert alert-warning">No hay turnos registrados o no se encontraron resultados para el filtro.</p>
            <%
            } else {
            %>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>ID T.</th>
                        <th>Fecha y Hora</th>
                        <th>Descripción</th>
                        <th>Estado</th>
                        <th>Ciudadano (ID/DNI)</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Turno t : turnos) { %>
                    <tr>
                        <td><%= t.getIdentificadorProgresivo() %></td>
                        <td><%= t.getFecha() %></td>
                        <td><%= t.getDescripcion() %></td>
                        <td>
                                        <span class="badge
                                              <% if (t.getEstadoTurno() == EstadoTurno.EN_ESPERA) { %> bg-danger
                                              <% } else { %> bg-success <% } %>">
                                            <%= t.getEstadoTurno() %>
                                        </span>
                        </td>
                        <td>
                            <%= t.getCiudadano().getId() %> / <%= t.getCiudadano().getDni() %>
                        </td>
                        <td>
                            <% if (t.getEstadoTurno() == EstadoTurno.EN_ESPERA) { %>
                            <form action="actualizarEstado" method="POST" style="display:inline;">
                                <input type="hidden" name="idTurno" value="<%= t.getId() %>">
                                <button type="submit" class="btn btn-sm btn-success"
                                        onclick="return confirm('¿Confirma que el turno ID <%= t.getIdentificadorProgresivo() %> ha sido atendido?');">
                                    Atender
                                </button>
                            </form>
                            <% } else { %>
                            <span class="text-muted">Atendido</span>
                            <% } %>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
            <%
            }
            %>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
<jsp:include page="partials/footer.jsp"/>
</html>