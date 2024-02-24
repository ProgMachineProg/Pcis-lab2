package org.homework;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GraphingFunction extends JPanel {

    private double a = 1.5; // Коефіцієнт a
    private double b = 2; // Коефіцієнт b
    private double c = 3; // Коефіцієнт c
    private double xmin = -10; // Мінімальне значення x
    private double xmax = 10; // Максимальне значення x
    private double ymin = -100; // Мінімальне значення y
    private double ymax = 100; // Максимальне значення y

    public GraphingFunction() {
        setPreferredSize(new Dimension(600, 400));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Обробка натискання лівої кнопки миші
                    double x = (e.getX() - getWidth() / 2.0) / (getWidth() / 2.0) * (xmax - xmin) + xmin;
                    double y = -(e.getY() - getHeight() / 2.0) / (getHeight() / 2.0) * (ymax - ymin) + ymax;
                    System.out.println("(" + x + ", " + y + ")");
                }
            }
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2); // Ось X
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight()); // Ось Y

        g.setColor(Color.RED);
        for (double x = xmin; x <= xmax; x += 0.01) {
            double y = a * Math.pow(x, b) + c;
            int x1 = (int) ((x - xmin) / (xmax - xmin) * getWidth());
            int y1 = (int) ((ymax - y) / (ymax - ymin) * getHeight());
            g.drawOval(x1, y1, 1, 1);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Графік складної статечної функції");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new GraphingFunction(), BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
        });
    }
}
