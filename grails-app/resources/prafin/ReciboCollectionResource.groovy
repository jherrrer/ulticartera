package prafin

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.POST
import javax.ws.rs.core.Response

@Path('/api/recibo')
@Consumes(['application/json'])
@Produces(['application/json'])
class ReciboCollectionResource {

    def reciboResourceService
    
    @POST
    Response create(Recibo dto) {
        created reciboResourceService.create(dto)
    }

    @GET
    Response readAll() {
        ok reciboResourceService.readAll()
    }
    
    @Path('/{id}')
    ReciboResource getResource(@PathParam('id') Long id) {
        new ReciboResource(reciboResourceService: reciboResourceService, id:id)
    }
        
}
