package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@MappedSuperclass
@View(members="#orden,indicador,unidad;valor,modificable")
class StockFabricaPDetalle extends Identifiable{
   
    @ManyToOne
    StockFabricaP stockFabricaP 

    @Column(length=2)
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Indicador indicador
    
    @DisplaySize(5)
    BigDecimal valor 
    
    boolean modificable
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList
    Unidad unidad
    
}
