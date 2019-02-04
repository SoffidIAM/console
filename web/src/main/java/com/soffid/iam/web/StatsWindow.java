package com.soffid.iam.web;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetGroup;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;

import es.caib.seycon.ng.exception.InternalErrorException;


public class StatsWindow extends Window implements AfterCompose {
	private Listbox submetric;
	private Map<String, int[]> stats;
	private Image graph;
	int step;
	private String server;
	private Map<String, int[]> statsError;
	
	public void open(String server) throws InternalErrorException, NamingException, CreateException, IOException
	{
		this.server = server;
		step = 10;
		stats = EJBLocator.getSyncServerService().getStats(server, "tasks-success", 1200, step);
		statsError = EJBLocator.getSyncServerService().getStats(server, "tasks-error", 1200, step);
		Object sel = submetric.getSelectedItem() == null ? null : submetric.getSelectedItem().getValue();
		submetric.getItems().clear();
		submetric.appendChild(new Listitem("Total", null));
		for (String s: stats.keySet())
		{
			Listitem listitem = new Listitem(s, s);
			submetric.appendChild(listitem);			
			if (s.equals(sel))
				submetric.setSelectedItem(listitem);
		}
		for (String s: statsError.keySet())
		{
			if ( ! stats.containsKey(s))
			{
				Listitem listitem = new Listitem(s, s);
				submetric.appendChild(listitem);			
				if (s.equals(sel))
					submetric.setSelectedItem(listitem);
			}
		}
		generateGraph();
		doHighlighted();
	}

	public void refresh () throws InternalErrorException, NamingException, CreateException, IOException {
		open (this.server);
	}

	public void generateGraph() throws IOException {
		Listitem selected = submetric.getSelectedItem();
		int data[] = null;
		int errordata[] = null;
		String label = null;
		
		if (selected != null && selected.getValue() != null)
		{
			data = stats.get(selected.getValue());
			errordata = statsError.get(selected.getValue());
			label = (String) selected.getValue();
		}
		else if ( ! stats.values().isEmpty() || !statsError.values().isEmpty())
		{
			int[] data0 = stats == null || stats.isEmpty() ? null: stats.values().iterator().next();
			int[] errordata0 = statsError == null || statsError.isEmpty()? null : statsError.values().iterator().next();
			
			int values = data0 == null && errordata0 == null ? 0: 
				data0 == null ? errordata0.length : 
					data0.length;
			data = new int[values];
			errordata = new int[values];
			if (stats != null)
			{
				for ( int[] data1: stats.values())
				{
					for (int i = 0; i < data1.length && i < data.length; i++)
						data [i] +=  data1[i];
				}
			}
			if (statsError != null)
			{
				for ( int[] data1: statsError.values())
				{
					for (int i = 0; i < data1.length && i < errordata0.length; i++)
						errordata [i] +=  data1[i];
				}
			}
			label = "Total";
		}
		if (data == null && errordata == null)
			graph.setVisible(false);
		else
		{
			DatasetGroup group = new DatasetGroup(label);
			DefaultXYDataset dataset = new DefaultXYDataset();
			if (errordata == null)
			{
				double[][] serieData2 = new double[][] { new double[data.length], new double[data.length]};
				for (int i = 0; i < data.length; i++)
				{
					serieData2[0][i] =  - (data.length - i) * step / 60.0;
					serieData2[1][i] = 0;
				}
				dataset.addSeries("Errors", serieData2);
			}
			else
			{
				double[][] serieData2 = new double[][] { new double[errordata.length], new double[errordata.length]};
				for (int i = 0; i < errordata.length; i++)
				{
					serieData2[0][i] =  - (errordata.length - i) * step / 60.0;
					serieData2[1][i] = errordata[i] * 60.0 / step;
				}
				dataset.addSeries("Errors", serieData2);
			}
			if (data == null)
			{
				double[][] serieData = new double[][] { new double[errordata.length], new double[errordata.length]};
				for (int i = 0; i < errordata.length; i++)
				{
					serieData[0][i] =  - (errordata.length - i) * step / 60.0;
					serieData[1][i] = 0;
				}
				dataset.addSeries("Tasks", serieData);
			}
			else
			{
				double[][] serieData = new double[][] { new double[data.length], new double[data.length]};
				for (int i = 0; i < data.length; i++)
				{
					serieData[0][i] =  - (data.length - i) * step / 60.0;
					serieData[1][i] = data[i] * 60.0 / step;
				}
				dataset.addSeries("Tasks", serieData);
			}

			dataset.setGroup(group );
			
			StandardChartTheme theme = (StandardChartTheme) StandardChartTheme.createJFreeTheme();
			theme.setPlotBackgroundPaint(Color.WHITE);
			theme.setRangeGridlinePaint(Color.BLACK);
			theme.setAxisOffset(new RectangleInsets(0, 0, 0, 0)); // disables the gap between graph and axes
			JFreeChart chart = ChartFactory.createXYAreaChart("Synchronization engine performance", "Minutes", "Tasks / minute", dataset);
			theme.apply(chart);
			XYPlot plot = (XYPlot) chart.getPlot();
			plot.setForegroundAlpha(0.5f);
			XYItemRenderer xyir1 = plot.getRenderer();
			xyir1.setSeriesPaint(1, new Color (0, 255, 64));
			xyir1.setSeriesPaint(0, Color.red);
			ValueAxis range = plot.getDomainAxis();
			range.setMinorTickCount(60);  
			
			BufferedImage bi = chart.createBufferedImage(800, 400, BufferedImage.TRANSLUCENT , null);
			byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);
			AImage image = new AImage("Pie Chart", bytes);
			graph.setVisible(true);
			graph.setContent(image);
		}
	}

	@Override
	public void afterCompose() {
		submetric = (Listbox) getFellow("submetric");
		graph = (Image) getFellow("graph");
	}

}
