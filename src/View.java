import com.sun.source.tree.Tree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {

    JFrame frame;
    JPanel fileLocationPanelPerson;
    JLabel fileLocationLabelPerson;
    JTextField fileLocationTextFieldPerson;
    JButton fileLocationButton;
    JPanel resultPanel;
    JLabel resultLabel;
    JTextArea resultTextArea;
    JScrollPane scroll;

    public View() {

        //create a layout configuration using the Flowlayout class
        FlowLayout layout = new FlowLayout();
        frame = new JFrame("File Reader");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(null);

        fileLocationPanelPerson = new JPanel();
        fileLocationPanelPerson.setBounds(10, 10, 600, 50);
        fileLocationPanelPerson.setLayout(null);

        fileLocationLabelPerson = new JLabel("Person File Location:");
        fileLocationLabelPerson.setBounds(10, 10, 100, 30);

        fileLocationTextFieldPerson = new JTextField();
        fileLocationTextFieldPerson.setBounds(110, 10, 310, 30);

        fileLocationButton = new JButton("Analyse");
        fileLocationButton.setBounds(410, 10, 90, 30);

        fileLocationPanelPerson.add(fileLocationLabelPerson);
        fileLocationPanelPerson.add(fileLocationTextFieldPerson);
        fileLocationPanelPerson.add(fileLocationButton);

        resultPanel = new JPanel();
        resultPanel.setBounds(10, 70, 600, 380);
        resultPanel.setLayout(null);

        resultLabel = new JLabel("Result:");
        resultLabel.setBounds(10, 10, 100, 30);

        resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);
        resultTextArea.setBounds(10, 50, 500, 320);
        resultTextArea.setLineWrap(true);

        scroll = new JScrollPane(resultTextArea);
        scroll.setBounds(10, 50, 500, 320);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        resultPanel.add(resultLabel);
        resultPanel.add(scroll);

        frame.add(fileLocationPanelPerson);
        frame.add(resultPanel);
        frame.setVisible(true);

        fileLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fileLocation = fileLocationTextFieldPerson.getText();
                Controller controller = new Controller();

                if(!controller.isFileFound(fileLocation)) {
                    JOptionPane.showMessageDialog(null, "File not found Try Again",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                controller.analyseFile(fileLocation);
            }
        });
    }
}
