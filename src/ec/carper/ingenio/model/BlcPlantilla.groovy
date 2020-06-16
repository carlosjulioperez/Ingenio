package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="codigo,descripcion;detalle1")
class BlcPlantilla{
    @Id
    int codigo
    
    @Column(length=30) @Required
    String descripcion 

    @OneToMany (mappedBy="blcPlantilla", cascade=CascadeType.ALL)
    Collection<BlcDetalle1>detalle1
}

