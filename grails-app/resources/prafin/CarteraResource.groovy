package prafin

import static org.grails.jaxrs.response.Responses.*

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.Produces
import javax.ws.rs.PUT
import javax.ws.rs.core.Response

import org.grails.jaxrs.provider.DomainObjectNotFoundException

@Consumes(['application/json'])
@Produces(['application/json'])
class CarteraResource {
    
    def carteraResourceService
    def id
    
    @GET
    Response read() {
        ok carteraResourceService.read(id)
    }
    
    @PUT
    Response update(Cartera dto) {
        dto.id = id
        ok carteraResourceService.update(dto)
    }
    
    @DELETE
    void delete() {
        carteraResourceService.delete(id)
    }
    
}

