package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""
    codigo, descripcion;
    titTqJDil { detalle1 }
""")
class StockFabricaP{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="stockFabricaP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockFabricaPDetalle1> detalle1
}