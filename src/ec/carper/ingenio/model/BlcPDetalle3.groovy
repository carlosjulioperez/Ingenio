package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*

@Entity
@View(extendsView="super.DEFAULT")
class BlcPDetalle3 extends BlcPDetalle{
    @ManyToOne
    BlcP blcP
}
