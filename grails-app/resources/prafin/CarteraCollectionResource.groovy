package prafin

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.POST
import javax.ws.rs.core.Response

@Path('/api/cartera')
@Consumes(['application/json'])
@Produces(['application/json'])
class CarteraCollectionResource {

    def carteraResourceService
    
    @POST
    Response create(Cartera dto) {
        created carteraResourceService.create(dto)
    }

    @GET
    Response readAll() {
        ok carteraResourceService.readAll()
    }
    
    @Path('/{id}')
    CarteraResource getResource(@PathParam('id') Long id) {
        new CarteraResource(carteraResourceService: carteraResourceService, id:id)
    }
        
}
