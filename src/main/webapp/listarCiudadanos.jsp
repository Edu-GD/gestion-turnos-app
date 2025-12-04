<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="turnopro.entities.Ciudadano"%>

<!DOCTYPE html>
<html>
<head>
    <title>Gestión de ciudadanos</title>
    <link href="css/styles.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
    <div class="container mt-5">
    <h1 class="mb-4">Gestión de ciudadanos</h1>
    <a href="index.jsp" class ="btn btn-secondary">Volver al menú</a>
    <a href="agregarCiudadano.jsp" class ="btn btn-primary">Registrar nuevo ciudadano</a>

    <%
    List<Ciudadano> ciudadanos=(List<Ciudadano>) request.getAttribute("ciudadanos");
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

