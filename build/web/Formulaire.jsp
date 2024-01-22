<%-- 
    Document   : Formulaire.jsp
    Created on : 20 janv. 2024, 12:24:29
    Author     : Haingo Adrienne
--%>

<%@page import="modele.Symptome"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Symptome> liste = (List<Symptome>)request.getAttribute("symptome");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="Style.css">
    <title>Formulaire</title>
</head>
<body>
    <nav>
        <ul>
            <li><a href="Formulaire">Faire consultation</a></li>
            <li><a href="Liste_Personne">Voir consultation</a></li>
            <li><a href="">Liste medicaments</a></li>
        </ul>
    </nav>
    <h1>Insertion de symptome :</h1>
    <form action="Formulaire" method="post">
        <table>
            <thead>
                <th><label for="">Date consultation : </label> <input type="date" name="date"></th>
                <th><label for="">Nom personne :</label> <input type="text" name="nom"></th>
                <th><label for="">Date de naissance : </label> <input type="date" name="naissance"></th>
                <th><label for="">Budget : </label> <input type="number" name="budget"></th>
                <th><label for="">Temperature : </label> <input type="number" name="temperature"><input type="number" name="double"></th>
            </thead>
        </table>
        <br>
        <table border="1px solid black">
            <thead>
                <th>Symptome</th>
                <th>Etat</th>
            </thead>
            <tbody>
                <%for(Symptome s : liste){%>
                    <tr>
                        <td><input type="checkbox" name="temp" value="<%=s.getId()%>"><%=s.getNom()%></td>
                        <td>
                            <%for(int i=0; i<11; i++){%>
                            <input type="checkbox" name="etat" value="<%=i%>"><%=i%>
                            <%}%>
                        </td>
                    </tr>
                <%}%>
            </tbody>
        </table>
        <br>
        <input type="submit" value="Inserer">
    </form>
</body>
</html>
