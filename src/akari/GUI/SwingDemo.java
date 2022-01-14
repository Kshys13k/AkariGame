package akari.GUI;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
public class SwingDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Demo Frame");
        JPanel panel = new JPanel();
        frame.getContentPane();
        JLabel label = new JLabel("Demo Label!");
        Dimension size = label.getPreferredSize();
        label.setBounds(150, 100, size.width, size.height);
        panel.setLayout(null);
        panel.add(label);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(500, 300);
        frame.setVisible(true);
    }
}