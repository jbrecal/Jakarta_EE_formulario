package org.juanbrenes.apiservlet.webapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/registro")
public class FormServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String pais = req.getParameter("pais");
        String[] lenguajes = req.getParameterValues("lenguajes");
        String[] roles = req.getParameterValues("roles");

        String idioma = req.getParameter("idioma");
        String habilitar = req.getParameter("habilitar");
        String secreto = req.getParameter("secreto");

        // Validaciones
        List<String> errores = new ArrayList<>();

        if (username == null || username.isBlank()) {
            errores.add("El username es obligatorio");
        }
        if (password == null || password.isBlank()) {
            errores.add("El password es obligatorio");
        }
        if (email == null || email.isBlank() || !email.contains("@")) {
            errores.add("El email es obligatorio y debe tener un formato válido");
        }
        if (pais == null || pais.isBlank()) {
            errores.add("El país es obligatorio");
        }
        if (lenguajes == null || lenguajes.length == 0) {
            errores.add("Debes seleccionar al menos un lenguaje de programación");
        }
        if (roles == null || roles.length == 0) {
            errores.add("Debes seleccionar al menos un rol");
        }
        if (idioma == null || idioma.isBlank()) {
            errores.add("El idioma es obligatorio");
        }

        try (PrintWriter out = resp.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("   <head>");
            out.println("       <meta charset=\"UTF-8\">");
            out.println("       <title>Resultado del Formulario</title>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("       <h1>Resultado del Formulario</h1>");

            if (!errores.isEmpty()) {
                out.println("       <h2>Errores:</h2>");
                out.println("       <ul>");
                errores.forEach(error -> out.println("           <li>" + error + "</li>"));
                out.println("       </ul>");
                out.println("<p><a href=\"/webapp-form/index.html\">Volver al formulario</a></p>");
            } else {
                out.println("       <ul>");
                out.println("           <li>Username: " + username + "</li>");
                out.println("           <li>Password: " + password + "</li>");
                out.println("           <li>Email: " + email + "</li>");
                out.println("           <li>País: " + pais + "</li>");
                out.println("           <li>Lenguajes: <ul>");
                for (String lenguaje : lenguajes) {
                    out.println("               <li>" + lenguaje + "</li>");
                }
                out.println("           </ul></li>");
                out.println("           <li>Roles: <ul>");
                for (String role : roles) {
                    out.println("               <li>" + role + "</li>");
                }
                out.println("           </ul></li>");
                out.println("           <li>Idioma: " + idioma + "</li>");
                out.println("           <li>Habilitado: " + (habilitar != null ? "Sí" : "No") + "</li>");
                out.println("           <li>Secreto: " + secreto + "</li>");
                out.println("       </ul>");
            }

            out.println("   </body>");
            out.println("</html>");
        }
    }
}
