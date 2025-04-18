/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Chart;

/**
 *
 * @author admin
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.AreaRenderer;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
public class createChart {
//    public static void createPieChart(JPanel pnlName,int[] dataset){
//        
//        DefaultPieDataset pChart= new DefaultPieDataset();
//        
//        pChart.setValue("Đơn hàng thành công",dataset[0]);
//        pChart.setValue("Đơn hàng bị hủy",dataset[1]);
//
//        JFreeChart pchart =ChartFactory.createPieChart("", pChart,false,false,false);
//        pchart.setBackgroundPaint(Color.decode("#353746"));
//        PiePlot piePlot = (PiePlot) pchart.getPlot();
//        piePlot.setSectionPaint("Đơn hàng thành công", Color.decode("#00FF33"));
//        piePlot.setSectionPaint("Đơn hàng đã hủy", Color.decode("#FF3300"));
//        piePlot.setBackgroundPaint(Color.decode("#353746"));
//        piePlot.setOutlinePaint(Color.decode("#353746"));
//        
//        piePlot.setShadowPaint(null);
//        piePlot.setSimpleLabels(true);
//        StandardPieSectionLabelGenerator labelGen = new StandardPieSectionLabelGenerator( "{2}", new DecimalFormat("0"), new DecimalFormat("0%"));
//        piePlot.setLabelGenerator(labelGen);
//        ChartPanel chartPie = new  ChartPanel(pchart);
//        
//        pnlName.add(chartPie);
//        pnlName.updateUI();
//    }
public static void createLineChart(JPanel pnlName,int[] dataset){
       DefaultCategoryDataset data = new DefaultCategoryDataset();
       int i = 0;
       while(i<dataset.length){
           data.setValue(dataset[i++],"","T"+(i));
       }
       JFreeChart lineChart=ChartFactory.createLineChart("","","",data,PlotOrientation.VERTICAL,false,true,false);
       
       ChartPanel lineChartPanel = new  ChartPanel(lineChart);
       
       CategoryPlot plotLine= lineChart.getCategoryPlot();
       plotLine.setBackgroundPaint(new Color(255,255,255));
       plotLine.setOutlinePaint(null);
       plotLine.setRangeGridlinePaint(Color.decode("#0092f2"));
       plotLine.getRangeAxis().setTickMarkPaint(Color.decode("#0092f2"));
       plotLine.getDomainAxis().setTickMarkPaint(Color.decode("#0092f2"));
       plotLine.getRangeAxis().setAxisLinePaint(Color.decode("#0092f2"));
       plotLine.getDomainAxis().setAxisLinePaint(Color.decode("#0092f2"));
       Stroke solid = new BasicStroke((float) 0.5);
       plotLine.setRangeGridlineStroke(solid);
       
       LineAndShapeRenderer rendererLineChart = (LineAndShapeRenderer) plotLine.getRenderer();
       rendererLineChart.setSeriesPaint(0, Color.decode("#0092f2"));
       rendererLineChart.setSeriesStroke(0, new BasicStroke(3.0f)); 
       
       
       pnlName.add(lineChartPanel);
       pnlName.updateUI();
    }

     public static void createLineChart2(JPanel pnlName,int[] dataset1, int[] dataset2,String[] year){
     DefaultCategoryDataset data = new DefaultCategoryDataset();
       int i = 0;
       int j = 0;

       while(i<dataset1.length && j<dataset2.length)
       {
           data.setValue(dataset1[i],"row1",year[i++]);
           data.setValue(dataset2[j],"row2",year[j++]);
       }
       JFreeChart lineChart=ChartFactory.createLineChart("","","",data,PlotOrientation.VERTICAL,false,true,false);
       
       ChartPanel lineChartPanel = new  ChartPanel(lineChart);
       
       
       CategoryPlot plotLine= lineChart.getCategoryPlot();
       plotLine.setBackgroundPaint(new Color(255,255,255));
       plotLine.setOutlinePaint(null);
       plotLine.setRangeGridlinePaint(Color.decode("#0092f2"));
       plotLine.getRangeAxis().setTickMarkPaint(Color.decode("#0092f2"));
       plotLine.getDomainAxis().setTickMarkPaint(Color.decode("#0092f2"));
       plotLine.getRangeAxis().setAxisLinePaint(Color.decode("#0092f2"));
       plotLine.getDomainAxis().setAxisLinePaint(Color.decode("#0092f2"));
       Stroke solid = new BasicStroke((float) 0.5);
       plotLine.setRangeGridlineStroke(solid);
       
       LineAndShapeRenderer rendererLineChart = (LineAndShapeRenderer) plotLine.getRenderer();
       rendererLineChart.setSeriesPaint(0, Color.decode("#C0C0C0"));
       rendererLineChart.setSeriesPaint(1, Color.decode("#0092f2"));
       rendererLineChart.setSeriesStroke(0, new BasicStroke(3.0f));
       rendererLineChart.setSeriesStroke(1, new BasicStroke(3.0f));
       
       pnlName.add(lineChartPanel);
       pnlName.updateUI();
    }    
public static void createBarChartVertical(JPanel pnlName,int[] dataset){
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        int i =0;
        while(i<dataset.length){
            data.setValue(dataset[i++],"","T"+(i));
        }
        JFreeChart bChart = ChartFactory.createBarChart("","","",data,PlotOrientation.VERTICAL,false,false,false);
        bChart.setBackgroundPaint(Color.decode("#353746"));
        bChart.setBorderPaint (Color.decode("#cccccc"));
        
        ChartPanel bChartPanel = new ChartPanel(bChart);
        CategoryPlot plotBar=bChart.getCategoryPlot();
        BarRenderer rendererBar= (BarRenderer) plotBar.getRenderer();
        
        plotBar.setBackgroundPaint(Color.decode("#353746"));
        plotBar.setRangeGridlinePaint(Color.decode("#D2302C"));
        plotBar.getRangeAxis().setTickMarkPaint(Color.decode("#D2302C"));
        plotBar.getDomainAxis().setTickMarkPaint(Color.decode("#D2302C"));
        plotBar.getRangeAxis().setAxisLinePaint(Color.decode("#D2302C"));
        plotBar.getDomainAxis().setAxisLinePaint(Color.decode("#D2302C"));
        Stroke solid = new BasicStroke((float) 0.5);
        plotBar.setRangeGridlineStroke(solid);
        plotBar.setDomainGridlinePaint (Color.decode("#D2302C"));
        plotBar.setOutlinePaint(Color.decode("#353746"));
        plotBar.getDomainAxis().setTickLabelPaint(Color.white);//change color of row
        plotBar.getRangeAxis().setTickLabelPaint(Color.white);
        
        rendererBar.setSeriesPaint(0,Color.decode("#d2302c"));
        rendererBar.setBarPainter(new StandardBarPainter());
        rendererBar.setMaximumBarWidth(0.005);
        
        
        pnlName.add(bChartPanel);
        pnlName.updateUI();
         
    }    
public static void createBarChartHorizontal(JPanel pnlName,int[] dataset,String[] year){
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        int i =0;
        while(i<dataset.length){
            data.setValue(dataset[i],"",year[i++]);
        }
        JFreeChart bChart = ChartFactory.createBarChart("","","",data,PlotOrientation.HORIZONTAL,false,false,false);
        bChart.setBackgroundPaint(Color.decode("#353746"));
        bChart.setBorderPaint (Color.decode("#cccccc"));
        
        ChartPanel bChartPanel = new ChartPanel(bChart);
        CategoryPlot plotBar=bChart.getCategoryPlot();
        BarRenderer rendererBar= (BarRenderer) plotBar.getRenderer();
        
        plotBar.setBackgroundPaint(Color.decode("#353746"));
        plotBar.setRangeGridlinePaint(Color.decode("#353746"));
        plotBar.getRangeAxis().setTickMarkPaint(Color.decode("#353746"));
        plotBar.getDomainAxis().setTickMarkPaint(Color.decode("#D2302C"));
        plotBar.getRangeAxis().setAxisLinePaint(Color.decode("#353746"));
        plotBar.getDomainAxis().setAxisLinePaint(Color.decode("#D2302C"));
        Stroke solid = new BasicStroke((float) 0.5);
        plotBar.setRangeGridlineStroke(solid);
        plotBar.setDomainGridlinePaint (Color.decode("#D2302C"));
        plotBar.setOutlinePaint(Color.decode("#353746"));
        plotBar.getDomainAxis().setTickLabelPaint(Color.white);//change color of row
        plotBar.getRangeAxis().setTickLabelPaint(Color.white);
        
        rendererBar.setSeriesPaint(0,Color.decode("#d2302c"));
        rendererBar.setBarPainter(new StandardBarPainter());
        rendererBar.setMaximumBarWidth(0.05);
        
        
        pnlName.add(bChartPanel);
        pnlName.updateUI();
         
    } 
    public static void createBarChart(JPanel pnlName,int[] dataset1,int[] dataset2, String[] year){
        DefaultCategoryDataset data = new DefaultCategoryDataset();
        
        int i=0;
        int j=0;
        while(i<12 && j<12){
            data.addValue(dataset1[i],"col1",year[i++]);
            data.addValue(dataset2[j],"col2",year[j++]);
        }
        
        JFreeChart bChart =ChartFactory.createBarChart("", "", "", data,PlotOrientation.VERTICAL,false,true,false);
        bChart.setBackgroundPaint(Color.decode("#353746"));
        bChart.setBorderPaint (Color.decode("#cccccc"));
        
        
        ChartPanel bChartPanel = new  ChartPanel(bChart);
        
        
        bChartPanel.setBackground(Color.decode("#353746"));
       
        
        
        
        CategoryPlot plotBarChart= bChart.getCategoryPlot();
        plotBarChart.setRangeGridlinePaint(Color.decode("#D2302C"));
        plotBarChart.getRangeAxis().setTickMarkPaint(Color.decode("#D2302C"));
        plotBarChart.getDomainAxis().setTickMarkPaint(Color.decode("#D2302C"));
        plotBarChart.getRangeAxis().setAxisLinePaint(Color.decode("#D2302C"));
        plotBarChart.getDomainAxis().setAxisLinePaint(Color.decode("#D2302C"));
        Stroke solid = new BasicStroke((float) 0.5);
        plotBarChart.setRangeGridlineStroke(solid);
        plotBarChart.setBackgroundPaint(Color.decode("#353746"));
        plotBarChart.setDomainGridlinePaint (Color.decode("#D2302C"));
        plotBarChart.setOutlinePaint(Color.decode("#353746"));
        plotBarChart.getDomainAxis().setTickLabelPaint(Color.white);//change color of row
        plotBarChart.getRangeAxis().setTickLabelPaint(Color.white);

        
        
        
       
         
        BarRenderer rendererBarChart = (BarRenderer) plotBarChart.getRenderer();
        rendererBarChart.setSeriesPaint(1,Color.decode("#D2302C"));
        rendererBarChart.setSeriesPaint(0,Color.decode("#c0c0c0"));
        rendererBarChart.setBarPainter(new StandardBarPainter());
        rendererBarChart.setItemMargin(-0.5);
        rendererBarChart.setMaximumBarWidth(0.005);
        
        
        
        pnlName.add(bChartPanel);
        pnlName.updateUI();
        
    }
    public static void createAreaChart(JPanel pnlName,int[] dataset){
       DefaultCategoryDataset data = new DefaultCategoryDataset();
       int i = 0;
       while(i<12){
           data.setValue(dataset[i++],"","T"+(i));
       }
       JFreeChart aChart=ChartFactory.createAreaChart("","","",data,PlotOrientation.VERTICAL,false,true,false);
       
       ChartPanel aChartPanel = new  ChartPanel(aChart);
       CategoryPlot plotArea= aChart.getCategoryPlot();
       AreaRenderer rendererAChart = (AreaRenderer) plotArea.getRenderer();
        
       plotArea.setBackgroundPaint(new Color(255,255,255));
       rendererAChart.setSeriesPaint(0, new Color(234, 249, 252));
       
       pnlName.add(aChartPanel);
       pnlName.updateUI();
    }
}
