# 📅 Gestión de turnos WEB (turnopro)

> 💡 **Aplicación Web de Gestión de Colas** desarrollada con Java Servlets, JSP y JPA (Hibernate), siguiendo el patrón **Modelo-Vista-Controlador (MVC)**.

---

## ✨ 1. Descripción del Proyecto

El proyecto-web es un sistema de **gestión de turnos de atención al público.** Está desarrollado con **Java**, utilizando **Servlets, JSP** y **JPA** (Hibernate) para la persistencia de datos.

El objetivo del proyecto es simular y gestionar de manera eficiente el flujo de atención al público de una entidad, garantizando un sistema de turnos **progresivo** y **ordenado**.

---

## ⚙️ 2. Características Funcionales

* **Registro de ciudadanos:** Permite crear y almacenar información de los ciudadanos (nombre y apellidos, DNI, teléfono y correo electrónico).
* **Generación de turnos:** Generación automática de ID's de turnos **correlativos** y **únicos**.
* **Asociación:** Cada *Turno* está asociado a un *Ciudadano*.
* **Gestión de estados:** Permite actualizar el estado del turno (*EN ESPERA* a *YA ATENDIDO*).
* **Filtrado de turnos:** Opciones de consulta y filtrado por **Estado** y por **Fecha/Hora**.

---

## 💻 3. Tecnologías Utilizadas

| Componente | Tecnología | Versión / Estándar |
| :--- | :--- | :--- |
| **Lenguaje** | Java | 21 |
| **Backend Web** | Servlets y JSP | Jakarta EE 5.0 |
| **Persistencia** | JPA / Hibernate | - |
| **Base de datos** | MySQL | - |
| **Front-end** | HTML, CSS | - |
| **Build Tool** | Apache Maven | - |
| **Servidor** | Apache Tomcat | 10 |

---

## 🚀 4. Configuración e Instalación

### 4.1. Pasos de Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone git@github.com:Edu-GD/gestion-turnos-app.git
    ```
2.  **Configurar Base de Datos:**
    * Crea una base de datos en **MySQL** con el siguiente nombre: `turnopro`.
    * Edita el archivo de configuración de JPA (*persistence.xml*, ubicado en *src/main/resources/META-INF/*) para ajustar las credenciales de conexión (**user**, **password**, **url**).
3.  **Compilar y empaquetar:**
    * **En IntelliJ IDEA:**
        * **Importar proyecto:** Abre IntelliJ y selecciona **File -> Open** e importa la carpeta del proyecto.
        * **Reconstruir:** Ve a **Build -> Rebuild Project** para compilar las clases.
        * **Generar WAR:** Abre la ventana de **Maven** (normalmente situada en la barra lateral derecha), navega a **Lifecycle** y haz doble clic en `package`.

> **Atención:** Si utilizas otro IDE (como **NetBeans** o **Eclipse**), sigue las instrucciones específicas para *Clean and Build* o *Exportar como archivo WAR*.

### 4.2. Acceso

* Inicia el servidor Tomcat.
* Ingresa a la aplicación a través de éste enlace desde tu navegador:
    ```
    http://localhost:8080/app-gestion-turnos/
    ```

---

## 5. 📂 Estructura del Proyecto (Capas)

### Capas Principales

* **`turnopro.servlets`**: Controladores de flujo y manejo de peticiones HTTP (Servlets). Esta capa es responsable de manejar las interacciones del usuario y la navegación.
* **`turnopro.logic`**: Lógica de negocio. Esta capa contiene las reglas de negocio, validaciones y la coordinación de las operaciones que dan sentido a la aplicación.
* **`turnopro.persistence`**: Acceso a datos (Clases JPA/DAO). Esta capa es el puente entre la aplicación Java y la Base de Datos (MySQL).
* **`src/main/webapp`**: Vistas con los archivos. Son la interfaz de usuario. Contienen el código HTML y JSP para mostrar los datos al usuario.

### Flujo de una Petición

1.  **Servlet** recibe la petición.
2.  Llama a la **lógica de Negocio**.
3.  La **lógica de Negocio** llama al **Acceso a Datos** (JPA) para obtener o guardar datos.
4.  El **Acceso a Datos** interactúa con **MySQL**.
5.  El **Servlet** recibe la respuesta y redirige a la **Vista** (JSP) para mostrar el resultado.

---

## 6. 🤝 Autores

* **Rubén Verde**
    * [https://github.com/rvbenr](https://github.com/rvbenr)
* **Carlos David**
    * [https://github.com/carlosdavid1990](https://github.com/carlosdavid1990)
* **Davide Pinna**
    * [https://github.com/davidep994](https://github.com/davidep994)
* **Edu Garcia**
    * [https://github.com/Edu-GD](https://github.com/Edu-GD)
