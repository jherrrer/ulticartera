package prafin

import org.springframework.dao.DataIntegrityViolationException

class CarteraController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [carteraInstanceList: Cartera.list(params), carteraInstanceTotal: Cartera.count()]
    }

    def create() {
        [carteraInstance: new Cartera(params)]
    }

    def save() {
        def carteraInstance = new Cartera(params)
        if (!carteraInstance.save(flush: true)) {
            render(view: "create", model: [carteraInstance: carteraInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'cartera.label', default: 'Cartera'), carteraInstance.id])
        redirect(action: "show", id: carteraInstance.id)
    }

    def show(Long id) {
        def carteraInstance = Cartera.get(id)
        if (!carteraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cartera.label', default: 'Cartera'), id])
            redirect(action: "list")
            return
        }

        [carteraInstance: carteraInstance]
    }

    def edit(Long id) {
        def carteraInstance = Cartera.get(id)
        if (!carteraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cartera.label', default: 'Cartera'), id])
            redirect(action: "list")
            return
        }

        [carteraInstance: carteraInstance]
    }

    def update(Long id, Long version) {
        def carteraInstance = Cartera.get(id)
        if (!carteraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cartera.label', default: 'Cartera'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (carteraInstance.version > version) {
                carteraInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'cartera.label', default: 'Cartera')] as Object[],
                          "Another user has updated this Cartera while you were editing")
                render(view: "edit", model: [carteraInstance: carteraInstance])
                return
            }
        }

        carteraInstance.properties = params

        if (!carteraInstance.save(flush: true)) {
            render(view: "edit", model: [carteraInstance: carteraInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'cartera.label', default: 'Cartera'), carteraInstance.id])
        redirect(action: "show", id: carteraInstance.id)
    }

    def delete(Long id) {
        def carteraInstance = Cartera.get(id)
        if (!carteraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'cartera.label', default: 'Cartera'), id])
            redirect(action: "list")
            return
        }

        try {
            carteraInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'cartera.label', default: 'Cartera'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'cartera.label', default: 'Cartera'), id])
            redirect(action: "show", id: id)
        }
    }
}
