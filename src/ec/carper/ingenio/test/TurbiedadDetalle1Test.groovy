package ec.carper.ingenio.test

import ec.carper.ingenio.model.TurbiedadDetalle1
import ec.carper.ingenio.util.*
 
import java.sql.Timestamp

import org.apache.commons.logging.*
import org.openxava.jpa.*
import org.openxava.model.*
import static org.openxava.jpa.XPersistence.*
import org.openxava.tests.*

class TurbiedadDetalle1Test extends ModuleTestBase {

    private static Log log = LogFactory.getLog(TurbiedadDetalle1Test.class)
 
    TurbiedadDetalle1Test(String testName) {
        super(testName, "Ingenio", "TurbiedadDetalle1")
    }
 
    void testGetTurJClaro(){
        String hora      = "2019-08-07 09:00:00"
       
        //def turJClaro = new TurbiedadDetalle1().getValorTurJClaro(Aux.instance.diaTrabajoId, Util.instance.toTimestamp(hora))
        def turJClaro = SqlUtil.instance.getValorDetalleCampoXHora(Aux.instance.diaTrabajoId, Util.instance.toTimestamp(hora), "turbiedad", "TurbiedadDetalle1", "turJClaro")
        println turJClaro 
    }
}
