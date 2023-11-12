import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class EditMenu {
    private JMenu menu;

    public EditMenu(JTabbedPane tabbedPane) {
        menu = new JMenu("Edit");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem pasteItem = new JMenuItem("Paste");

        menu.add(copyItem);
        menu.add(cutItem);
        menu.add(pasteItem);

        copyItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                activeTextArea.copy();
            }
        });

        cutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                activeTextArea.cut();
            }
        });

        pasteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
                activeTextArea.paste();
            }
        });
    }

    public JMenu getMenu() {
        return menu;
    }
}
