import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TextEditor implements ActionListener {
    // declare properties of TextEditor
    JFrame frame;
    JMenuBar menuBar;

    JTextArea textArea;


    JMenu file, edit;

    //File Menu items
    JMenuItem newFile, openFile, saveFile;

    //Edit menu Items
    JMenuItem cut, copy, paste, selectAll, close;
    TextEditor() {
        //Initialize a frame
        frame = new JFrame();

        //Initialie menubar
        menuBar = new JMenuBar();

        //Initialize JTextArea

        textArea = new JTextArea();

        //Initialize menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize File Menu Items
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        //Adding action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        // Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize Edit Menu Items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("SelectAll");
        close = new JMenuItem("Close");

        //Adding action listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Add menu items to Edit Menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //Add menus to menubar
        menuBar.add(file);
        menuBar.add(edit);

        //set Menubar to frame
        frame.setJMenuBar(menuBar);

        //set menubar to frame
        frame.setJMenuBar(menuBar);

        //Creating content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new BorderLayout(0, 0));
        //Adding textArea to Panel
        panel.add(textArea, BorderLayout.CENTER);
        //Creating scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Adding scrollBar to panel
        panel.add(scrollPane);
        //Adding panel to frame
        frame.add(panel);
        //set dimensions of frame
        frame.setBounds(100, 100, 400, 400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);


    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(actionEvent.getSource() == cut){
            //perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()== copy){
            //perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()== paste){
            //perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()== selectAll){
            //select all the text
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            //close the text editor
            System.exit(0);
        }

        if(actionEvent.getSource()== openFile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();

                String filePath = file.getPath();

                try {
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //BufferReader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    //read contents of file
                    while ((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate + "\n";
                    }

                    textArea.setText(output);
                    bufferedReader.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }

        if(actionEvent.getSource() == saveFile){
            //initialize file chooser
            JFileChooser fileChooser = new JFileChooser("C:");
            //getting choose option from chooser
            int chooseOption1 = fileChooser.showSaveDialog(null);
            //Checking which option is chosen
            if(chooseOption1 == JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsoluteFile()+".txt");

                try{
                    //Ini fileWriter
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write contents of textArea
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }

            }

        }
        if(actionEvent.getSource() == newFile){
            new TextEditor();
        }
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();

    }
}