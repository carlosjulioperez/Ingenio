package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class CanaDetalle2Action extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        String diaTrabajoId     = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaSD = (String)getView().getValue("horaSD")
        if (horaSD)
            getView().setValue("horaDesde", SqlUtil.instance.obtenerFecha(horaSD, diaTrabajoId)) 
        
        String horaSH = (String)getView().getValue("horaSH")
        if (horaSH)
            getView().setValue("horaHasta", SqlUtil.instance.obtenerFecha(horaSH, diaTrabajoId)) 
        
        Timestamp horaDesde     = (Timestamp)getView().getValue("horaDesde")
        Timestamp horaHasta     = (Timestamp)getView().getValue("horaHasta")
        BigDecimal brixExtracto = (BigDecimal)getView().getValue("brixExtracto")
        BigDecimal polExtracto  = (BigDecimal)getView().getValue("polExtracto")

        BigDecimal wH2O         = null
        BigDecimal wCana        = null

        // println ">>>>> " + horaDesde
        // println ">>>>> " + horaHasta
        
        def lista = new CanaDetalle1().getCampos(diaTrabajoId, horaDesde)
        if (lista){
            wH2O  = lista[0]
            wCana = lista[1]
        }

        if (brixExtracto && polExtracto) 
            getView().setValue("polReal", Calculo.instance.getSac(brixExtracto,polExtracto,1,2))
        
        // println("values=" + getView().getRoot().getValues());
        // println("values=" + getView().getValues());
        
        // =AVERAGE(K6:K11)
        if (horaDesde && horaHasta){
            getView().setValue("porcHumedad", 
                new CanaDetalle1().getPromPorcHumedad(
                    diaTrabajoId, horaDesde, horaHasta
                )
            )
        }
        
        // =U6*(C6-0,25*D6+0,0125*D6*W6)/D6/(1-0,0125*U6)
        BigDecimal porcHumedad   = (BigDecimal)getView().getValue("porcHumedad")
        if (brixExtracto && wH2O && wCana && porcHumedad) {
            getView().setValue("brix", Calculo.instance.getBrix(brixExtracto, wH2O, wCana, porcHumedad, 2))
        }

        // =100-V6-W6
        BigDecimal brix = (BigDecimal)getView().getValue("brix")
        if (porcHumedad && brix)
            getView().setValue("porcFibra", Calculo.instance.getPorcFibra(porcHumedad, brix))

        // =T6*(D6+C6-0,0125*Y6*D6)/D6
        BigDecimal polReal = (BigDecimal)getView().getValue("polReal")
        BigDecimal porcFibra = (BigDecimal)getView().getValue("porcFibra")
        if (polReal && wH2O && wCana && porcFibra) {
            getView().setValue("porcSacarosa", Calculo.instance.getPorcSacarosa(polReal, wCana, wH2O, porcFibra, 2))
        }

        BigDecimal porcSacarosa = (BigDecimal)getView().getValue("porcSacarosa")
        if (porcSacarosa && brix)
            getView().setValue("pureza", Calculo.instance.getPorc(porcSacarosa,brix,2))

    }
}
