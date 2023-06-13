package resumebuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import resumebuilder.ResumeTemplate;

public class ResumeTemplate{
    private JFrame frame;
    
    public ResumeTemplate(){
        JTextPane resumeTextPane = new JTextPane();
        resumeTextPane.setContentType("text/html");
        resumeTextPane.setEditable(false);

        // Fetch resume details from MySQL database
        try {
            // Connect to the MySQL database
            ConnectionClass obj = new ConnectionClass();
            
            

            // Create a statement
            

            // Execute the SQL query to fetch resume details
            String sqlQuery = "SELECT * FROM resumedetails";
            ResultSet resultSet = obj.stm.executeQuery(sqlQuery);

            // Process the result set and set the resume content
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String Gender = resultSet.getString("gender");
                String fathername = resultSet.getString("fathername");
                String password = resultSet.getString("password");
                String city = resultSet.getString("city");
                String email = resultSet.getString("email");
                String skills = resultSet.getString("skills");
                String college = resultSet.getString("college");
                String degree = resultSet.getString("degree");
                String linkdin = resultSet.getString("linkedin");
                String github = resultSet.getString("github");
                String contact = resultSet.getString("contact");
                String Experience = resultSet.getString("experience");

                // Set the resume content with HTML formatting
                String resumeContent = "<html>" +
                       "<h1 style='color: #336699; font-size: 24pt;'>" + name + "</h1>" +
                        "<table>" +
                        "<tr><td><b>Gender:</b></td><td>" + Gender + "</td></tr>" +
                        "<tr><td><b>Father Name:</b></td><td>" + fathername + "</td></tr>" +
                        "<tr><td><b>Password:</b></td><td>" + password + "</td></tr>" +
                        "<tr><td><b>City:</b></td><td>" + city + "</td></tr>" +
                        "<tr><td><b>Email:</b></td><td>" + email + "</td></tr>" +
                        "<tr><td><b>Contact:</b></td><td>" + contact + "</td></tr>" +
                        "<tr><td><b>College:</b></td><td>" + college + "</td></tr>" +
                        "<tr><td><b>Degree:</b></td><td>" + degree + "</td></tr>" +
                        "</table><br>" +
                        "<h2 style='color: #336699;'>LinkdIN Link</h2>" +
                        "<p>" + linkdin + "</p><br>" +
                        "<h2 style='color: #336699;'>Github LINK</h2>" +
                        "<p>" + github + "</p><br>" +
                        "<h2 style='color: #336699;'>Skills</h2>" +
                        "<p>" + skills + "</p><br>" +
                        "<h2 style='color: #336699;'>Experience</h2>" +
                        "<p>" + Experience + "</p>" +
                        "</html>";



                resumeTextPane.setText(resumeContent);
            }

            // Close the connections
            resultSet.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        JScrollPane scrollPane = new JScrollPane(resumeTextPane);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        // Create a JFrame to display the resume
        JFrame frame = new JFrame("Resume Template");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.add(scrollPane, BorderLayout.EAST);
        
        frame.add(scrollPane);
        //frame.add(new JScrollPane(resumeTextPane));

        // Create a panel for the download button
        JPanel buttonPanel = new JPanel();
        JButton downloadButton = new JButton("Download");

        // Add the download button to the button panel
        buttonPanel.add(downloadButton);
        
         downloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to open a new frame or redirect to a different page for download
                // Replace this code with your own logic
                Downloadresume downloadFrame = new Downloadresume();
                downloadFrame.setVisible(true);
                frame.setVisible(false);
            }
        });

        // Set the layout of the frame to BorderLayout
        frame.setLayout(new BorderLayout());

        // Add the resumeTextPane to the center of the frame
        frame.add(resumeTextPane, BorderLayout.CENTER);

        // Add the button panel to the bottom of the frame
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Set the frame properties
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        
    
    }
    public void showFrame() {
        if (frame != null) {
            frame.setVisible(true);
        }
    }

    public static void main(String[] args) {
        // Create a JTextPane to display the resume content
        ResumeTemplate r = new ResumeTemplate();
        
    }
    
}
   



