package core


import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */

@TestFor(HumoristaController)
@Mock([Humorista, Piada])
class HumoristaControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Testando render view index"() {
        when:
        controller.index()
        then:
        model.nome == "Joao"
        model.idade == 7

    }

    void "Cadastrar novo humorista"(){
        when:
        params.nome = "Golias"
        params.nomeArtistico = "Golias"
        params.nivel = "1"
        params.processo = "10"
        params.salario = "100,00"
        params.DataNascimento = new Date()

        controller.salvar()
        then:
        response.json.id != null
    }

    void "Cadastrar novo humorista errado"(){
        when:
        params.nome = "Golias"
        params.nomeArtistico = "Golias"
        params.nivel = "1"
        params.processo = "10"
        params.salario = "-1"
        params.DataNascimento = new Date()

        controller.salvar()
        then:
        response.json.erro != null
    }

    void "Atualizar humorista"(){
        given:
            params.nome = "Golias"
            params.nomeArtistico = "Golias"
            params.nivel = "1"
            params.processo = "10"
            params.salario = "100"
            params.DataNascimento = new Date()
            controller.salvar()

        when:
            params.id = response.json.id
            params.nivel = "2"
            controller.salvar()

        then:
            response.json.nome == params.nome
            response.json.id == params.id
            response.nivel = 2
    }

    void "Atualizar humorista que não existe"(){
        when:
        params.id = "1"
        params.nome = "Golias"
        params.nomeArtistico = "Golias"
        params.nivel = "1"
        params.processo = "10"
        params.salario = "100"
        params.DataNascimento = new Date()

        controller.salvar()
        then:
        response.json.erro == "java.lang.NullPointerException"
    }
}
