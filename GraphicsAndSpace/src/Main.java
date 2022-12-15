import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        Simple1 f = new Simple1();
        f.setContentPane(f.panel1);
        f.setTitle("Простейшие графики");
        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}