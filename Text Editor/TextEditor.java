import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TextEditor implements ActionListener {
    JFrame frame;

    JMenuBar menuBar;

    JTextArea textArea;

    JMenu file, edit;

    // Initalize the file menu and edit menu items
    JMenuItem newFile, openFile, saveFile;
    JMenuItem cut, copy, paste, selectAll, close;

    TextEditor() {
        // Intitalize my Frame
        frame = new JFrame();
        // Initalize menubar
        menuBar = new JMenuBar();

        // Initalize Text Area
        textArea = new JTextArea();

        // Initalize file and edit menu
        file = new JMenu("File");
        edit = new JMenu("Edit");

        // Initalize File menu Items
        newFile = new JMenuItem("New File");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        // adding Action listener to File items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Adding file items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        // Initalize Edit menu Items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        // adding action listener to edit items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        // Adding file items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        // Adds menus to Menubar
        menuBar.add(file);
        menuBar.add(edit);

        // Adding menu to Frame
        frame.setJMenuBar(menuBar);

        // adding text area to frame
        // frame.add(textArea);

        // Create a Content Pane
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));

        // Add text Area to Panel
        panel.add(textArea, BorderLayout.CENTER);

        // Adding a Scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        // add panel to frame
        frame.add(panel);

        // set Dimension for my Frame Window
        frame.setBounds(0, 0, 400, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == cut) {
            textArea.cut();
        }

        if (actionEvent.getSource() == copy) {
            textArea.copy();
        }

        if (actionEvent.getSource() == copy) {
            textArea.copy();
        }
        if (actionEvent.getSource() == paste) {
            textArea.paste();
        }
        if (actionEvent.getSource() == selectAll) {
            textArea.selectAll();
        }
        if (actionEvent.getSource() == close) {
            // status here means closing the current session
            System.exit(0);
        }
        if (actionEvent.getSource() == openFile) {

            // open a file Chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            // if we have clicked on open folder
            if (chooseOption == JFileChooser.APPROVE_OPTION) {

                // getting selected file
                File file = fileChooser.getSelectedFile();

                // get the path of selected file
                String filePath = file.getPath();
                try {

                    // Initalize file Reader
                    FileReader fileReader = new FileReader(filePath);

                    // Initalize Buffer Reader
                    BufferedReader bufferReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";

                    // Read content of file Line by Line
                    while ((intermediate = bufferReader.readLine()) != null) {
                        output += intermediate + "\n";
                    }

                    // Set the output string to textArea
                    textArea.setText(output);

                } catch (IOException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        }

        if (actionEvent.getSource() == saveFile) {

            // Initalize file picker
            JFileChooser fileChooser = new JFileChooser("C:");
            // Get choose option from filechooser

            int chooseOption = fileChooser.showSaveDialog(null);

            if (chooseOption == JFileChooser.APPROVE_OPTION) {
                // here we are creating a new file and select the path
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".txt");
                try {
                    FileWriter fileWriter = new FileWriter(file);

                    BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferWriter);
                    bufferWriter.close();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }

        if (actionEvent.getSource() == newFile) {
            TextEditor newTextEditor = new TextEditor();
        }
    }

    public static void main(String[] args) {

        TextEditor textEditor = new TextEditor();
    }
}