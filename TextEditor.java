import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TextEditor extends JFrame {

    private JTextArea textArea;
    private JMenuBar menuBar;


    public TextEditor() {
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        FileMenu fileMenu = new FileMenu(textArea);
        EditMenu editMenu = new EditMenu(textArea);
        OptionMenu optionMenu = new OptionMenu(textArea);
        menuBar.add(fileMenu.getMenu());
        menuBar.add(editMenu.getMenu());
        menuBar.add(optionMenu.getMenu());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 500);
        this.setTitle("Simple Text Editor");
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });
        this.setVisible(true);
    }

    private void resizeComponents() {
        menuBar.setPreferredSize(new Dimension(getWidth(), 40));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextEditor();
            }
        });
    }
}
