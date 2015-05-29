

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;


public class ChartCreator extends JFrame {

  private static final long serialVersionUID = 1L;

  public ChartCreator(String applicationTitle, String chartTitle, ArrayList data) {
        super(applicationTitle);
        // This will create the dataset 
        PieDataset dataset = createDataset(data);
        // based on the dataset we create the chart
        JFreeChart chart = createChart(dataset, chartTitle);
        // we put the chart into a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        // add it to our application
        setContentPane(chartPanel);

    }
    
    /**
     * Creates a sample dataset 
     */

    private  PieDataset createDataset(ArrayList hp) {
    	DefaultPieDataset result = new DefaultPieDataset();
    	for(int i=0;i<hp.size();i++){
    		if(i==0)
    			result.setValue("Negative Feedback", Integer.parseInt(hp.get(i).toString()));
    		else if(i==1)
    			result.setValue("Neutral Feedback", Integer.parseInt(hp.get(i).toString()));
    		else
    			result.setValue("Positive Feedback", Integer.parseInt(hp.get(i).toString()));
    	}     
        return result;
        
    }
    
    /**
     * Creates a chart
     */

    private JFreeChart createChart(PieDataset dataset, String title) {
        
        JFreeChart chart = ChartFactory.createPieChart3D(title,          // chart title
            dataset,                // data
            true,                   // include legend
            true,
            false);

        PiePlot3D plot = (PiePlot3D) chart.getPlot();
        plot.setStartAngle(290);
        plot.setDirection(Rotation.CLOCKWISE);
        plot.setForegroundAlpha(0.5f);
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                "{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
            plot.setLabelGenerator(gen);
        return chart;
        
    }
    
    public static void main(String[] args) {
    	ArrayList lst = new ArrayList();
    	lst.add(20);
    	lst.add(30);
    	lst.add(50);
    	ChartCreator demo = new ChartCreator("Feedback Analyser", "Customer Feedback",lst);
        demo.pack();
        demo.setVisible(true);
    }

} 

