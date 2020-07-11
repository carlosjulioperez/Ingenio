package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""#
    cana, j1Extracto;
    jDiluido, jClaro;
    jFiltrado, mClara;
    mielA, mielB;
    mielF
""")
class Cto24HDetalle1 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cto24H cto24H
    
    @DisplaySize(6)
    BigDecimal cana  
    
    @DisplaySize(6)
    BigDecimal j1Extracto  
    
    @DisplaySize(6)
    BigDecimal jDiluido    
    
    @DisplaySize(6)
    BigDecimal jClaro      
    
    @DisplaySize(6)
    BigDecimal jFiltrado   
    
    @DisplaySize(6)
    BigDecimal mClara
    
    @DisplaySize(6)
    BigDecimal mielA
    
    @DisplaySize(6)
    BigDecimal mielB
    
    @DisplaySize(6)
    BigDecimal mielF
    
}
