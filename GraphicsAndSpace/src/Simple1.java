import javax.swing.*;
import org.jfree.chart.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.*;

import java.security.PrivateKey;
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
    private JPanel inputPanel = new JPanel();
    private JPanel radioPanel = new JPanel();
    private JPanel buttonsPanel = new JPanel();

    private JButton button = new JButton("Создать график");
    private TextField input1 = new TextField("");
    private JLabel label1 = new JLabel("Верхнее ограничение:");
    private TextField input2 = new TextField("");
    private JLabel label2 = new JLabel("Нижнее ограничение:");

    private JRadioButton radio1 = new JRadioButton("<html>" + "Функция y=x " + "</html>");
    private JRadioButton radio2 = new JRadioButton("<html>" + "Функция y=sin(x): " + "</html>");
    private JRadioButton radio3 = new JRadioButton("<html>" + "Функция y=x^2: " + "</html>");
    private JRadioButton radio4 = new JRadioButton("<html>" + "Функция y=cos(x): " + "</html>");
    private JRadioButton radio5 = new JRadioButton("<html>" + "Функция y=lg(x): " + "</html>");
    private JRadioButton radio6 = new JRadioButton("<html>" + "Функция y=e^x: " + "</html>");
    private JRadioButton radio7 = new JRadioButton("<html>" + "Функция y=ln(x): " + "</html>");
    private JCheckBox check = new JCheckBox("Высчитать интеграл: ");

    public Simple1() {


        super("Простые примеры");
        this.setBounds(150, 150, 660, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = panel1;
        container.setLayout(new BorderLayout());

        //задаем окна для ввода и вывода
        inputPanel.add(label1);
        inputPanel.add(input1);
        inputPanel.add(label2);
        inputPanel.add(input2);
        input1.setBounds(200, 400, 160, 60);
        input2.setBounds(200, 400, 160, 60);

        ButtonGroup group = new ButtonGroup();
        group.add(radio1);
        group.add(radio2);
        group.add(radio3);
        group.add(radio4);
        group.add(radio5);
        group.add(radio6);
        group.add(radio7);

        //настройка расположения интерфейса внутри окна
        radioPanel.setLayout(new GridLayout(3, 4, 1, 1));
        buttonsPanel.setLayout(new GridLayout(1, 2, 3, 1));
        inputPanel.setLayout(new GridLayout(2, 2, 1, 1));

        //настройка положения
        container.add(radioPanel, BorderLayout.CENTER);
        container.add(inputPanel, BorderLayout.NORTH);
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

        //выбор действия внизу
        button.addActionListener(new ButtonEventListener());
        button.setBounds(260, 450, 160, 60);
        radio1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncSwitch = 1;
            }
        });
        radio2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncSwitch = 2;
            }
        });
        radio3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncSwitch = 3;
            }
        });
        radio4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncSwitch = 4;
            }
        });
        radio5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncSwitch = 5;
            }
        });
        radio6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncSwitch = 6;
            }
        });
        radio7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FuncSwitch = 7;
            }
        });
    }
        static int FuncSwitch=1;

    public static double Function(double x)//задание функции
    {
        switch (FuncSwitch) {
            case 1:
                return x;
            case 2:
                return Math.sin(x);

            case 3:
                return x * x;

            case 4:
                return Math.cos(x);

            case 5:
                return Math.log10(x);

            case 6:
                return Math.exp(x);

            case 7:
                return Math.log(x);
        }
        return 0;//дойти невозможно
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

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e)
        {
            double top = Double.parseDouble(input1.getText());
            double bot = Double.parseDouble(input2.getText());
            if(!((FuncSwitch==5 || FuncSwitch==7) && bot<=0)) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new XYLineChartExample(bot, top).setVisible(true);
                    }
                });
                String message = "";
                message += "График функции"  + " был выполнен!";
                Simple1 f = new Simple1();
            /*f.setContentPane(f.panel1);
            f.setTitle("bruh");
            f.setSize(500, 500);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
*/
                double integralStart = bot;
                double integralEnd = top;
                double integralPrecision = 0.001;
                double integralRes = f.Integral(integralStart, integralEnd, integralPrecision);
                // JOptionPane.showMessageDialog(f, "Integral from " + String.valueOf(integralStart) + " to " + String.valueOf(integralEnd) + " is " + String.valueOf(integralRes) + " (precision: " + String.valueOf(integralPrecision) + ")");
                message += "\nИнтеграл выбранной функции " +((check.isSelected()) ? String.valueOf(integralRes) : "не был высчитан по вашему решению");
                JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
            else{
            String message = "";
            message += "График функции не был выполнен!";
            message += "\nИнтеграл выбранной функции невозможно высчитать!";
            JOptionPane.showMessageDialog(null, message, "Output", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
}
