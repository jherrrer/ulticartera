package prafin

import org.springframework.dao.DataIntegrityViolationException

class ReciboController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [reciboInstanceList: Recibo.list(params), reciboInstanceTotal: Recibo.count()]
    }

    def create() {
        [reciboInstance: new Recibo(params)]
    }

    def save() {
        def reciboInstance = new Recibo(params)
        if (!reciboInstance.save(flush: true)) {
            render(view: "create", model: [reciboInstance: reciboInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'recibo.label', default: 'Recibo'), reciboInstance.id])
        redirect(action: "show", id: reciboInstance.id)
    }

    def show(Long id) {
        def reciboInstance = Recibo.get(id)
        if (!reciboInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recibo.label', default: 'Recibo'), id])
            redirect(action: "list")
            return
        }

        [reciboInstance: reciboInstance]
    }

    def edit(Long id) {
        def reciboInstance = Recibo.get(id)
        if (!reciboInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recibo.label', default: 'Recibo'), id])
            redirect(action: "list")
            return
        }

        [reciboInstance: reciboInstance]
    }

    def update(Long id, Long version) {
        def reciboInstance = Recibo.get(id)
        if (!reciboInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recibo.label', default: 'Recibo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reciboInstance.version > version) {
                reciboInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'recibo.label', default: 'Recibo')] as Object[],
                          "Another user has updated this Recibo while you were editing")
                render(view: "edit", model: [reciboInstance: reciboInstance])
                return
            }
        }

        reciboInstance.properties = params

        if (!reciboInstance.save(flush: true)) {
            render(view: "edit", model: [reciboInstance: reciboInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'recibo.label', default: 'Recibo'), reciboInstance.id])
        redirect(action: "show", id: reciboInstance.id)
    }

    def delete(Long id) {
        def reciboInstance = Recibo.get(id)
        if (!reciboInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recibo.label', default: 'Recibo'), id])
            redirect(action: "list")
            return
        }

        try {
            reciboInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'recibo.label', default: 'Recibo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'recibo.label', default: 'Recibo'), id])
            redirect(action: "show", id: id)
        }
    }
}
