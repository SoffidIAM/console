package com.soffid.iam.web.popup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.function.Consumer;

import javax.naming.InitialContext;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Div;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.Select;
import es.caib.zkib.component.Switch;
import es.caib.zkib.component.Wizard;


public class ImportCsvHandler extends Window implements AfterCompose {
	private Div step1;
	private Div step2;
	private File file;
	private CsvParser parser;
	private Select charsetSelect;
	private Textbox separator;
	private DataTable testTable;
	private Textbox quote;
	private Textbox escape;
	private Switch containsHeader;
	private Grid columnsGrid;
	private Div step3;
	private Vector<String> columnNames;
	private Vector<String> columnMappings;

	public ImportCsvHandler() {
		
	}

	public void start() {
		step1();
		doHighlighted();
	}
	
	@Override
	public void setPage(Page page) {
		super.setPage(page);
		Map args = Executions.getCurrent().getAttributes();
		if (args != null) {
			page.setVariable("columns", args.get("columns") );
			page.setVariable("title", args.get("title"));
			page.setVariable("invoker", args.get("invoker"));
			page.setVariable("consumer", args.get("consumer"));
			page.setVariable("visible", args.get("visible"));
			page.setVariable("newTransaction", args.get("newTransaction"));
		}
	}
	
		
	public void onClose(Event event) {
		event.stopPropagation();		                                 
		close (event);
	}
	
	public void close(Event event) {
		setVisible(false);
	}
	
	@Override
	public void afterCompose() {
		setTitle( (String) getPage().getVariable("title"));
		step1 = (Div) getFellow("step1");
		step2 = (Div) getFellow("step2");
		step3 = (Div) getFellow("step3");
		if (Boolean.TRUE.equals(getPage().getVariable("visible"))) {
			doHighlighted();
			step1();
		}
		charsetSelect = (Select) getFellow("charsetSelect");
		separator = (Textbox) getFellow("separator");
		testTable = (DataTable) getFellow("test-table");
		quote = (Textbox) getFellow("quote");
		escape = (Textbox) getFellow("escape");
		containsHeader = (Switch) getFellow("containsHeader");
		columnsGrid = (Grid) getFellow("columnsGrid");
	}
	
	
	public void cancelUpload () {
		if (file == null) 
			setVisible(false);
	}
	
	private void step1() {
		file = null;
		getWizard().start();
	}
	
	public void onUpload( UploadEvent event ) throws IOException {
		file = File.createTempFile("import", ".csv");
		file.deleteOnExit();
		FileOutputStream out = new FileOutputStream(file);
		Media media = event.getMedia();
		if (media == null)
			return;
		if (media.isBinary() && media.inMemory())
			out.write(media.getByteData());
		else if (media.isBinary()) {
			InputStream in = media.getStreamData();
			int read;
			while ((read = in.read()) > 0 )
				out.write(read);
			in.close();
		} 
		else if (media.inMemory()) {
			out.write( media.getStringData().getBytes());
		}
		else {
			Reader in = media.getReaderData();
			Writer w = new OutputStreamWriter(out);
			int read;
			while ((read = in.read()) > 0 ) 
				w.write(read);
			w.close();
			in.close();
		}
		
		parser = new CsvParser(file);
		parser.initialParse();
		
		quote.setValue(parser.getQuote());
		escape.setValue(parser.getEscapeChar());
		charsetSelect.setSelectedValue(parser.getCharset());
		separator.setValue(parser.getSeparator());
		containsHeader.setChecked( parser.isContainsHeaders());
		
		updateTable();
		
		getWizard().next();
	}

	public void reload() throws IOException {
		parser.setCharset( (String) charsetSelect.getSelectedValue());
		parser.setEscapeChar(escape.getValue());
		parser.setQuote(quote.getValue());
		String s = separator.getValue();
		if (s == null || s.isEmpty()) s = "\t";
		else if (s.equals("\\t")) s = "\t";
		else if (s.equals("\\n")) s = "\n";
		else if (s.equals("\\r")) s = "\r";
		parser.setSeparator(s);
		parser.setContainsHeaders(containsHeader.isChecked());
		updateTable();
	}
	
	private void updateTable() throws IOException {
		List<String[]> content = parser.readFirstLines();
		columnNames = new Vector<String>();
		columnMappings = new Vector<String>();
		
		if (content.isEmpty())
			testTable.setVisible(false);
		else {
			JSONArray array = new JSONArray();
			Iterator<String[]> it = content.iterator();
			String[] headers = it.next();
			for ( int i = 0; i < headers.length; i++ ) {
				String header = headers[i];
				JSONObject o = new JSONObject();
				o.put("name", header);
				o.put("sort", false);
				o.put("value", "_"+Integer.toString(i));
				array.put(o);
				columnNames.add( header );
				columnMappings.add(null);
			}
			testTable.setColumns(array.toString());
			
			JSONArray data = new JSONArray();
			while (it.hasNext()) {
				String[] row = it.next();
				JSONObject o = new JSONObject();
				for ( int i = 0; i < row.length; i++ ) {
					o.put("_"+Integer.toString(i), row[i]);
				}
				data.put(o);
			}
			testTable.setData(data);
		}
		
		guessMappings();
			
	}

