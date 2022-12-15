import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;
import javax.swing.*;
import java.awt.*;

public class XYLineChartExample extends JFrame
{
    private XYDataset createDataset(double bottomLimit, double upperLimit)
    {
        XYSeriesCollection dataset1 = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Object 1");
        for (double value = bottomLimit; value < upperLimit; value += 0.01) {
            series1.add(value, Simple1.Function(value));
        }
        dataset1.addSeries(series1);
        return dataset1;
    }

    private JPanel createChartPanel(double bottomLimit, double upperLimit)
    {
        String chartTitle = "График";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";

        XYDataset dataset = createDataset(bottomLimit, upperLimit);

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return new ChartPanel(chart);
    }

    public XYLineChartExample(double bottomLimit, double upperLimit) {
        super("График выбранной функции");

        JPanel chartPanel = createChartPanel(bottomLimit, upperLimit);
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
