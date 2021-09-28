package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import javax.persistence.Query;
import org.openxava.actions.*
import org.openxava.jpa.*
import static org.openxava.jpa.XPersistence.*;

class StockProcesoDetalle2Action extends OnChangePropertyBaseAction{

    def diaTrabajoId = ""

    // No obtengo los valores, solo disparo la acción para realizar los cálculos
    void execute() throws Exception{

        diaTrabajoId     = (String)getView().getRoot().getValue("diaTrabajo.id")
        def materialId   = (String)getView().getValue("material.id")
        def material     = SqlUtil.instance.getMaterial(materialId)
        
        if (material.campo){
            // Obtener el material y dependiendo del campo hacer las validaciones
            // Los cálculos se realizan cuando se cambian los valores de volumen1 o eq
            
            def volumen1 = getView().getValue("volumen1")
            def eq       = getView().getValue("eq")
          
            def (porcBrix, porcSac, pureza) = [0, 0, 0]
            if (material.campo=="jDiluidoBr"){
                porcBrix = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdBri")
                porcSac  = SqlUtil.instance.getValorCampo(diaTrabajoId, "Jugo", "jdSac")
            }else{
                porcBrix = 0
                porcSac  = 0
            }

            // Calculos
            def factor   = new FactorVolumen().getValor(0, eq+1)
            def volumen2 = factor ? Calculo.instance.redondear(volumen1/factor, 3): 0
            def densidad = new BrixDensidadWp().getP(porcBrix)
            def peso     = Calculo.instance.redondear(densidad*volumen2/1000, 3)
            def tonBrix  = Calculo.instance.redondear(peso*porcBrix/100, 3)
            def tonSac   = Calculo.instance.redondear(peso*porcSac/100, 3)

            if (material.campo=="jDiluidoBr")
                pureza   = porcBrix ? Calculo.instance.redondear(porcSac/porcBrix*100, 3): 0

            getView().setValue("volumen1" , volumen1?:null)
            getView().setValue("volumen2" , volumen2?:null)
            getView().setValue("peso"     , peso?:null)
            getView().setValue("porcBrix" , porcBrix?:null)
            getView().setValue("tonBrix"  , tonBrix?:null)
            getView().setValue("porcSac"  , porcSac?:null)
            getView().setValue("tonSac"   , tonSac?:null)
            getView().setValue("pureza"   , pureza?:null)
            getView().setValue("densidad" , densidad?:null)
            getView().setValue("factor"   , factor?:null)

            // 26-09-2021
            // Cálculos para StockProcesoDetalle3 (de la parte resaltada en amarillo)
            // ='AZUCAR GRANEL'!M30
        
            def c75 = SqlUtil.instance.getValorCampo(diaTrabajoId, "AzucarGranel", "pol")
            setValor("PurProAzu", c75)

            def c76 = SqlUtil.instance.getValorCampo(diaTrabajoId, "Mieles" , "mfSac")
            setValor("SacMel", c76)
            
            def c77 = SqlUtil.instance.getValorCampo(diaTrabajoId, "Mieles" , "mfPur")
            setValor("PurProMel", c77)

            //f78=((C75*(M67-C77))/(M67*(C75-C77)))*100
            def m67 = SqlUtil.instance.getValorCampo(diaTrabajoId, "StockProceso" , "pureza")
            def f78 = Calculo.instance.redondear(((c75*(m67-c77))/(m67*(c75-c77)))*100, 4)
            setValor("PorRecSjm", f78)

            def l67 = SqlUtil.instance.getValorCampo(diaTrabajoId, "StockProceso" , "tonSac")
            def f79 = Calculo.instance.redondear(l67*f78/100,4)
            setValor("TonSacRec", f79)

            def f80 = Calculo.instance.redondear(f79/c75*100, 4)
            setValor("TonAzuRec", f80)

            def f81 = l67 - f80
            setValor("TonSacMelaza", f81)

            def f82 = Calculo.instance.redondear(f81/c76*100, 4)
            setValor("TonMelRec", f82)

            // =BLC!H73+'Stock Proceso'!F80-'Stock Proceso'!F83
            // Toneladas Sacarosa Azúcar Hecha
            // TonSacAzuHec. TODO: validar 568
            def f83 = 0
            def d = SqlUtil.instance.getDetallePorDTI(diaTrabajoId, "blc", "BlcDetalle12", "TonSacAzuHec")
            def h73 = d ? (d.unidades?:0): 0
            
            println "\n h73: $h73, f80: $f80, f83: $f83"
            def f84 = h73 + f80 - f83
            
            println "\n f84: $f84"
            setValor("TonSacRecobr", f84)

            // =BLC!H72+'Stock Proceso'!F81-'Stock Proceso'!F86
            // Toneladas Sacarosa Miel Final - Melaza
            // TonSacMieFin
            def f86 = 0
            d = SqlUtil.instance.getDetallePorDTI(diaTrabajoId, "blc", "BlcDetalle12", "TonSacMieFin")
            def h72 = d ? (d.unidades?:0): 0
            def f85 = h72 + f81 - f86
            setValor("TonSacNetMel", f85)

        }
    }

    void setValor(def campo, def nuevoValor){
        def modulo = "StockProcesoDetalle3"
        def campoFk = "stockProceso.diaTrabajo.id"
        
        println "\n>>> campo: $campo, nuevoValor: $nuevoValor"
        def d = SqlUtil.instance.getDetallePorIndicador(diaTrabajoId, modulo, campoFk, campo)
        d.setValor(nuevoValor)
        getManager().persist(d)
    }

}
