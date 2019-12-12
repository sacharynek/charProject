import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

public class LineChartEx extends JFrame {

    public LineChartEx() {

        initUI();
    }

    private void initUI() {

        XYDataset dataset = createDataset(0,1, 10);
        JFreeChart chart = createChart(dataset);


        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        chartPanel.setBackground(Color.white);
        add(chartPanel);

        pack();
        setTitle("Line chart");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private XYDataset createDataset(double beginValue, double endValue, int numberOfSteps) {

        var series = new XYSeries("funkcja");
        double step = (endValue - beginValue) / numberOfSteps;
        series.add(beginValue, HistogramEx.squareFunction(beginValue));
        for (int i = 1; i < numberOfSteps; i++) {
            var temp = beginValue+i*step;
           series.add(temp, HistogramEx.squareFunction(temp));
        }

        series.add(endValue, HistogramEx.squareFunction(endValue));

        var dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Average salary per age",
                "Age",
                "Salary (€)",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        XYPlot plot = chart.getXYPlot();

        var renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));


        plot.setRenderer(renderer);
        plot.setBackgroundPaint(Color.white);

        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.BLACK);

        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.BLACK);

        chart.getLegend().setFrame(BlockBorder.NONE);

        chart.setTitle(new TextTitle("Average Salary per Age",
                        new Font("Serif", java.awt.Font.BOLD, 18)
                )
        );

        return chart;
    }

//    public static void main(String[] args) {
//
//        EventQueue.invokeLater(() -> {
//
//            var ex = new LineChartEx();
//            ex.setVisible(true);
//        });
//    }
}