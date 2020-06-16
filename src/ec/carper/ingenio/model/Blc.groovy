package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

@Entity
@Tab(properties="""
    diaTrabajo.descripcion
""")
@View(members="""
    diaTrabajo;
    datos {
        datosDia { detalle1 }
    }
""")
class Blc extends DiaTrabajoEditable {

    @CollectionView("Edicion")
    @OneToMany (mappedBy="blc", cascade=CascadeType.ALL)
    @EditOnly
    Collection<BlcDetalle1>detalle1

}
