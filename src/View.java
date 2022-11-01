import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View {

    JFrame frame;
    JPanel fileLocationPanel;
    JLabel fileLocationLabel;
    JTextField fileLocationTextField;
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

        fileLocationPanel = new JPanel();
        fileLocationPanel.setBounds(10, 10, 600, 50);
        fileLocationPanel.setLayout(null);

        fileLocationLabel = new JLabel("File Location:");
        fileLocationLabel.setBounds(10, 10, 100, 30);

        fileLocationTextField = new JTextField();
        fileLocationTextField.setBounds(110, 10, 310, 30);

        fileLocationButton = new JButton("Analyse");
        fileLocationButton.setBounds(410, 10, 90, 30);

        fileLocationPanel.add(fileLocationLabel);
        fileLocationPanel.add(fileLocationTextField);
        fileLocationPanel.add(fileLocationButton);

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

        frame.add(fileLocationPanel);
        frame.add(resultPanel);
        frame.setVisible(true);

        fileLocationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String fileLocation = fileLocationTextField.getText();
                Controller controller = new Controller();

                if(!controller.analyseFile(fileLocation)) {
                    JOptionPane.showMessageDialog(null, "File not found Try Again",
                                                  "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        });
    }
}
