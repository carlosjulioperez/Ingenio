package ec.carper.ingenio.util

import javax.persistence.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

@Singleton
class SqlUtil{

    BigDecimal getPromedio (String diaTrabajoId, String modulo, String campo){
        Query query = getManager().createQuery("SELECT ${campo} FROM ${modulo} WHERE diaTrabajo.id = :diaTrabajoId")
        query.setParameter("diaTrabajoId", diaTrabajoId)

        def lista = query.resultList
        return lista ? lista[0]: 0
    }

}