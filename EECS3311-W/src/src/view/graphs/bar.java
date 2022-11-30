package src.view.graphs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import src.backend.concrete.linkedList;
import src.backend.interfaces.Iterator;
import src.view.interfaces.Graph;

public class bar implements Graph{

	@Override
	public void update(linkedList dataSets, JPanel west, String graphTitle) {
		int i = 0, pairs = 0; // every 2 data series we will make a new collection
		int count =0;
		DefaultCategoryDataset dataSeries = new DefaultCategoryDataset();
		CategoryPlot plot = new CategoryPlot();
		BarRenderer barrenderer = new BarRenderer();
		
		while (dataSets != null) {
			Iterator dataIterator = dataSets.getIterator();
			
			LinkedHashMap<?,?> dataSet = dataSets.getData();
			for (Entry<?, ?> temp : dataSet.entrySet()) {
				dataSeries.setValue((double)temp.getValue(), dataSets.getName(), temp.getKey().toString());
			}
			
			dataSets = (linkedList) dataIterator.next();
		//	plot.setRangeAxis(count, new NumberAxis("")); //insert axis name
			i++;
			count++;
			if(i > 1) {
				plot.setDataset(pairs, dataSeries);
				plot.setRenderer(pairs, barrenderer);
				plot.setRangeAxis(pairs, new NumberAxis("")); //insert axis name
				//List<Integer> axes = Arrays.asList(count-2, count-1);
				//plot.mapDatasetToRangeAxes(pairs, axes);
				//plot.mapDatasetToRangeAxis(pairs, count-1);
				//plot.mapDatasetToRangeAxis(pairs, count-2);
				//plot.setRangeAxis(pairs, new NumberAxis(""));
				pairs++;
				dataSeries = new DefaultCategoryDataset();
				barrenderer = new BarRenderer();
				i=0;
			} 
		}
		
		if(i == 1) {
			plot.setDataset(pairs, dataSeries);
			plot.setRenderer(pairs, barrenderer);
		//	plot.mapDatasetToRangeAxis(pairs, count-1);
			plot.setRangeAxis(pairs, new NumberAxis("")); 
		}
		
		CategoryAxis domainAxis = new CategoryAxis("");
		plot.setDomainAxis(domainAxis);
		
		for(int j=0; j<count; j++) {
			//System.out.println("j: " + j);
			plot.mapDatasetToRangeAxis(j, j);
		}
		
		JFreeChart barChart = new JFreeChart(graphTitle,
				new Font("Serif", java.awt.Font.BOLD, 18), plot, true);
		
		ChartPanel chartPanel = new ChartPanel(barChart);
		chartPanel.setPreferredSize(new Dimension(400, 300));
		chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		chartPanel.setBackground(Color.white);
		west.add(chartPanel);
	}
	
}