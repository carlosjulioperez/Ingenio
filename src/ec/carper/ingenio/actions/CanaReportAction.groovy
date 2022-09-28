package ec.carper.ingenio.actions

import ec.carper.ingenio.model.Cana;

import java.util.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.actions.*;
import org.openxava.jpa.XPersistence;
import org.openxava.model.*;
import org.openxava.util.*;
import org.openxava.validators.*;

public class CanaReportAction extends JasperReportBaseAction {

	private static Log log = LogFactory.getLog(CanaReportAction.class);
	
	private Cana cana;

	public Map getParameters() throws Exception {
        Messages errores = MapFacade.validate("Cana", getView().getValues());
        if (errores.contains()) throw new ValidationException(errores);
        Map parametros = new HashMap();
        //parametros.put("familia", getSubfamilia().getFamilia().getDescripcion());
        //parametros.put("subfamilia", getSubfamilia().getDescripcion());
        return parametros;
    }
	
	protected JRDataSource getDataSource() throws Exception {
		Vector collection = new Vector();
		collection.add( getCana() );
        return new JRBeanCollectionDataSource( collection );
    }
	
	protected String getJRXML() {
        return "cana.jrxml"; // Para leer del classpath (carpeta "reports" o "informes")
        //return "/home/javi/Products.jrxml"; // Para leer del sistema de ficheros
    }

	private Cana getCana() throws Exception {
		if (cana == null){
			String id = getView().getValueString("id");
			cana = (Cana) XPersistence.getManager().find(Cana.class, id);
		}
		return cana;
	}
}
