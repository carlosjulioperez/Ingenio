package ec.carper.ingenio.model

import java.time.LocalDate
import javax.persistence.*
import javax.validation.constraints.Digits
import org.openxava.annotations.*
import org.openxava.calculators.*
import org.openxava.model.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*

import ec.carper.ingenio.actions.*

@Entity
@View(members="""#
    horaS, hora;
    ini, fin;
    tot;
    horaSBrixJDil, horaBrixJDil;
    brixJDil;
    p, tonJugo
""")
class FlujoJugoDetalle extends Identifiable {
    
    @ManyToOne //Sin lazy fetching porque falla al quitar un detalle desde el padre
    FlujoJugo flujoJugo
    
    @Stereotype("TIME") @Column(length=5) @OnChange(FlujoJugoDetalleAction.class) @Required
    String horaS

    @Stereotype("DATETIME") @ReadOnly @Required
    java.sql.Timestamp hora
    
    @DisplaySize(6) @ReadOnly
    Integer ini
    
    @DisplaySize(6) @OnChange(FlujoJugoDetalleAction.class)
    Integer fin 
    
    @DisplaySize(6) @ReadOnly
    Integer tot
    
    @Stereotype("TIME") @Column(length=5) @OnChange(FlujoJugoDetalleAction.class)
    String horaSBrixJDil

    @Stereotype("DATETIME") @ReadOnly
    java.sql.Timestamp horaBrixJDil
    
    @DisplaySize(6) @OnChange(FlujoJugoDetalleAction.class)
    BigDecimal brixJDil
    
    @Digits(integer=10, fraction=3) @DisplaySize(6) @ReadOnly
    BigDecimal p

    @Digits(integer=12, fraction=6) @DisplaySize(12) @ReadOnly
    BigDecimal tonJugo
    
}
