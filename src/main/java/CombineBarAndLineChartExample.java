import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * @author imssbora
 *
 */
public class CombineBarAndLineChartExample extends JFrame {

    private static final long serialVersionUID = 1L;

    public CombineBarAndLineChartExample(String title) {
        super(title);

        // Create Category plot
        CategoryPlot plot = new CategoryPlot();

        // Add the first dataset and render as bar
//        CategoryItemRenderer lineRenderer = new LineAndShapeRenderer();
//        lineRenderer.setDefaultItemLabelsVisible(true);
//        plot.setDataset(0, createLineDataset(0,1, 10));
//        plot.setRenderer(0, lineRenderer);

        // Add the second dataset and render as lines
        CategoryItemRenderer baRenderer = new BarRenderer();


        baRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        baRenderer.setDefaultItemLabelsVisible(true);
        ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,
                TextAnchor.TOP_CENTER);
        baRenderer.setDefaultPositiveItemLabelPosition(position);

        plot.setDataset(1, createBarDataset(0,1, 10));
        plot.setRenderer(1, baRenderer);

        // Set Axis
        plot.setDomainAxis(new CategoryAxis("Argument"));
        plot.setRangeAxis(new NumberAxis("Value"));

        JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("wykres funkcji");

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private DefaultCategoryDataset createLineDataset(double beginValue, double endValue, int numberOfSteps) {
        double step = (endValue - beginValue) / numberOfSteps;
        // First Series
        String series1 = "Vistor";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(HistogramEx.squareFunction(beginValue), series1, Double.toString(beginValue));

        for (int i = 1; i < numberOfSteps; i++) {
            var temp = beginValue+i*step;
            dataset.addValue(HistogramEx.squareFunction(temp), series1, Double.toString(temp));
        }
        dataset.addValue(HistogramEx.squareFunction(endValue), series1, Double.toString(endValue));
        return dataset;
    }

    private DefaultCategoryDataset createBarDataset(double beginValue, double endValue, int numberOfSteps) {

        double step = (endValue - beginValue) / numberOfSteps;


        String series1 = "Vistor2";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (int i = 0; i < numberOfSteps; i++) {
            var temp = beginValue+i*step;
            var temp2 = beginValue+(i+1)*step;
            dataset.addValue(HistogramEx.squareFunction(temp), series1, Double.toString((temp+temp2)/2));
        }


        return dataset;
    }


    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            CombineBarAndLineChartExample example = new CombineBarAndLineChartExample(
//                    "Line Chart and Bar chart Example");
//            example.setSize(800, 400);
//            example.setLocationRelativeTo(null);
//            example.setVisible(true);
//        });

//        EventQueue.invokeLater(() -> {
//
//            var ex = new CombineBarAndLineChartExample("Line Chart and Bar chart Example");
//            ex.setSize(800, 400);
//            ex.setLocationRelativeTo(null);
//            ex.setVisible(true);
//        });



    }
}