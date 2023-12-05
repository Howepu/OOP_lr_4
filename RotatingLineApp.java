import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatingLineApp extends JFrame {
    private LinePanel linePanel;
    private Timer timer;
    private double angle = 0.0;
    private Color currentColor;

    public RotatingLineApp() {
        setTitle("Rotating Line Animation");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        linePanel = new LinePanel();
        add(linePanel);

        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                angle += 0.02; // Увеличиваем угол вращения
                currentColor = calculateColor(); // Вычисляем текущий цвет
                linePanel.repaint(); // Перерисовываем панель
            }
        });
        timer.start();

        setVisible(true);
    }

    private Color calculateColor() {
        float hue = (float) ((angle / (2 * Math.PI)) % 1.0); // Преобразовываем угол в цветовой тон
        return Color.getHSBColor(hue, 1.0f, 1.0f);
    }

    private class LinePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            int centerX = getWidth() / 2;
            int centerY = getHeight() / 2;

            int lineLength = 150;
            int x1 = centerX - lineLength / 2;
            int y1 = centerY;
            int x2 = centerX + lineLength / 2;
            int y2 = centerY;

            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(currentColor);
            g2d.setStroke(new BasicStroke(2.0f));

            // Поворачиваем линию
            g2d.rotate(angle, centerX, centerY);
            g2d.drawLine(x1, y1, x2, y2);
            g2d.rotate(-angle, centerX, centerY); // Возвращаем координаты в исходное положение
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RotatingLineApp());
    }
}