	private void guessMappings() {
		String[][] columns = (String[][]) getPage().getVariable("columns");
		for (int i = 0; i < columnNames.size(); i++) {
			String columnName = columnNames.get(i);
			for (int j = 0; j < columns.length; j++) {
				if (columns[j][0].equalsIgnoreCase(columnName) || 
						columns[j][1].equalsIgnoreCase(columnName))
				{
					columnMappings.set(i, columns[j][0]);
					break;
				}
			}
		}
	}

	public void step2back( Event event ) {
		if (file != null) {
			file.delete();
			file = null;
		}

		getWizard().previous();
	}
	
	public void step2next( Event event ) {
		guessMappings();
		loadMappingsGrid();
		getWizard().next();
	}

	public void step3back( Event event ) {
		getWizard().previous();
	}
	
	public void step3next( Event event ) throws Exception {
		Consumer<CsvParser> consumer = (Consumer<CsvParser>) getPage().getVariable("consumer");
		parser.setMappings ( columnMappings );
		if (Boolean.TRUE.equals(getPage().getVariable("newTransaction"))) {
			UserTransaction tx =  (UserTransaction) new InitialContext().lookup("java:comp/UserTransaction");
			tx.begin();
			try {
				consumer.accept(parser);
			} catch (Exception e) {
				tx.setRollbackOnly();
				throw e;
			} finally {
				if (tx.getStatus() == Status.STATUS_MARKED_ROLLBACK)
					tx.rollback();
				else
					tx.commit();
			}			
		} else {
			consumer.accept(parser);
		}
		file.delete();
		file = null;
		setVisible(false);
	}

	private void loadMappingsGrid() {
		String[][] columns = (String[][]) getPage().getVariable("columns");
		// Generate select options
		JSONArray options = new JSONArray();
		JSONObject o = new JSONObject();
		o.put("label", Labels.getLabel("csvimport.do-not-load"));
		o.put("value", "");
		options.put(o);
		
		for ( String[] column : columns) {
			o = new JSONObject();
			o.put("label", column[1]);
			o.put("value", column[0]);
			options.put(o);
		}
		
		columnsGrid.getRows().getChildren().clear();
		for ( int i = 0; i < columnNames.size(); i++) {
			Row row = new Row();
			columnsGrid.getRows().appendChild(row);
			Label label = new Label(columnNames.get(i));
			row.appendChild(label);
			Select select = new Select();
			select.setOptions(options.toString());
			String selectedValue = columnMappings.get(i);
			select.setSelectedValue(selectedValue == null ? "": selectedValue);
			select.setAttribute("pos", new Integer(i));
			select.addEventHandler("onSelect", 
					new EventHandler(ZScript.parseContent("ref:window.changeSelectedMapping"), null));
			row.appendChild(select);
		}
	}
	
	public void changeSelectedMapping(Event event) {
		Select s = (Select) event.getTarget();
		String value = (String) s.getSelectedValue();
		Integer index = (Integer) s.getAttribute("pos");
		columnMappings.set(index.intValue(), value);
	}

	public static void startWizard (String title, String[][] columns,
			Component invoker,
			Consumer<CsvParser> consumer) throws IOException {
		startWizard(title, columns, invoker, consumer, true);
	}
	
	public static void startWizard (String title, String[][] columns,
			Component invoker,
			Consumer<CsvParser> consumer, boolean newTransaction) throws IOException {
		Page p = invoker.getDesktop().getPageIfAny("importCsv");
		if ( p == null) {
			Include i = new Include("/popup/importCsv.zul");
			i.setDynamicProperty("consumer", consumer);
			i.setDynamicProperty("title", title);
			i.setDynamicProperty("columns", columns);
			i.setDynamicProperty("visible", true);
			i.setDynamicProperty("newTransaction", newTransaction);
			i.setPage(invoker.getPage());
		} else {
			p.setVariable("title", title);
			p.setVariable("consumer", consumer);
			p.setVariable("columns", columns);
			p.setVariable("newTransaction", newTransaction);
			Events.sendEvent(new Event("onDisplay", p.getFellow("window")));
		}
	}

	Wizard getWizard() {
		return (Wizard) getFellow("wizard");
	}
}

