package ec.carper.ingenio.model

import ec.carper.ingenio.actions.*
import ec.carper.ingenio.util.Calculo

import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.model.*

@Entity
@View(members="""#
    bv600, bv50;
    sc4, sc8;
    pf, pj;
    bs600, bs50;
    masa1, masa2;
    masa3, porcInso
""")
class Cto24HDetalle2 extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    Cto24H cto24H
    
    @OnChange(Cto24HDetalle2Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal bv600
    
    @OnChange(Cto24HDetalle2Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal bv50
    
    @OnChange(Cto24HDetalle2Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal sc4
    
    @OnChange(Cto24HDetalle2Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal sc8
    
    @OnChange(Cto24HDetalle2Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal pf
    
    @OnChange(Cto24HDetalle2Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal pj
    
    @OnChange(Cto24HDetalle2Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal bs600
    
    @OnChange(Cto24HDetalle2Action.class)
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal bs50
    
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal masa1
    
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal masa2
    
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal masa3
    
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal porcInso

    /*
    @Depends("bv600,bv50,sc4,sc8,pf")
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal getMasa1(){
        return (bv600 && bv50 && sc4 && sc8 && pf) ? (bv600 + bv50 + sc4 + sc8 + pf): 0
    }
    
    @Depends("masa1, pj")
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal getMasa2(){
        return (masa1 && pj) ? (masa1 + pj): 0
    }
    
    @Depends("bs600, bs50")
    @Digits(integer=4, fraction=4) @DisplaySize(6)
    BigDecimal getMasa3(){
        return (bs600 && bs50) ? (bs600 + bs50) : 0
    }
    
    @Depends("masa1, masa2, masa3")
    @DisplaySize(6)
    BigDecimal getPorcInso(){
        return (masa1 && masa2 && masa3) ? Calculo.instance.redondear((((masa3-masa1)/(masa2-masa1))*100), 2): 0
    }
    */
}
