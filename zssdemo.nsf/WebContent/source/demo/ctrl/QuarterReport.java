package demo.ctrl;

import java.io.ByteArrayOutputStream;

import org.zkoss.xpage.core.bean.ComponentBinding;
import org.zkoss.xpage.core.component.Action;
import org.zkoss.xpage.core.component.ZulBridgeBase;
import org.zkoss.xpage.zss.component.SpreadsheetBridge;
import org.zkoss.zkmax.zul.Filedownload;
import org.zkoss.zss.model.Book;
import org.zkoss.zss.model.Exporter;
import org.zkoss.zss.model.Exporters;
import org.zkoss.zss.ui.Spreadsheet;

import demo.data.QuarterBean;
import demo.data.QuarterBeanProvider;

public class QuarterReport {

	
	public void doDownload(){
		ComponentBinding.getBridge("quartersheet").execute(new Action(){
			public void doAction(ZulBridgeBase bridge) {
				//get the spreadsheet
				Spreadsheet ss = ((SpreadsheetBridge)bridge).getSpreadsheet();
				Exporter c = Exporters.getExporter("pdf");//need to setup pdf jars
			    ByteArrayOutputStream baos = new ByteArrayOutputStream();
			    Book book = ss.getBook();
			    c.export(book.getWorksheetAt(0), baos);
			    Filedownload.save(baos.toByteArray(), "application/pdf","demo-report.pdf");
			}});
		
	}
	
	public void doQuarter1(){
		doQuarter(1);
	}
	public void doQuarter2(){
		doQuarter(2);
	}
	public void doQuarter3(){
		doQuarter(3);
	}
	public void doQuarter4(){
		doQuarter(4);
	}
	
	public void doQuarter(final int quoter){
		//get the binding bridge back, and execute the action
		ComponentBinding.getBridge("quartersheet").execute(new Action(){
			public void doAction(ZulBridgeBase bridge) {
				//get the spreadsheet
				Spreadsheet ss = ((SpreadsheetBridge)bridge).getSpreadsheet();
				QuarterBeanProvider.queryQuarterBean(quoter, (QuarterBean)ss.getAttribute("quarterBean"));
//				ss.setAttribute("quarterBean", bean,false);
				ss.getBook().notifyChange(new String[]{"quarterBean"});
			}});
		
	}
	
	public void doStyle1(){
		//get the binding bridge back, and execute the action
		ComponentBinding.getBridge("quartersheet").execute(new Action(){
			public void doAction(ZulBridgeBase bridge) {
				//get the spreadsheet
				Spreadsheet ss = ((SpreadsheetBridge)bridge).getSpreadsheet();
				ss.setSrc("/quartersheet1.xls");
				ss.setWidth("600px");
			}});
	}
	
	public void doStyle2(){
		//get the binding bridge back, and execute the action
		ComponentBinding.getBridge("quartersheet").execute(new Action(){
			public void doAction(ZulBridgeBase bridge) {
				//get the spreadsheet
				Spreadsheet ss = ((SpreadsheetBridge)bridge).getSpreadsheet();
				ss.setSrc("/quartersheet2.xls");
				ss.setWidth("800px");
			}});
	}
}
