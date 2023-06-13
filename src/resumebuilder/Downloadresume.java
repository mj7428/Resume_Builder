
package resumebuilder;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Downloadresume extends JFrame {

    private JButton downloadButton;
    Font f1;

    public Downloadresume() {
        super("Download Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 650)); // Set the preferred window size
        
        f1 = new Font("Arial",Font.BOLD,20);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.LIGHT_GRAY);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 0, 0);

        JLabel textLabel = new JLabel("Download Generate Resume from Here");
        textLabel.setForeground(Color.blue);
        textLabel.setBackground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 25));
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(new EmptyBorder(10, 0, 0, 0));
        mainPanel.add(textLabel, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(30, 25, 0, 0);

        JLabel textLabel1 = new JLabel("Enter Your Name");
        textLabel1.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(textLabel1, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 25);

        JTextField name = new JTextField(20);
        name.setFont(f1);
        mainPanel.add(name, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);

        JLabel imageLabel = new JLabel();
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("resumebuilder/icon/resume2.jpg"));
        Image i = img.getImage().getScaledInstance(650, 400, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(i);
        imageLabel.setIcon(img2);
        mainPanel.add(imageLabel, gbc);

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(30, 0, 0, 0);


        downloadButton = new JButton("Download Resume");
        downloadButton.setFont(new Font("Arial", Font.BOLD, 24));
        downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                //ResumeBuilder obj = new ResumeBuilder();
                String r = name.getText();
                String q ="file:///C:/Users/mkjai/OneDrive/Documents/NetBeansProjects/ResumeBuilder/"+r+"_Resume.txt";
                String w= r+"_Resume.txt";
                URL url = new URL(q);
                String fileName = url.getFile();
                Path targetPath = Path.of("C:/Users/mkjai/Downloads", w);
                Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                JOptionPane.showMessageDialog(null, "Resume downloaded successfully!");
                
                ConnectionClass obj=new ConnectionClass();
                String q1="delete from resumedetails where name = '"+r+"'";
                int rea=obj.stm.executeUpdate(q1);
                
                }
                catch(Exception ee){
                    ee.printStackTrace();
                
                }
            }
        });
        
        mainPanel.add(downloadButton, gbc);

        getContentPane().add(mainPanel);
        pack(); // Adjust the window size to fit the content
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Downloadresume();
            }
        });
    }
}

class DownloadResumefile {

    public static void downloadFile(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            BufferedInputStream inputStream = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream("resume.txt"); // Specify the desired file name

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer, 0, 1024)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            fileOutputStream.close();
            inputStream.close();

            JOptionPane.showMessageDialog(null, "Resume downloaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error downloading the resume: " + e.getMessage());
        }
    }
}




