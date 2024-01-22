<%-- 
    Document   : Liste_Personne
    Created on : 20 janv. 2024, 12:24:56
    Author     : Haingo Adrienne
--%>

<%@page import="modele.Personne"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Personne> liste = (List<Personne>)request.getAttribute("liste");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Liste consultations</title>
</head>
<body>
    <nav>
        <ul>
            <li><a href="Formulaire">Faire consultation</a></li>
            <li><a href="Liste_Personne">Voir consultation</a></li>
            <li><a href="">Liste medicaments</a></li>
        </ul>
    </nav>
    <h1>Liste de consultation</h1>

    <table border="1px solid black">
        <thead>
            <th>Date</th>
            <th>Personne</th>
            <th>#</th>
        </thead>
        <tbody>
            <%for(Personne p : liste){%>
                <tr>
                    <td><%=p.getConsultation()%></td>
                    <td><%=p.getNom()%></td>
                    <td><a href="Details_Personne.html">Voir details.</a></td>
                </tr>
            <%}%>
        </tbody>
    </table>
</body>
</html>
