package core

import grails.converters.JSON
import org.hibernate.criterion.CriteriaSpecification

class HumoristaController {

    def index() {
        render (view: "index", model:[nome:"Joao", idade: 7])
    }

    def listar(){
//        //listar todos os humoristas
//        def humoristas = Humorista.list()
////        render humoristas as JSON
//
//        //listar humoristas por nome
//         humoristas  = Humorista.createCriteria().list{
//            order("nome", "desc")
//            order("salario")
//        }
//
//        //humoristas com a letra A
//         humorista = Humorista.createCriteria().list {
//            like("nome","%a%")
////            iLike("nome", "%o%")
//        }
//
//        //consultas no bancos com OR , AND
//         humorista = Humorista.createCriteria().list{
//            ilike("nome","%o%")
//            gt("salario","1000")
//            or{
//                ilike("nome","%o%")
//                gt("salario",1000d)
//            }
//        }

        //consultas com join
         def humoristas = Humorista.createCriteria().list {
             setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY)
            createAlias("piadas", "p", CriteriaSpecification.LEFT_JOIN)
             ge("p.nivelGraca",100)
        }

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
        humorista.Salario = params.salario?.replace(",",".")?.toDouble()
        humorista.DataNascimento = new Date()

        humorista.validate();

        if(humorista.hasErrors()){
            def listaErros = []
            humorista.errors.each { erro ->
                listaErros += g.message(code: erro.fieldError.defaultMessage, error: erro.fieldError)
            }


            def mensagem = ["erro" : listaErros]
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
