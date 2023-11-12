import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;

public class FileMenu {
    private JMenu menu;
    private JTabbedPane tabbedPane;
    private int newFileCount = 1;


    public FileMenu(JTabbedPane tabbedPane) {
        this.tabbedPane = tabbedPane;
        menu = new JMenu("File");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem saveAsItem = new JMenuItem("Save As");
        JMenuItem newFileItem = new JMenuItem("New File");
        JMenuItem closeItem = new JMenuItem("Close");


        menu.add(openItem);
        menu.add(saveItem);
        menu.add(saveAsItem);
        menu.add(newFileItem);
        menu.add(closeItem);

        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JScrollPane scrollPane = (JScrollPane) tabbedPane.getSelectedComponent();
                JViewport viewPort = scrollPane.getViewport();
                JTextArea textArea = (JTextArea) viewPort.getView();
                JFileChooser fileChooser = new JFileChooser();

                int returnValue = fileChooser.showSaveDialog(null);

                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        File file = fileChooser.getSelectedFile();
                        String filePath = file.getAbsolutePath();

                        if (!filePath.toLowerCase().endsWith(".txt")) {
                            file = new File(filePath + ".txt");
                        }

                        FileWriter writer = new FileWriter(file);
                        writer.write(textArea.getText());
                        writer.close();

                        // Update the tab title to the saved file name
                        int selectedIndex = tabbedPane.getSelectedIndex();
                        tabbedPane.setTitleAt(selectedIndex, file.getName());

                        saveTextProperties(file, tabbedPane);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });


        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    try {
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        String line;
                        StringBuilder fileContent = new StringBuilder();
                        while ((line = reader.readLine()) != null) {
                            fileContent.append(line).append("\n");
                        }
                        reader.close();
                        JTextArea newTextArea = new JTextArea();
                        JScrollPane scrollPane = new JScrollPane();
                        scrollPane.setViewportView(newTextArea);
                        newTextArea.setText(fileContent.toString());

                        tabbedPane.addTab(file.getName(), new JScrollPane(newTextArea));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    loadTextProperties(file, tabbedPane);
                }
            }
        });

        newFileItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JTextArea newTextArea = new JTextArea();
                tabbedPane.addTab("Untitled" + newFileCount, new JScrollPane(newTextArea));
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
                newFileCount++;


            }
        });

        closeItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tabbedPane.getSelectedIndex();
                if (selectedIndex >= 0) {
                    int choice = JOptionPane.showConfirmDialog(null, "Close without saving?", "Confirm", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        tabbedPane.remove(selectedIndex);
                    }
                }
            }
        });
    }

    public JMenu getMenu() {
        return menu;
    }

    private void saveTextProperties(File file, JTabbedPane tabbedPane) {
        JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();

        try {
            BasicFileAttributes attrs = Files.readAttributes(file.toPath(), BasicFileAttributes.class);
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class);
            view.write("TextColor", Charset.defaultCharset().encode(activeTextArea.getForeground().getRGB() + ""));
            view.write("FontSize", Charset.defaultCharset().encode(activeTextArea.getFont().getSize() + ""));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void loadTextProperties(File file, JTabbedPane tabbedPane) {
        try {
            UserDefinedFileAttributeView view = Files.getFileAttributeView(file.toPath(), UserDefinedFileAttributeView.class);
            ByteBuffer textColor = ByteBuffer.allocate(view.size("TextColor"));
            ByteBuffer fontSize = ByteBuffer.allocate(view.size("FontSize"));
            view.read("TextColor", textColor);
            view.read("FontSize", fontSize);

            int colorValue = Integer.parseInt(new String(textColor.array(), "UTF-8"));
            int fontSizeValue = Integer.parseInt(new String(fontSize.array(), "UTF-8"));


            JTextArea activeTextArea = (JTextArea) ((JScrollPane) tabbedPane.getSelectedComponent()).getViewport().getView();
            activeTextArea.setForeground(new Color(colorValue));
            activeTextArea.setFont(activeTextArea.getFont().deriveFont((float) fontSizeValue));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}

