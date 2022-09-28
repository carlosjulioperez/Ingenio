package ec.carper.ingenio.actions

import ec.carper.ingenio.model.Trash;

import java.util.*;

// import javax.servlet.*;
// import javax.servlet.jsp.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openxava.actions.*;
import org.openxava.jpa.XPersistence;
import org.openxava.model.*;
import org.openxava.util.*;
import org.openxava.validators.*;

public class TrashReportAction extends JasperReportBaseAction {

	private static Log log = LogFactory.getLog(TrashReportAction.class);
	
	private Trash trash;

	public Map getParameters() throws Exception {
        Messages errores = MapFacade.validate("Trash", getView().getValues());
        if (errores.contains()) throw new ValidationException(errores);
        Map parametros = new HashMap();
        //parametros.put("familia", getSubfamilia().getFamilia().getDescripcion());
        //parametros.put("subfamilia", getSubfamilia().getDescripcion());
        return parametros;
    }
	
	protected JRDataSource getDataSource() throws Exception {
		Vector collection = new Vector();
		collection.add( getTrash() );
        return new JRBeanCollectionDataSource( collection );
    }
	
	protected String getJRXML() {
        return "trash.jrxml"; // Para leer del classpath (carpeta "reports" o "informes")
        //return "/home/javi/Products.jrxml"; // Para leer del sistema de ficheros
    }

	private Trash getTrash() throws Exception {
		if (trash == null){
			String id = getView().getValueString("id");
			trash = (Trash) XPersistence.getManager().find(Trash.class, id);
		}
		return trash;
	}

	//public boolean inNewWindow() {
	//    return false;
	//}

	// public void execute() throws Exception {
	// 	ServletContext application = getRequest().getSession().getServletContext();
	// 	String reporte = application.getRealPath("/") + "WEB-INF/classes/" + "trash.jasper"
	// 	System.out.println(reporte);

	// 	JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, new HashMap());
	// 	JasperExportManager.exportReportToPdfFile(jasperPrint, "/Users/carper/MyReport.pdf");

	// 	//JasperExportManager.exportReportToPdfFile(getJRXML(), "/MyReport.pdf");
	// }

}
