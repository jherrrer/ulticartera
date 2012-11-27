package prafin

import org.grails.jaxrs.provider.DomainObjectNotFoundException

class ReciboResourceService {
    
    def create(Recibo dto) {
		
		def list=Cartera.findAll()
		for(cartera in list){
			if(cartera.getCC().equals(dto.getCC())){
				cartera.HasMany.put(dto.getCC(), dto);
			}
			
		}
        dto.save()
    }

    def read(def id) {
        def obj = Recibo.get(id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Recibo.class, id)
        }
        obj
    }
    
    def readAll() {
        Recibo.findAll()
    }
    
    def update(Recibo dto) {
        def obj = Recibo.get(dto.id)
        if (!obj) {
            throw new DomainObjectNotFoundException(Recibo.class, dto.id)
        }
        obj.properties = dto.properties 
        obj
    }
    
    void delete(def id) {
        def obj = Recibo.get(id)
        if (obj) { 
            obj.delete()
        }
    }
    
}

