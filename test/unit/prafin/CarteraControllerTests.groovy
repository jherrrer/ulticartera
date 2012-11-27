package prafin



import org.junit.*
import grails.test.mixin.*

@TestFor(CarteraController)
@Mock(Cartera)
class CarteraControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/cartera/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.carteraInstanceList.size() == 0
        assert model.carteraInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.carteraInstance != null
    }

    void testSave() {
        controller.save()

        assert model.carteraInstance != null
        assert view == '/cartera/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/cartera/show/1'
        assert controller.flash.message != null
        assert Cartera.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/cartera/list'

        populateValidParams(params)
        def cartera = new Cartera(params)

        assert cartera.save() != null

        params.id = cartera.id

        def model = controller.show()

        assert model.carteraInstance == cartera
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/cartera/list'

        populateValidParams(params)
        def cartera = new Cartera(params)

        assert cartera.save() != null

        params.id = cartera.id

        def model = controller.edit()

        assert model.carteraInstance == cartera
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/cartera/list'

        response.reset()

        populateValidParams(params)
        def cartera = new Cartera(params)

        assert cartera.save() != null

        // test invalid parameters in update
        params.id = cartera.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/cartera/edit"
        assert model.carteraInstance != null

        cartera.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/cartera/show/$cartera.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        cartera.clearErrors()

        populateValidParams(params)
        params.id = cartera.id
        params.version = -1
        controller.update()

        assert view == "/cartera/edit"
        assert model.carteraInstance != null
        assert model.carteraInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/cartera/list'

        response.reset()

        populateValidParams(params)
        def cartera = new Cartera(params)

        assert cartera.save() != null
        assert Cartera.count() == 1

        params.id = cartera.id

        controller.delete()

        assert Cartera.count() == 0
        assert Cartera.get(cartera.id) == null
        assert response.redirectedUrl == '/cartera/list'
    }
}
