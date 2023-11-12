import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OptionMenu {

    private JMenu menu;

    public OptionMenu(JTabbedPane tabbedPane) {
        menu = new JMenu("Option");

        JMenuItem sizeItem = new JMenuItem("Change Size");
        JMenuItem colorItem = new JMenuItem("Change Color");
        JMenuItem zoomInItem = new JMenuItem("Zoom In");
        JMenuItem zoomOutItem = new JMenuItem("Zoom Out");
        JMenuItem backgroundColorItem = new JMenuItem("Backgornd");

        menu.add(sizeItem);
        menu.add(colorItem);
        menu.add(zoomInItem);
        menu.add(zoomOutItem);
        menu.add(backgroundColorItem);

        sizeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                String input = JOptionPane.showInputDialog("Enter font size (e.g. 12)");
                try {
                    int size = Integer.parseInt(input);
                    activeTextArea.setFont(new Font("Arial", Font.PLAIN, size));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input for size!");
                }
            }
        });

        colorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                Color color = JColorChooser.showDialog(null, "Choose Text Color", Color.BLACK);
                activeTextArea.setForeground(color);
            }
        });

        zoomInItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                Font currentFont = activeTextArea.getFont();
                float newSize = currentFont.getSize() * 1.2f; // Zoom in by 20%
                activeTextArea.setFont(currentFont.deriveFont(newSize));
            }
        });

        zoomOutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                Font currentFont = activeTextArea.getFont();
                float newSize = currentFont.getSize() / 1.2f; // Zoom out by 20%
                activeTextArea.setFont(currentFont.deriveFont(newSize));
            }
        });

        tabbedPane.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.isControlDown() && e.getKeyChar() == '+') {
                    JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                    Font currentFont = activeTextArea.getFont();
                    Font newFont = currentFont.deriveFont((float) currentFont.getSize() + 2);
                    activeTextArea.setFont(newFont);
                } else if (e.isControlDown() && e.getKeyChar() == '-') {
                    JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                    Font currentFont = activeTextArea.getFont();
                    Font newFont = currentFont.deriveFont((float) currentFont.getSize() - 2);
                    activeTextArea.setFont(newFont);
                }
            }
        });

        backgroundColorItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                System.out.println(activeTextArea);
                if (activeTextArea != null) {
                    Color backgroundColor = JColorChooser.showDialog(null, "Choose Background Color", Color.WHITE);
                    activeTextArea.setBackground(backgroundColor);
                }
            }
        });


    }

    public JMenu getMenu() {
        return menu;
    }



}

