import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EditMenu {
    private JMenu menu;

    public EditMenu(JTextArea textArea) {
        menu = new JMenu("Edit");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem pasteItem = new JMenuItem("Paste");

        menu.add(copyItem);
        menu.add(cutItem);
        menu.add(pasteItem);

        copyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.copy();
            }
        });

        cutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.cut();
            }
        });

        pasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.paste();
            }
        });
    }

    public JMenu getMenu() {
        return menu;
    }
}
