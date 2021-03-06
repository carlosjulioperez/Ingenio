package ec.carper.ingenio.model

import ec.carper.ingenio.util.*

import java.text.SimpleDateFormat
import java.time.format.*
import javax.persistence.*

import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.jpa.*
import org.openxava.model.*
import org.openxava.util.*
import org.openxava.validators.*
import static org.openxava.jpa.XPersistence.*

import java.time.LocalDate

@Entity
@Tab(properties="""
    diaTrabajo.fecha,
    wH2O,
    wBagazo,
    polReal,
    brixExtracto,
    polExtracto,
    tamizVacioM0,
    muestraHumM1,
    muestraSecaM2,
    porcHumedad,
    brix,
    porcFibra,
    porcSacarosa,
    porcSacJR,
    gradosAguaMac
""")
@View(members="""
    diaTrabajo, wH2OTmp, wBagazoTmp;
    titAnaBag { 
        detalle 
    }
""")
class Bagazo extends Formulario {

    // Facilidad para el usuario... ********************
    boolean itemsPorHoraCreados

    @DisplaySize(6)
    BigDecimal wH2OTmp
    
    @DisplaySize(6)
    BigDecimal wBagazoTmp
    //**************************************************

    BigDecimal wH2O
    BigDecimal wBagazo
    BigDecimal polReal
    BigDecimal brixExtracto
    BigDecimal polExtracto
    BigDecimal tamizVacioM0
    BigDecimal muestraHumM1
    BigDecimal muestraSecaM2
    BigDecimal porcHumedad
    BigDecimal brix
    BigDecimal porcFibra
    BigDecimal porcSacarosa
    BigDecimal porcSacJR
    BigDecimal gradosAguaMac
    
    @OneToMany (mappedBy="bagazo", cascade=CascadeType.ALL) @XOrderBy("hora")
    @ListProperties("""
        hora,
        wH2O           [bagazo.promWH2O],
        wBagazo        [bagazo.promWBagazo],
        polReal        [bagazo.promPolReal],
        brixExtracto   [bagazo.promBrixExtracto],
        polExtracto    [bagazo.promPolExtracto],
        tamizVacioM0   [bagazo.promTamizVacioM0],
        muestraHumM1   [bagazo.promMuestraHumM1],
        muestraSecaM2  [bagazo.promMuestraSecaM2],
        porcHumedad    [bagazo.promPorcHumedad],
        brix           [bagazo.promBrix],
        porcFibra      [bagazo.promPorcFibra],
        porcSacarosa   [bagazo.promPorcSacarosa],
        porcSacJR      [bagazo.promPorcSacJR],
        gradosAguaMac  [bagazo.promGradosAguaMac]
    """)
    Collection<BagazoDetalle>detalle

    BigDecimal getPromWH2O(){
        return super.getPromedio(detalle, "wH2O", 2)
    }
    BigDecimal getPromWBagazo(){
        return super.getPromedio(detalle, "wBagazo", 2)
    }
    BigDecimal getPromPolReal(){
        return super.getPromedio(detalle, "polReal", 2)
    }
    BigDecimal getPromBrixExtracto(){
        return super.getPromedio(detalle, "brixExtracto", 2)
    }
    BigDecimal getPromPolExtracto(){
        return super.getPromedio(detalle, "polExtracto", 2)
    }
    BigDecimal getPromTamizVacioM0(){
        return super.getPromedio(detalle, "tamizVacioM0", 2)
    }
    BigDecimal getPromMuestraHumM1(){
        return super.getPromedio(detalle, "muestraHumM1", 2)
    }
    BigDecimal getPromMuestraSecaM2(){
        return super.getPromedio(detalle, "muestraSecaM2", 2)
    }
    BigDecimal getPromPorcHumedad(){
        return super.getPromedio(detalle, "porcHumedad", 2)
    }
    BigDecimal getPromBrix(){
        return super.getPromedio(detalle, "brix", 2)
    }
    BigDecimal getPromPorcFibra(){
        return super.getPromedio(detalle, "porcFibra", 2)
    }
    BigDecimal getPromPorcSacarosa(){
        return super.getPromedio(detalle, "porcSacarosa", 2)
    }
    BigDecimal getPromPorcSacJR(){
        return super.getPromedio(detalle, "porcSacJR", 2)
    }
    BigDecimal getPromGradosAguaMac(){
        return super.getPromedio(detalle, "gradosAguaMac", 2)
    }
    
    void actualizar() throws ValidationException{
        try{

            this.wH2O          = promWH2O
            this.wBagazo       = promWBagazo
            this.polReal       = promPolReal
            this.brixExtracto  = promBrixExtracto
            this.polExtracto   = promPolExtracto
            this.tamizVacioM0  = promTamizVacioM0
            this.muestraHumM1  = promMuestraHumM1
            this.muestraSecaM2 = promMuestraSecaM2
            this.porcHumedad   = promPorcHumedad
            this.brix          = promBrix
            this.porcFibra     = promPorcFibra
            this.porcSacarosa  = promPorcSacarosa
            this.porcSacJR     = promPorcSacJR
            this.gradosAguaMac = promGradosAguaMac

            XPersistence.getManager().persist(this)

        }catch(Exception ex){
            throw new SystemException("registro_no_actualizado", ex)
        }
    }
    
    BigDecimal getValorPorcHumedad(String diaTrabajoId){
        Query query = getManager().createQuery("SELECT turJClaro FROM TurbiedadDetalle1 WHERE turbiedad.diaTrabajo.id = :diaTrabajoId AND hora = :hora ORDER BY hora")
        query.setParameter("diaTrabajoId", diaTrabajoId)
        query.setParameter("hora", hora)
        
        return query.resultList[0]?: 0
    }

    void crearItemsPorHora() throws ValidationException{
        try{
            this.itemsPorHoraCreados = true
            getManager().persist(this)
            crearItems(this)
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }

    void crearItems(Bagazo bagazo) {
        try{
            def d     = SqlUtil.instance.getDiaTrabajo(diaTrabajo.id)
            def hora  = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaDesde, diaTrabajo.id)
            def horaF = SqlUtil.instance.obtenerFecha(d.turnoTrabajo.horaHasta, diaTrabajo.id)

            while(hora < horaF ) {
                def det = new BagazoDetalle(bagazo: bagazo, horaS: Util.instance.getHoraS(hora), hora: hora, wH2O: bagazo.wH2OTmp, wBagazo: bagazo.wBagazoTmp)
                getManager().persist(det)
                hora = Util.instance.agregarHora(hora) // Incremento de hora
            }
            // Agregar la última hora configurada
            // def det = new BagazoDetalle(bagazo: bagazo, horaS: Util.instance.getHoraS(horaF), hora: horaF, wH2O: bagazo.wH2OTmp, wBagazo: bagazo.wBagazoTmp)
            // getManager().persist(det)
        }catch(Exception ex){
            throw new SystemException("items_por_hora_no_creados", ex)
        }
    }
}
