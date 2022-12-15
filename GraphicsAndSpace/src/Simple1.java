import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simple1 extends JFrame
{
    public JPanel panel1;
    public static double Function(double x)//задание функции
    {
        return Math.sin(x*x);
    }
    public double Integral ( double start, double finish, double precision)//подсчёт интеграла
    {
        double res = 0;
        double currentValue = start;
        while (currentValue < finish) {
            double end = currentValue + precision;
            double mid = (currentValue + end) / 2;
            res += precision * Function(mid);
            currentValue = end;
        }
        return res;
    }

    private JPanel radioPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private JButton button = new JButton("Создать график");
    //private TextField input = new TextField("");
    //private JLabel label = new JLabel("Input:");
    /*private JRadioButton radio1 = new JRadioButton("Функция y=x ");
    private JRadioButton radio2 = new JRadioButton("Функция y=sin(x): ");
    private JRadioButton radio3 = new JRadioButton("Функция y=x^2: ");
    private JRadioButton radio4 = new JRadioButton("Функция y=cos(x): ");
    private JRadioButton radio5 = new JRadioButton("Функция y=lg(x): ");
    private JRadioButton radio6 = new JRadioButton("Функция y=e^x: ");
    private JRadioButton radio7 = new JRadioButton("Функция y=ln(x): ");
     */
    private JRadioButton radio1 = new JRadioButton("<html>" + "Функция y=x " + "</html>");
    private JRadioButton radio2 = new JRadioButton("<html>" + "Функция y=sin(x): " + "</html>");
    private JRadioButton radio3 = new JRadioButton("<html>" + "Функция y=x^2: " + "</html>");
    private JRadioButton radio4 = new JRadioButton("<html>" + "Функция y=cos(x): " + "</html>");
    private JRadioButton radio5 = new JRadioButton("<html>" + "Функция y=lg(x): " + "</html>");
    private JRadioButton radio6 = new JRadioButton("<html>" + "Функция y=e^x: " + "</html>");
    private JRadioButton radio7 = new JRadioButton("<html>" + "Функция y=ln(x): " + "</html>");

    private JCheckBox check = new JCheckBox("Высчитать интеграл: ");

    public Simple1()
    {
       /* super("Простые примеры");
        this.setBounds(150, 150, 660, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(4 , 4, 1, 1));
        container.setLocation(140,140);
        //container.add(label);
        //container.add(input);
        ButtonGroup group = new ButtonGroup();

        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        group.add(radio4);
        group.add(radio5);
        group.add(radio6);
        group.add(radio7);

        container.add(radio1);
        radio1.setSelected(true);
        container.add(radio2);
        container.add(radio3);
        container.add(radio4);
        container.add(radio5);
        container.add(radio6);
        container.add(radio7);

        radio1.setBounds(140,140, 40,10);
        radio1.setBounds(190,140, 40,10);
        radio1.setBounds(240,140, 40,10);
        radio1.setBounds(290,140, 40,10);
        radio1.setBounds(140,170, 40,10);
        radio1.setBounds(190,140, 40,10);
        radio1.setBounds(240,140, 40,10);
        radio1.setBounds(290,140, 40,10);

        button.setSize(140,50);
        button.addActionListener(new ButtonEventListener());
        container.add(button);

        container.add(check);
        */
        super("Простые примеры");
        this.setBounds(150, 150, 660, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Container container = this.getContentPane();
        Container container = panel1;
        container.setLayout(new BorderLayout());

        //container.add(label);
        //container.add(input);

        ButtonGroup group = new ButtonGroup();
        button.setBounds(260, 450,160, 60 );

        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        group.add(radio4);
        group.add(radio5);
        group.add(radio6);
        group.add(radio7);

        radioPanel.setLayout(new GridLayout(3,3,1,1));
        buttonsPanel.setLayout(new GridLayout(1,2,1,1));

        container.add(radioPanel, BorderLayout.CENTER);
        container.add(buttonsPanel, BorderLayout.SOUTH);

        radioPanel.add(radio1);
        radio1.setSelected(true);
        radioPanel.add(radio2);
        radioPanel.add(radio3);
        radioPanel.add(radio4);
        radioPanel.add(radio5);
        radioPanel.add(radio6);
        radioPanel.add(radio7);

        buttonsPanel.add(button);
        buttonsPanel.add(check);

        radioPanel.setVisible(true);
        buttonsPanel.setVisible(true);
        button.addActionListener(new ButtonEventListener());
    }


    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new XYLineChartExample().setVisible(true);
                }
            });

            String message = "";
            //message += "Text is :" + input.getText() + "\n";
            message += (radio1.isSelected() ? "График функции синуса" : "График второй функции") + " был выполнен!";

            Simple1 f = new Simple1();
            /*f.setContentPane(f.panel1);
            f.setTitle("bruh");
            f.setSize(500, 500);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/
            double integralStart = 0;
            double integralEnd = 5;
            double integralPrecision = 0.001;
            double integralRes = f.Integral(integralStart, integralEnd, integralPrecision);
           // JOptionPane.showMessageDialog(f, "Integral from " + String.valueOf(integralStart) + " to " + String.valueOf(integralEnd) + " is " + String.valueOf(integralRes) + " (precision: " + String.valueOf(integralPrecision) + ")");

            message += "\nИнтеграл выбранной функции " +((check.isSelected()) ? String.valueOf(integralRes) : "не был высчитан по вашему решению");
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
