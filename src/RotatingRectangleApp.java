import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotatingRectangleApp extends JFrame {
    private static final int ANIMATION_DELAY = 10;
    private static final int RECTANGLE_WIDTH = 160;
    private static final int RECTANGLE_HEIGHT = 160;

    private double rotationAngle = 0;
    public RotatingRectangleApp(){
        Timer timer = new Timer(ANIMATION_DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotateRectangle();
                repaint();
            }
        });
        timer.start();

        JPanel panel= new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                drawRotatedRectangle(g);
            }
        };
        add(panel);
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// остановка приложения при закрытии окна
        setLocationRelativeTo(null); // для открытия окна по центру
        setVisible(true);
    }
    private void rotateRectangle(){
        rotationAngle += Math.toRadians(1);
    }

    //отрисовка прямоугольника
    private void drawRotatedRectangle(Graphics g) {

        //расчет всех координат
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        int x1 = (centerX - RECTANGLE_WIDTH / 2);
        int y1 = (centerY - RECTANGLE_HEIGHT / 2);

        int x2 = (int) (centerX + RECTANGLE_WIDTH / 2 * Math.cos(rotationAngle) - RECTANGLE_HEIGHT / 2 * Math.sin(rotationAngle));
        int y2 = (int) (centerY + RECTANGLE_WIDTH / 2 * Math.sin(rotationAngle) + RECTANGLE_HEIGHT / 2 * Math.cos(rotationAngle));

        int x3 = (centerX + RECTANGLE_WIDTH / 2);
        int y3 = (centerY + RECTANGLE_HEIGHT / 2);

        int x4 = (int) (centerX - RECTANGLE_WIDTH / 2 * Math.cos(rotationAngle) + RECTANGLE_HEIGHT / 2 * Math.sin(rotationAngle));
        int y4 = (int) (centerY - RECTANGLE_WIDTH / 2 * Math.sin(rotationAngle) - RECTANGLE_HEIGHT / 2 * Math.cos(rotationAngle));


        //создание прямогульника и установление его цвета
        g.setColor(Color.BLUE);
        int[] xPoints = {x1, x2, x3, x4};
        int[] yPoints = {y1, y2, y3, y4};
        g.fillPolygon(xPoints, yPoints, 4);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RotatingRectangleApp::new);
    }
}
