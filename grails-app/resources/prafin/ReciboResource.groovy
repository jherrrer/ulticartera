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
class ReciboResource {
    
    def reciboResourceService
    def id
    
    @GET
    Response read() {
       def  list =Recibo.findAll()
			def list2=[]
		for(recibo in list){
			   if(((Recibo)recibo).getCC().equals(id)){
				   list2.add(recibo)
			   }
		   } }
    
    @PUT
    Response update(Recibo dto) {
        dto.id = id
        ok reciboResourceService.update(dto)
    }
    
    @DELETE
    void delete() {
        reciboResourceService.delete(id)
    }
    
}

