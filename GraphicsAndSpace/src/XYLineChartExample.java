import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;
import org.jfree.data.xy.XYDataItem;

import javax.swing.*;
import java.awt.*;

public class XYLineChartExample extends JFrame
{
    private XYDataset createDataset()
    {
        XYSeriesCollection dataset1 = new XYSeriesCollection();
        XYSeries series1 = new XYSeries("Object 1");

        for (double value = -10; value < 10; value += 0.01) {
            series1.add(value, Simple1.Function(value));
        }

        dataset1.addSeries(series1);
        return dataset1;
    }
    //
    private JPanel createChartPanel()
    {
        String chartTitle = "График";
        String xAxisLabel = "X";
        String yAxisLabel = "Y";

        XYDataset dataset = createDataset();

        JFreeChart chart = ChartFactory.createXYLineChart(chartTitle,
                xAxisLabel, yAxisLabel, dataset);

        return new ChartPanel(chart);
    }
    //
    public XYLineChartExample() {
        super("График выбранной функции");

        JPanel chartPanel = createChartPanel();
        add(chartPanel, BorderLayout.CENTER);

        setSize(640, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /*public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new XYLineChartExample().setVisible(true);
            }
        });
    }*/

}
