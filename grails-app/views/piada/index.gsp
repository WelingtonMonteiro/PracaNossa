<%--
  Created by IntelliJ IDEA.
  User: Welington
  Date: 05/11/2016
  Time: 11:00
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Piadas do Humorista ${humorista?.nome}</title>
    <meta content="main" name="layout">

    <script>
        function carregarLista(){
            $.ajax({
                url: '/PracaNossa/piada/listar/${humorista.id}',
                method: 'POST',
                data: {
                    id: ${humorista.id}
                },
                suceess: function (data){
                    $("#divLista").html(data)
                }
            })
        }
    </script>
</head>

<body>
<h1>Piadas do humorista ${humorista?.nome}</h1>

<a href="#" onclick="exibirForm()">Cadastrar Piadas</a>
<g:formRemote id='formPiada' name="formPiada" url="[controller: 'piada', action: 'cadastrar']" onSuccess="carregarLista()">
    Texto: <textarea name="texto"></textarea><br>
    Graça: <input type="number" name="nivelGraca"><br>

    <input type="hidden" name="humoristaId" value="${humorista.id}">
    <input type="submit" name="salvar" value="Salvar">
</g:formRemote>

<div id="divLista">
    <g:each in="${humorista?.piadas}" var="piada">
        ${piada.texto} <br>
    </g:each>
</div>
</body>
</html>