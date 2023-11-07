import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OptionMenu {

    private JMenu menu;

    public OptionMenu(JTextArea textArea) {
        menu = new JMenu("Option");

        JMenuItem sizeItem = new JMenuItem("Change Size");
        JMenuItem colorItem = new JMenuItem("Change Color");
        JMenuItem zoomInItem = new JMenuItem("Zoom In");
        JMenuItem zoomOutItem = new JMenuItem("Zoom Out");

        menu.add(sizeItem);
        menu.add(colorItem);
        menu.add(zoomInItem);
        menu.add(zoomOutItem);

        sizeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog("Enter font size (e.g. 12)");
                try {
                    int size = Integer.parseInt(input);
                    textArea.setFont(new Font("Arial", Font.PLAIN, size));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for size!");
                }
            }
        });

        colorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "Choose Text Color", Color.BLACK);
                textArea.setForeground(color);
            }
        });

        zoomInItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Font currentFont = textArea.getFont();
                float newSize = currentFont.getSize() * 1.2f; // Zoom in by 20%
                textArea.setFont(currentFont.deriveFont(newSize));
            }
        });

        zoomOutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Font currentFont = textArea.getFont();
                float newSize = currentFont.getSize() / 1.2f; // Zoom out by 20%
                textArea.setFont(currentFont.deriveFont(newSize));
            }
        });

        textArea.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == '+') {
                    Font currentFont = textArea.getFont();
                    Font newFont = currentFont.deriveFont((float) currentFont.getSize() + 2);
                    textArea.setFont(newFont);
                } else if (e.isControlDown() && e.getKeyChar() == '-') {
                    Font currentFont = textArea.getFont();
                    Font newFont = currentFont.deriveFont((float) currentFont.getSize() - 2);
                    textArea.setFont(newFont);
                }
            }
        });
    }

    public JMenu getMenu() {
        return menu;
    }
}
