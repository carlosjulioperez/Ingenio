package ec.carper.ingenio.model

import ec.carper.ingenio.util.Util
import javax.persistence.*
import org.openxava.annotations.*
import org.openxava.model.*

@Embeddable
class ParoDetalle{

    // https://github.com/mariuszs/openxava/blob/master/source/src/test/java/org/openxava/test/model/Clerk.java
    @Stereotype("DATETIME") @Required
    java.sql.Timestamp inicioParo

    @Stereotype("DATETIME") @Required
    java.sql.Timestamp finParo

    @Depends("inicioParo, finParo") //Propiedad calculada
    @Stereotype("TIME") @Required
    String getCalTotalParo(){
        return (inicioParo!=null && finParo!=null) ? 
            Util.instance.toTimeString( 
            Math.abs (finParo.getTime()-inicioParo.getTime()) / 1000*60*60 ): ""
    }
    
    String totalParo

    @ManyToOne(fetch=FetchType.LAZY)
    @DescriptionsList
    Area area

    @Column(length=100) @Required
    String descripcion

    // java.sql.Timestamp getTiempoTotalParada(){
    //     java.sql.Timestamp total = new java.sql.Timestamp();
    // }

    @PrePersist // Ejecutado justo antes de grabar el objeto por primera vez
    private void preGrabar() throws Exception {
        recalcularTotalParo()
    }

    public void recalcularTotalParo() {
        setTotalParo(getCalTotalParo())
    }

}