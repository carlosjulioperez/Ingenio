package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.*

import java.sql.Timestamp
import org.openxava.actions.*

class MielesNutschDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        def diaTrabajoId = (String)getView().getRoot().getValue("diaTrabajo.id")
        String horaS = (String)getView().getValue("horaS")
        getView().setValue("hora", (diaTrabajoId && horaS) ? SqlUtil.instance.obtenerFecha(horaS, diaTrabajoId): null) 
        
        BigDecimal maBri  = (BigDecimal)getView().getValue("maBri")
        BigDecimal maBri2 = (BigDecimal)getView().getValue("maBri2")
        BigDecimal maPol  = (BigDecimal)getView().getValue("maPol")
        getView().setValue("maSac", (maBri && maPol) ? Calculo.instance.getSac(maBri, maPol, 6, 2): null)
        
        BigDecimal maSac = (BigDecimal)getView().getValue("maSac")
        getView().setValue("maPur", (maSac && maBri2) ? Calculo.instance.getPorc(maSac, maBri2, 2): null)
        
        getView().setValue("maBri2", maBri ? Calculo.instance.redondear(maBri*6, 2): null)

        BigDecimal mbBri  = (BigDecimal)getView().getValue("mbBri")
        BigDecimal mbBri2 = (BigDecimal)getView().getValue("mbBri2")
        BigDecimal mbPol  = (BigDecimal)getView().getValue("mbPol")
        getView().setValue("mbSac", (mbBri && mbPol)? Calculo.instance.getSac(mbBri, mbPol, 6, 2): null)
        
        BigDecimal mbSac = (BigDecimal)getView().getValue("mbSac")
        getView().setValue("mbPur", (mbSac && mbBri2) ? Calculo.instance.getPorc(mbSac, mbBri2, 2): null)

        getView().setValue("mbBri2", mbBri ? Calculo.instance.redondear(mbBri*6, 2): null)

        BigDecimal mcBri  = (BigDecimal)getView().getValue("mcBri")
        BigDecimal mcBri2 = (BigDecimal)getView().getValue("mcBri2")
        BigDecimal mcPol  = (BigDecimal)getView().getValue("mcPol")
        getView().setValue("mcSac", (mcBri && mcPol) ? Calculo.instance.getSac(mcBri, mcPol, 6, 2): null)
        
        BigDecimal mcSac = (BigDecimal)getView().getValue("mcSac")
        getView().setValue("mcPur", (mcSac && mcBri2) ? Calculo.instance.getPorc(mcSac, mcBri2, 2): null)
        getView().setValue("mcBri2", mcBri ? Calculo.instance.redondear(mcBri*6, 2): null)

    }
    
}
