package com.angelov00;

import javax.swing.*;
import java.awt.*;

public class SierpinskiCarpet extends JPanel {
    private int depth = 0;

    public SierpinskiCarpet() {
        setBackground(Color.WHITE);
    }

    public void setDepth(int depth) {
        this.depth = depth;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight());
        int x = (getWidth() - size) / 2;
        int y = (getHeight() - size) / 2;
        drawCarpet(g2d, x, y, size, depth);
    }

    private void drawCarpet(Graphics2D g, int x, int y, int size, int depth) {
        if (depth == 0 || size <= 1) {
            g.setColor(Color.BLACK);
            g.fillRect(x, y, size, size);
            return;
        }

        int newSize = (int) Math.ceil(size / 3.00);

        for (int dx = 0; dx < 3; dx++) {
            for (int dy = 0; dy < 3; dy++) {
                if (dx == 1 && dy == 1) {
                    continue;
                }

                int currentX = x + dx * newSize;
                int currentY = y + dy * newSize;

                drawCarpet(g, currentX, currentY, newSize, depth - 1);
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski Carpet");
        SierpinskiCarpet carpet = new SierpinskiCarpet();

        JSlider depthSlider = new JSlider(0, 6, 0);
        depthSlider.setMajorTickSpacing(1);
        depthSlider.setPaintTicks(true);
        depthSlider.setPaintLabels(true);
        depthSlider.addChangeListener(e -> {
            int newDepth = depthSlider.getValue();
            carpet.setDepth(newDepth);
        });

        JPanel sliderPanel = new JPanel();
        sliderPanel.add(new JLabel("Depth:"));
        sliderPanel.add(depthSlider);

        frame.add(sliderPanel, BorderLayout.SOUTH);
        frame.add(carpet, BorderLayout.CENTER);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}