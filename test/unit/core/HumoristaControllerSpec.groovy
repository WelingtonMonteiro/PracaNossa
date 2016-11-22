package core


import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.servlet.mvc.SynchronizerTokensHolder
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
        def tokenHolder = SynchronizerTokensHolder.store(session)

        params[SynchronizerTokensHolder.TOKEN_URI] = '/humorista/index'
        params[SynchronizerTokensHolder.TOKEN_KEY] = tokenHolder.generateToken(params[SynchronizerTokensHolder.TOKEN_URI])

        controller.index()
        then:
        model.nome == "Joao"
        model.idade == 7

    }

    void "Cadastrar novo humorista"(){
        when:
        def tokenHolder = SynchronizerTokensHolder.store(session)

        params[SynchronizerTokensHolder.TOKEN_URI] = '/humorista/salvar'
        params[SynchronizerTokensHolder.TOKEN_KEY] = tokenHolder.generateToken(params[SynchronizerTokensHolder.TOKEN_URI])

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
        def tokenHolder = SynchronizerTokensHolder.store(session)

        params[SynchronizerTokensHolder.TOKEN_URI] = '/humorista/salvar'
        params[SynchronizerTokensHolder.TOKEN_KEY] = tokenHolder.generateToken(params[SynchronizerTokensHolder.TOKEN_URI])


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
        def tokenHolder = SynchronizerTokensHolder.store(session)

        params[SynchronizerTokensHolder.TOKEN_URI] = '/humorista/salvar'
        params[SynchronizerTokensHolder.TOKEN_KEY] = tokenHolder.generateToken(params[SynchronizerTokensHolder.TOKEN_URI])


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
            response.nivel == 2
    }

    void "Atualizar humorista que não existe"(){
        when:
        def tokenHolder = SynchronizerTokensHolder.store(session)

        params[SynchronizerTokensHolder.TOKEN_URI] = '/humorista/salvar'
        params[SynchronizerTokensHolder.TOKEN_KEY] = tokenHolder.generateToken(params[SynchronizerTokensHolder.TOKEN_URI])

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
