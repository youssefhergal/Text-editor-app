import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class TextEditor extends JFrame {

    private JTextArea textArea;
    private JMenuBar menuBar;
    private JTabbedPane tabbedPane;


    public TextEditor() {
        textArea = new JTextArea();
        tabbedPane = new JTabbedPane();
        JScrollPane scrollPane = new JScrollPane(textArea);
        this.add(scrollPane);
        this.add(tabbedPane);

        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        FileMenu fileMenu = new FileMenu(tabbedPane);
        EditMenu editMenu = new EditMenu(tabbedPane);
        OptionMenu optionMenu = new OptionMenu(tabbedPane);
        menuBar.add(fileMenu.getMenu());
        menuBar.add(editMenu.getMenu());
        menuBar.add(optionMenu.getMenu());

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100,100,800,600);
        this.setTitle("Simple Text Editor");
        this.setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TextEditor();
            }
        });
    }
}
