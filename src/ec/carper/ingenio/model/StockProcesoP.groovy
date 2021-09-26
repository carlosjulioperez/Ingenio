package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""
    codigo, descripcion;
    titDetalle { detalle }
    titTotales { detalle2 }
    titTotales { detalle3 }
""")
class StockProcesoP{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="stockProcesoP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockProcesoPDetalle> detalle

    @OneToMany (mappedBy="stockProcesoP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockProcesoPDetalle2> detalle2

    @OneToMany (mappedBy="stockProcesoP", cascade=CascadeType.ALL) @XOrderBy("orden")
    Collection<StockProcesoPDetalle3> detalle3
}
