package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*

@Entity
@View(extendsView="super.DEFAULT")
class StockFabricaPDetalle13 extends StockFabricaPDetalle{
    @ManyToOne
    StockFabricaP stockFabricaP
}
