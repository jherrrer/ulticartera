package prafin



import org.junit.*
import grails.test.mixin.*

@TestFor(ReciboController)
@Mock(Recibo)
class ReciboControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/recibo/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.reciboInstanceList.size() == 0
        assert model.reciboInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.reciboInstance != null
    }

    void testSave() {
        controller.save()

        assert model.reciboInstance != null
        assert view == '/recibo/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/recibo/show/1'
        assert controller.flash.message != null
        assert Recibo.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/recibo/list'

        populateValidParams(params)
        def recibo = new Recibo(params)

        assert recibo.save() != null

        params.id = recibo.id

        def model = controller.show()

        assert model.reciboInstance == recibo
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/recibo/list'

        populateValidParams(params)
        def recibo = new Recibo(params)

        assert recibo.save() != null

        params.id = recibo.id

        def model = controller.edit()

        assert model.reciboInstance == recibo
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/recibo/list'

        response.reset()

        populateValidParams(params)
        def recibo = new Recibo(params)

        assert recibo.save() != null

        // test invalid parameters in update
        params.id = recibo.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/recibo/edit"
        assert model.reciboInstance != null

        recibo.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/recibo/show/$recibo.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        recibo.clearErrors()

        populateValidParams(params)
        params.id = recibo.id
        params.version = -1
        controller.update()

        assert view == "/recibo/edit"
        assert model.reciboInstance != null
        assert model.reciboInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/recibo/list'

        response.reset()

        populateValidParams(params)
        def recibo = new Recibo(params)

        assert recibo.save() != null
        assert Recibo.count() == 1

        params.id = recibo.id

        controller.delete()

        assert Recibo.count() == 0
        assert Recibo.get(recibo.id) == null
        assert response.redirectedUrl == '/recibo/list'
    }
}
