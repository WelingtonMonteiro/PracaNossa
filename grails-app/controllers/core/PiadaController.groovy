package core

import grails.converters.JSON

class PiadaController {

    def index() {
        def humoristaId = params.id?.toInteger()

        if(humoristaId){
            Humorista humorista = Humorista.get(humoristaId)
            render (view: 'index', model: [humorista: humorista])
        }
    }

    def cadastrar(){
        Integer humoristaId = params.humoristaId?.toInteger()
        String texto = params.texto
        Integer nivelGraca = params.nivelGraca?.toInteger()

        Piada piada = new Piada();
        Humorista humorista = new Humorista()

        piada.texto = texto
        piada.nivelGraca = nivelGraca
        piada.humorista = Humorista.get(humoristaId)

        piada.validate()

        def mensagem = [:]

        if(piada.hasErrors()){
            mensagem = [mensagem: "Erro"]
        } else {

            piada = piada.save(flush: true)
            mensagem = [mensagem: "OK"]

        }
        render mensagem as JSON

    }

    def listar(){
        Integer humoristaId = params.id?.toInteger()

        if(humoristaId){
            def piadas = Humorista.get(humoristaId)?.piadas
            render piadas.texto
        }

    }


}
