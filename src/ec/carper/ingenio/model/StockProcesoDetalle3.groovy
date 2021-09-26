package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""#
    orden,indicador;
    valor
""")
class StockProcesoDetalle3 extends Identifiable{
   
    @ManyToOne
    StockProceso stockProceso

    @Column(length=2) @ReadOnly
    int orden
   
    @ManyToOne(fetch=FetchType.LAZY) @DescriptionsList @ReadOnly
    Indicador indicador

    @Digits(integer=10, fraction=4) @DisplaySize(6) @ReadOnly
    BigDecimal valor
    
}
