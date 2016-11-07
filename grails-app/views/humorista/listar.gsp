<!DOCTYPE html>
<html>
<head>
    <title>Lista de Humorista</title>
    <meta name="layout" content="main">
    <script>
        $(document).ready(function () {
//            $("#divForm").hide();
//            $("#tablelist tbody").append()
            //carregarLista()
        })
        function exibirForm(data) {
            $("#divForm").html(data);
            $("#divForm").fadeIn("slow");
            $("input[name=nome]").focus();
        }


        function exibirMensagem(data) {
            if (data.erro) return alert("Deu ruim");
            carregarLista(data)
        }

        function alterar(id) {
            $.ajax({
                url: 'alterar/' + id,
                data: {id: id},
                method: 'POST',
                success: function (data) {
                    exibirForm(data)
                }
            })
        }
        function carregarLista(humorista) {
//            var dataNasc =  Date.parse(humorista.dataNascimento);
//            dataNasc = dataNasc.toString('dd/mmmm');

            var colunas = "<td><a href='#' onclick='alterar(" + humorista.id + ")'>" + humorista.nome + "</td>" +
                    "<td>" + humorista.nivel + "</td>" +
                    "<td>" + humorista.nomeArtistico + "</td>" +
                    "<td>" + humorista.dataNascimento + "</td>" +
                    "<td>" + humorista.salario.toFixed(2).replace('.',',')+ "</td>" +
                    "<td><a href='#' onclick='excluir(" + humorista.id + ")'>Excluir </td>";

            //verifica se existe linha na tabela
            var linha = $("#" + humorista.id);

            if (linha.attr("id"))
                linha.html(colunas);
            else
                $("#tableHumoristas tbody").append("<tr id='" + humorista.id + "'>" + colunas + "</tr>")

        }

        function excluir(id){
            if(confirm("Deseja realmente excluir?")){
                $.ajax({
                    url: "excluir",
                    data: {id:id},
                    method: 'POST',
                    success: function(data){
                        var id = data.id;
                        var linha = $("#"+id);

                        if (linha.attr("id"))
                            linha.remove();

                    }

                })
            }
        }


    </script>
</head>

<body>
%{--<a href="index">Cadastrar Humurista</a><br>--}%
<g:remoteLink controller="humorista" action="cadastrar" onSuccess="exibirForm(data)">Cadastar Humorista</g:remoteLink>
<div id="divForm">

</div>
<table width="100%" id="tableHumoristas">
    <thead>
    <tr>
        <th>Nome</th>
        <th>Nivel</th>
        <th>Nome Artistico</th>
        <th>Data Nascimento</th>
        <th>Salario</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <g:each in="${lista}" var="humorista">
        <tr id="${humorista.id}">
            <td><g:remoteLink controller="humorista" action="alterar" id="${humorista.id}"
                              onSuccess="exibirForm(data)">${humorista.nome}</g:remoteLink></td>
            <td>${humorista.nivel}</td>
            <td>${humorista.nomeArtistico}</td>
            <td>${humorista.DataNascimento.format("dd/MMMM")}</td>
            <td>${String.format("%.2f", humorista.Salario)}</td>
            <td>
                <a href="#" onclick="excluir(${humorista.id})">Excluir</a>
                 |
                <g:link controller="piada" action="index" id="${humorista.id}">Piadas</g:link>
            </td>

        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>
