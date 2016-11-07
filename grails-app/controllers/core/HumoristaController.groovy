package core

import grails.converters.JSON

class HumoristaController {

    def index() {
        render (view: "index", model:[nome:"Joao", idade: 7])
    }

    def listar(){
        def humoristas = Humorista.list()
//        render humoristas as JSON
        render (view: "listar", model: [lista:humoristas])
    }

    def salvar(){
        Humorista humorista

        if(!params.id)
            humorista = new Humorista();
        else
            humorista = Humorista.get(params.id)

        humorista.nome = params.nome
        humorista.nomeArtistico = params.nomeArtistico
        humorista.nivel = params.nivel.toInteger()
        humorista.processos = params.processo.toInteger()
        humorista.Salario = params.salario.toDouble()
        humorista.DataNascimento = new Date()

        humorista.validate();

        if(humorista.hasErrors()){
            def mensagem = ["erro" : humorista.errors.allErrors]
             render mensagem as JSON
            return
        }
        humorista = humorista.save(flush: true)
        render humorista as JSON
    }

    def cadastrar(){
        Humorista humorista = new Humorista();

        render (template: "form", model: [humorista: humorista])
    }

    def alterar(){
        Humorista humorista = Humorista.get(params.id)

        if(humorista == null) return;

        render (template: "form", model: [humorista: humorista])
    }

    def carregar(){
        def humorista = Humorista.list(sort: "nome")
        render humorista as JSON

    }

    def excluir(){
        Humorista humorista = Humorista.get(params.id)
        if(humorista == null) return;

        humorista.delete(flush:true)

        def mensagem = [id: params.id, mensagem: 'sucesso']
        render mensagem as JSON
    }
}
