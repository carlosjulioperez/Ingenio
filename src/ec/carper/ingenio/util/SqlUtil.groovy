package ec.carper.ingenio.util

import ec.carper.ingenio.model.*

import javax.persistence.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

@Singleton
class SqlUtil{

    BigDecimal getValorCampo(String diaTrabajoId, String modulo, String campo){
        Query query = getManager().createQuery("SELECT ${campo} FROM ${modulo} WHERE diaTrabajo.id = :diaTrabajoId")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        return query.resultList[0]?: 0
    }
    
    BigDecimal getValorCampoBlc(String diaTrabajoId, String detalle, String campo){
        Query query = getManager().createQuery("""
            SELECT valor FROM ${detalle}
            WHERE blc.diaTrabajo.id = :diaTrabajoId
            AND material.campo = '${campo}'
        """)
        query.setParameter("diaTrabajoId", diaTrabajoId)
        return query.resultList[0]?: 0
    }

    BigDecimal getValorDetalleCampo(String diaTrabajoId, String maestro, String detalle, String campo){
        Query query = getManager().createQuery("""
            SELECT ${campo} FROM ${detalle}
            WHERE ${maestro}.diaTrabajo.id = :diaTrabajoId
        """)
        query.setParameter("diaTrabajoId", diaTrabajoId)
        return query.resultList[0]?: 0
    }
    
    String getCampo(String diaTrabajoId, String modulo, String campo){
        Query query = getManager().createQuery("SELECT ${campo} FROM ${modulo} WHERE diaTrabajo.id = :diaTrabajoId")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        return query.resultList[0]?: ""
    }

    def getDiaTrabajo(String diaTrabajoId){
        return getManager().find(DiaTrabajo.class, diaTrabajoId)
    }
}
