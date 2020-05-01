package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import org.openxava.actions.*

class TrashDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        BigDecimal cogollos     = (BigDecimal)getView().getValue("cogollos")
        BigDecimal cantidadCana = (BigDecimal)getView().getValue("cantidadCana")
        BigDecimal netaCana     = (BigDecimal)getView().getValue("netaCana")
        BigDecimal hojas        = (BigDecimal)getView().getValue("hojas")

        BigDecimal cepa          = (BigDecimal)getView().getValue("cepa")
        BigDecimal canaSeca      = (BigDecimal)getView().getValue("canaSeca")
        BigDecimal suelo         = (BigDecimal)getView().getValue("suelo")
        BigDecimal otros         = (BigDecimal)getView().getValue("otros")
        BigDecimal canaInfectada = (BigDecimal)getView().getValue("canaInfectada")

        if (cogollos!=null)
           getView().setValue("calPorcCogollos", (cogollos*100/cantidadCana).setScale(2, BigDecimal.ROUND_HALF_UP))

        if (hojas!=null)
           getView().setValue("calPorcHojas", (hojas*100/cantidadCana).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        if (cepa!=null)
           getView().setValue("calPorcCepa", (cepa*100/cantidadCana).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        if (canaSeca!=null)
           getView().setValue("calPorcCanaSeca", (canaSeca*100/cantidadCana).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        if (suelo!=null)
           getView().setValue("calPorcSuelo", (suelo*100/cantidadCana).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        if (otros!=null)
           getView().setValue("calPorcOtros", (otros*100/cantidadCana).setScale(2, BigDecimal.ROUND_HALF_UP))
        
        if (canaInfectada!=null)
           getView().setValue("calPorcCanaInfectada", (canaInfectada*100/netaCana).setScale(2, BigDecimal.ROUND_HALF_UP))
        
    }
}