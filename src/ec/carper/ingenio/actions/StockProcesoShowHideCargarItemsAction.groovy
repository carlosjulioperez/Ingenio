package ec.carper.ingenio.actions

import org.openxava.actions.*

class StockProcesoShowHideCargarItemsAction extends OnChangePropertyBaseAction implements IShowActionAction, IHideActionAction{

    private boolean show

    void execute() throws Exception{
        def itemsCargados = (Boolean)getView().getValue("itemsCargados")
        println ">>>>> ${itemsCargados}"
        show = itemsCargados ?: false
    }

    String getActionToShow(){
        return !show ? "StockProceso.cargarItems": ""
    }

    String getActionToHide(){
        return show ? "StockProceso.cargarItems": ""
    }
}


