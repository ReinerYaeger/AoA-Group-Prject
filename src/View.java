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
    JPanel fileLocationPanelActivities;
    JLabel fileLocationLabelActivitiesActivities;
    JTextField fileLocationTextFieldActivities;
    JButton fileLocationButton;
    JPanel resultPanel;
    JLabel resultLabel;
    JTextArea resultTextArea;
    JScrollPane scroll;

    //create a user interface using the gridbag layout manager and add the components to the frame in the View constructor
    public View() {
        frame = new JFrame("File Analysis");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(10, 10, 10, 10);

        fileLocationPanelPerson = new JPanel();
        fileLocationLabelPerson = new JLabel("Person File Location");
        fileLocationTextFieldPerson = new JTextField(20);
        fileLocationPanelPerson.add(fileLocationLabelPerson);
        fileLocationPanelPerson.add(fileLocationTextFieldPerson);
        c.gridx = 0;
        c.gridy = 0;
        frame.add(fileLocationPanelPerson, c);

        fileLocationPanelActivities = new JPanel();
        fileLocationLabelActivitiesActivities = new JLabel("Activities File Location");
        fileLocationTextFieldActivities = new JTextField(20);
        fileLocationPanelActivities.add(fileLocationLabelActivitiesActivities);
        fileLocationPanelActivities.add(fileLocationTextFieldActivities);
        c.gridx = 0;
        c.gridy = 1;
        frame.add(fileLocationPanelActivities, c);

        fileLocationButton = new JButton("Analyse");
        c.gridx = 0;
        c.gridy = 2;
        frame.add(fileLocationButton, c);

        resultPanel = new JPanel();
        resultLabel = new JLabel("Result");
        resultTextArea = new JTextArea(10, 20);
        resultTextArea.setEditable(false);
        scroll = new JScrollPane(resultTextArea);
        resultPanel.add(resultLabel);
        resultPanel.add(scroll);
        c.gridx = 0;
        c.gridy = 3;
        frame.add(resultPanel, c);

        frame.setVisible(true);

        fileLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String filePersonLocation = fileLocationTextFieldPerson.getText();
                String fileActLocation2 = fileLocationTextFieldActivities.getText();
                Controller controller = new Controller();

                if(!controller.isFileFound(filePersonLocation,fileActLocation2)) {
                    return;
                }
                controller.readPersonFile(filePersonLocation);
                controller.readActFile(fileActLocation2);
                Frame frame1 = new Frame();
                frame1.setVisible(true);
                frame1.setSize(1000,1000);
                JTextArea jt = new JTextArea(50,50);
                jt.append(controller.getPersonList().toString());
                frame1.add(jt);
            }
        });
    }

}
