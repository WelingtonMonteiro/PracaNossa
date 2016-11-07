package core



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ShowsController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Shows.list(params), model:[showsInstanceCount: Shows.count()]
    }

    def show(Shows showsInstance) {
        respond showsInstance
    }

    def create() {
        respond new Shows(params)
    }

    @Transactional
    def save(Shows showsInstance) {
        if (showsInstance == null) {
            notFound()
            return
        }

        if (showsInstance.hasErrors()) {
            respond showsInstance.errors, view:'create'
            return
        }

        showsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shows.label', default: 'Shows'), showsInstance.id])
                redirect showsInstance
            }
            '*' { respond showsInstance, [status: CREATED] }
        }
    }

    def edit(Shows showsInstance) {
        respond showsInstance
    }

    @Transactional
    def update(Shows showsInstance) {
        if (showsInstance == null) {
            notFound()
            return
        }

        if (showsInstance.hasErrors()) {
            respond showsInstance.errors, view:'edit'
            return
        }

        showsInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Shows.label', default: 'Shows'), showsInstance.id])
                redirect showsInstance
            }
            '*'{ respond showsInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Shows showsInstance) {

        if (showsInstance == null) {
            notFound()
            return
        }

        showsInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Shows.label', default: 'Shows'), showsInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shows.label', default: 'Shows'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
