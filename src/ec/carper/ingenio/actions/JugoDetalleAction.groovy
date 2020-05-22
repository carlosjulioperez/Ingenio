package ec.carper.ingenio.actions

import ec.carper.ingenio.model.*
import ec.carper.ingenio.util.Calculo

import java.sql.Timestamp
import org.openxava.actions.*

class JugoDetalleAction extends OnChangePropertyBaseAction{

    void execute() throws Exception{

        BigDecimal jeBri = (BigDecimal)getView().getValue("jeBri")
        BigDecimal jePol = (BigDecimal)getView().getValue("jePol")
        if (jeBri && jePol)
            getView().setValue("jeSac", getSac(jeBri, jePol))
        BigDecimal jeSac = (BigDecimal)getView().getValue("jeSac")
        if (jeSac && jeBri)
            getView().setValue("jePur", getPur(jeSac, jeBri))

        BigDecimal jdBri = (BigDecimal)getView().getValue("jdBri")
        BigDecimal jdPol = (BigDecimal)getView().getValue("jdPol")
        if (jdBri && jdPol)
            getView().setValue("jdSac", getSac(jdBri, jdPol))
        BigDecimal jdSac = (BigDecimal)getView().getValue("jdSac")
        if (jdSac && jdBri)
            getView().setValue("jdPur", getPur(jdSac, jdBri))

        BigDecimal jcBri = (BigDecimal)getView().getValue("jcBri")
        BigDecimal jcPol = (BigDecimal)getView().getValue("jcPol")
        if (jcBri && jcPol)
            getView().setValue("jcSac", getSac(jcBri, jcPol))
        BigDecimal jcSac = (BigDecimal)getView().getValue("jcSac")
        if (jcSac && jcBri)
            getView().setValue("jcPur", getPur(jcSac, jcBri))

        BigDecimal jnBri = (BigDecimal)getView().getValue("jnBri")
        BigDecimal jnPol = (BigDecimal)getView().getValue("jnPol")
        if (jnBri && jnPol)
            getView().setValue("jnSac", getSac(jnBri, jnPol))
        BigDecimal jnSac = (BigDecimal)getView().getValue("jnSac")
        if (jnSac && jnBri)
            getView().setValue("jnPur", getPur(jnSac, jnBri))

        BigDecimal jrBri = (BigDecimal)getView().getValue("jrBri")
        BigDecimal jrPol = (BigDecimal)getView().getValue("jrPol")
        if (jrBri && jrPol)
            getView().setValue("jrSac", getSac(jrBri, jrPol))
        BigDecimal jrSac = (BigDecimal)getView().getValue("jrSac")
        if (jrSac && jrBri)
            getView().setValue("jrPur", getPur(jrSac, jrBri))

        BigDecimal jfBri = (BigDecimal)getView().getValue("jfBri")
        BigDecimal jfPol = (BigDecimal)getView().getValue("jfPol")
        if (jfBri && jfPol)
            getView().setValue("jfSac", getSac(jfBri, jfPol))
        BigDecimal jfSac = (BigDecimal)getView().getValue("jfSac")
        if (jfSac && jfBri)
            getView().setValue("jfPur", getPur(jfSac, jfBri))

    }
    
    private BigDecimal getSac(BigDecimal bri, BigDecimal pol){
        return Calculo.instance.getSac(bri, pol).setScale(2, BigDecimal.ROUND_HALF_UP)
    }

    private BigDecimal getPur(BigDecimal sac, BigDecimal bri){
        return Calculo.instance.getPur(sac, bri).setScale(2, BigDecimal.ROUND_HALF_UP)
    }

}
