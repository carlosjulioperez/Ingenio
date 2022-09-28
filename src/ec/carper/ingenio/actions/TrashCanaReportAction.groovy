package ec.carper.ingenio.actions

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
 
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;
 
import org.openxava.actions.*;
import org.openxava.jpa.XPersistence;
import org.openxava.model.*;
import org.openxava.util.*;
import org.openxava.validators.*;

import ec.carper.ingenio.model.TrashCana;

public class TrashCanaReportAction extends JasperReportBaseAction {

	private static Log log = LogFactory.getLog(TrashCanaReportAction.class);
	
	private TrashCana trashCana;

	public Map getParameters() throws Exception {
        Messages errores = MapFacade.validate("TrashCana", getView().getValues());
        if (errores.contains()) throw new ValidationException(errores);
        Map parametros = new HashMap();
        //parametros.put("familia", getSubfamilia().getFamilia().getDescripcion());
        //parametros.put("subfamilia", getSubfamilia().getDescripcion());
        return parametros;
    }
	
	protected JRDataSource getDataSource() throws Exception {
		Vector collection = new Vector();
		collection.add( getTrashCana() );
        return new JRBeanCollectionDataSource( collection );
    }
	
	protected String getJRXML() {
        return "trashcana.jrxml"; // Para leer del classpath (carpeta "reports" o "informes")
        //return "/home/javi/Products.jrxml"; // Para leer del sistema de ficheros
    }

	private TrashCana getTrashCana() throws Exception {
		if (trashCana == null){
			String id = getView().getValueString("id");
			trashCana = (TrashCana) XPersistence.getManager().find(TrashCana.class, id);
		}
		return trashCana;
	}
}
