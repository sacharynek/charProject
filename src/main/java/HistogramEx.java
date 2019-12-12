import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;
import java.awt.EventQueue;

import java.io.File;
import java.io.IOException;

public class HistogramEx {
    public static double squareFunction(double x) {
        // Declaring the function f(x) = 1/(1+x*x)
//        return 1 / (1 + x * x);
        return x*x*x;
    }

    static double getStep(double beginValue, double endValue, int numberOfSteps){
        return (endValue - beginValue) / numberOfSteps;
    }

    static double[] prepareTableOfArguments(double beginValue, double endValue, int numberOfSteps) {
        double[] output_tab = new double[numberOfSteps + 1];
        double step = (endValue - beginValue) / numberOfSteps;

        output_tab[0] = squareFunction(beginValue);

        for (int i = 1; i < numberOfSteps; i++) {
            output_tab[i] = squareFunction(beginValue + i * step);
        }

        output_tab[numberOfSteps] = squareFunction(endValue);

        return output_tab;
    }

    static double leftPointMethod(double[] tab, double step) {
        double output = 0;

        for (int i = 0; i < tab.length - 1;i++) {
            output += tab[i] * step;
        }

        return output;
    }

    static double rightPointMethod(double[] tab, double step) {
        double output = 0;
        for (int i = 1; i < tab.length;i++) {
            output += tab[i] * step;
        }
        return output;
    }

    static double midPointMethod(double[] tab, double step) {
        double output = 0;
        for (int i = 0; i < tab.length-1;i++) {
            double mid = (tab[i]+tab[i+1])/2;
            output += mid * step;
        }
        return output;
    }

    // Function to evalute the value of integral
    static double trapezoidal(double beginValue, double endValue, int numberOfSteps) {

        // krok obliczen
        double step = (endValue - beginValue) / numberOfSteps;
        double s = 0;

        s += squareFunction(beginValue);
        s += squareFunction(endValue);

        // dodawanie środkowych wartości
        for (int i = 1; i < numberOfSteps; i++) {
            s += 2 * squareFunction(beginValue + i * step);

        }
        // h/2 indicates (b-a)/2n. Multiplying h/2
        // with s.
        return (step / 2) * s;
    }

    public double getRightDerivateive(double argX, double delta){
        return (squareFunction(argX+delta)-squareFunction(argX))/delta;
    }

    public double getLeftDerivative(double argX, double delta){
        return (squareFunction(argX)-squareFunction(argX-delta))/delta;
    }

    public static void main(String[] args) throws IOException {

        double x0 = 0;
        double xn = 1;
        int n = 10;
        double[] table = prepareTableOfArguments(x0, xn, n);
        double step = getStep(x0, xn, n);

        System.out.format("Value of leftpoint integral is %6.4f\n", leftPointMethod(table,step));
        System.out.format("Value of rightpoint integral is %6.4f\n", rightPointMethod(table, step));
        System.out.format("Value of average integral is %6.4f\n", (rightPointMethod(table, step)+leftPointMethod(table,step))/2);
        System.out.format("Value of midpoint integral is %6.4f\n", midPointMethod(table, step));

        System.out.format("Value of trapeze integral is %6.4f\n", trapezoidal(x0, xn, n));





        var dataset = new HistogramDataset();
        dataset.addSeries("wartość", table, n);//n - number of rectangles drawn on the chart

        JFreeChart histogram = ChartFactory.createHistogram("Normal distribution",
                "y values", "x values", dataset);

        ChartUtils.saveChartAsPNG(new File("histogram.png"), histogram, 450, 400);

        EventQueue.invokeLater(() -> {

            var ex = new LineChartEx();
            ex.setVisible(true);
        });

        EventQueue.invokeLater(() -> {

            var ex = new CombineBarAndLineChartExample("Line Chart and Bar chart Example");
            ex.setSize(1600, 800);
            ex.setLocationRelativeTo(null);
            ex.setVisible(true);
        });
    }
}