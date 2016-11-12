
<div id="erros">

</div>
<g:formRemote name="formHumorista" url="[controller: 'humorista', action: 'salvar']" onSuccess="exibirMensagem(data)">


    ID<input type="text" name="nome" value="${humorista.id}" disabled="true"/></label><br>
    <g:message code = "humorista.nome"/>
    Nome<input type="text" name="nome" value="${humorista.nome}"/><br>
    Nivel<input type="number" name="nivel" value="${humorista.nivel}"/><br>
    Nome Artistico<input type="text" name="nomeArtistico" value="${humorista.nomeArtistico}"/><br>
    Data Nascimento<input type="date" name="dataNascimento" value="${humorista.DataNascimento}"/><br>
    Processo <input type="number" name="processo" value="${humorista.processos}"/> <br>
    Salário <input type="number" name="salario" value="${humorista.Salario}"/><br>

    <input type="hidden" name="id" value="${humorista.id}">
    <input type="submit" name="enviar" value="Salvar">
</g:formRemote>