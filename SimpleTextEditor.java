import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.io.*;

public class SimpleTextEditor implements ActionListener {
    JFrame frame;

    JTextArea jTextArea;
    JMenuBar jMenuBar;

    JMenu File;
    JMenu Edit;
    JMenu Close;

    JMenuItem NewFile;
    JMenuItem OpenFile;
    JMenuItem SaveFile;
    JMenuItem PrintFile;

    JMenuItem Cut;
    JMenuItem Copy;
    JMenuItem Paste;

    JMenuItem CloseEditor;

    SimpleTextEditor() {
        // Creating Frame
        frame = new JFrame("Simple Text Editor");
        frame.setBounds(0, 0, 800, 1000);
        // Initalizing the textArea
        jTextArea = new JTextArea("Welcome to editor");

        // Creating MenuBar
        jMenuBar = new JMenuBar();
        // Creating Different Menu
        Edit = new JMenu("Edit");
        File = new JMenu("File");
        Close = new JMenu("Close");

        // Adding Menu into MenuBar
        jMenuBar.add(File);
        jMenuBar.add(Edit);
        jMenuBar.add(Close);

        // Creating Menu Items for file
        OpenFile = new JMenuItem("Open");
        OpenFile.addActionListener(this);
        NewFile = new JMenuItem("New");
        NewFile.addActionListener(this);
        SaveFile = new JMenuItem("Save");
        SaveFile.addActionListener(this);
        PrintFile = new JMenuItem("Print");
        PrintFile.addActionListener(this);
        // Creating Menu Items for Close
        CloseEditor = new JMenuItem("Close");
        CloseEditor.addActionListener(this);
        Close.add(CloseEditor);

        // Creating Menu Items for Edit
        Copy = new JMenuItem("Copy");
        Copy.addActionListener(this);
        Cut = new JMenuItem("Cut");
        Cut.addActionListener(this);
        Paste = new JMenuItem("Paste");
        Paste.addActionListener(this);

        // Adding menu items into Edit Menu
        Edit.add(Cut);
        Edit.add(Copy);
        Edit.add(Paste);

        // Adding menu items into File Menu
        File.add(NewFile);
        File.add(OpenFile);
        File.add(SaveFile);
        File.add(PrintFile);
        // Adding menu into menubar
        frame.setJMenuBar(jMenuBar);
        // Adding textarea into frame
        frame.add(jTextArea);
        // Adding The Close Features
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        SimpleTextEditor editor = new SimpleTextEditor();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("Copy")) {
            jTextArea.copy();
        } else if (s.equals("Cut")) {
            jTextArea.cut();
        } else if (s.equals("Paste")) {
            jTextArea.paste();
        } else if (s.equals("Print")) {
            try {
                jTextArea.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
        } else if (s.equals("New")) {
            jTextArea.setText("");
        } else if (s.equals("Close")) {
            System.exit(1);
        } else if (s.equals("Open")) {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if (ans == JFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                String s1 ="", s2 ="";
                try {
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2 = bufferedReader.readLine();
                    while ((s1 = bufferedReader.readLine())!= null) {
                        s2 += s1 +"\n";
                    }

                    jTextArea.setText(s2);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        } else if (s.equals("Save")) {
            JFileChooser jFileChooser = new JFileChooser("C:");
            int ans = jFileChooser.showOpenDialog(null);
            if (ans == JFileChooser.APPROVE_OPTION) {
                File file = new File(jFileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(file, false));
                    writer.write(jTextArea.getText());
                    writer.flush();
                    writer.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            }
        }
    }
}