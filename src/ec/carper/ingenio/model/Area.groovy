package ec.carper.ingenio.model

import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
class Area extends Identifiable{

    @Column(length=30) @Required
    String descripcion

}
