import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class MainFrame {

	final int MAX = 10000; //max amount of people
	JButton button;
	static BufferedReader in; 
	static BufferedWriter out;
	static int numOfUsers;
	String fullName;
    String[] names, emails, passwords, grades, points;
	//static String fileName = "C:\\Users\\Shreya S\\Documents\\GitHub\\FBLACP\\src\\data\\students.txt";
	static Student curUser;
    public MainFrame() throws ClassNotFoundException, IOException {
    	//reads in students
    	/**in = new BufferedReader(new FileReader("data\\students.txt"));
    	names = new String[2*MAX];
    	emails = new String[MAX];
    	passwords = new String[MAX];
    	grades = new String[MAX];
    	points = new String[MAX];
    	names = in.readLine().split(" ");
    	emails = in.readLine().split(" ");
    	passwords = in.readLine().split(" ");
    	grades = in.readLine().split(" ");
    	points = in.readLine().split(" ");
    	numOfUsers = points.length;
    	for(int i = 0, n = 0; i < numOfUsers; i++) {
    		fullName = names[n] + " " + names[n+1]; 
            n+=2;
    		Student.getStudents().add(new Student(fullName, emails[i], passwords[i], Integer.parseInt(grades[i]), Integer.parseInt(points[i])));
    	}
    	in.close();*/
        if(Event.eventList.isEmpty())EventsDataFile.Input(); 

        /**students for testing 
        Student.addNineStudent(new Student("Sophie Tester", "sophie@student.ca", "testing", 9, 10));
        Student.addTenStudent(new Student("Liam Tester", "liam@student.ca", "testing", 10, 30));
        Student.addElevenStudent(new Student("Neo Tester", "neo@student.ca", "testing", 11, 30));
        Student.addTwelveStudent(new Student("Dan Tester", "dan@student.ca", "testing", 12, 20));
        Student.addTenStudent(new Student("Sarah Tester", "sarah@student.ca", "testing", 10, 30));*/

        System.out.println(String.valueOf(Student.getNineStudents()));
        System.out.println(String.valueOf(Student.getTenStudents()));
        System.out.println(String.valueOf(Student.getElevenStudents()));
        System.out.println(String.valueOf(Student.getTwelveStudents()));
        

        //defining the quarter-end dates
        System.out.println();
        String q1 = "", q2 = "", q3 = "", q4 = ""; 
        String[] date = new String[2];
        LocalDate quarter1 = null, quarter2 = null, quarter3 = null, quarter4 = null;
        //try {
            in = new BufferedReader(new FileReader("data\\quarterlyDates.txt"));
            for(int i = 0; i < 4; i++){
                date = in.readLine().split("\\|");
                if(date[1].equals("1")){
                    q1 = date[0];
                } else if (date[1].equals("2")){
                    q2 = date[0];
                } else if (date[1].equals("3")){
                    q3 = date[0];  
                } else if (date[1].equals("4")){
                    q4 = date[0];
                }
            }
            /**while(!date.equals("")){
                for(String n : date){
                    System.out.print(n);
                }
                System.out.println();
                if(date[1].equals("1")){
                    q1 = date[0];
                } else if (date[1].equals("2")){
                    q2 = date[0];
                } else if (date[1].equals("3")){
                    q3 = date[0];  
                } else if (date[1].equals("4")){
                    q4 = date[0];
                }
                date = in.readLine().split("|");
            }*/
       // } catch (NullPointerException e){
         //   e.printStackTrace();
        //}

        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);
        System.out.println(q4);
        System.out.println();
        
        quarter1 = LocalDate.parse(q1, DateTimeFormatter.ISO_LOCAL_DATE);
        quarter2 = LocalDate.parse(q2, DateTimeFormatter.ISO_LOCAL_DATE);
        quarter3 = LocalDate.parse(q3, DateTimeFormatter.ISO_LOCAL_DATE);
        quarter4 = LocalDate.parse(q4, DateTimeFormatter.ISO_LOCAL_DATE);

            /**for(int i = 2; i >= 0; i--){
                q1 += AdminView.quarterDates[0][i];
            }
            System.out.println("q1: " + q1);
            quarter1 = LocalDate.parse(q1, DateTimeFormatter.ISO_LOCAL_DATE);
            q1 = String.valueOf(quarter1);
            for(int i = 2; i > -1; i--){
                q2 += AdminView.quarterDates[1][i];
            }
            System.out.println("q2: " + q2);
            quarter2 = LocalDate.parse(q2, DateTimeFormatter.ISO_LOCAL_DATE);
            q2 = String.valueOf(quarter2);
            for(int i = 2; i > -1; i--){
                q3 += AdminView.quarterDates[2][i];
            }
            System.out.println("q3: " + q3);
            quarter3 = LocalDate.parse(q3, DateTimeFormatter.ISO_LOCAL_DATE);
            q3 = String.valueOf(quarter3);
            for(int i = 2; i > -1; i--){
                q4 += AdminView.quarterDates[3][i];
            }
            System.out.println("q4: " + q4);
            quarter4 = LocalDate.parse(q4, DateTimeFormatter.ISO_LOCAL_DATE);
            q4 = String.valueOf(quarter4);
        /} catch (ArrayIndexOutOfBoundsException | java.time.format.DateTimeParseException e){
            e.printStackTrace();
        }*/

        LocalDate currDate = LocalDate.now();
        System.out.println(currDate);

        System.out.println(String.valueOf(currDate).equals(q1) + " 1");
        System.out.println(String.valueOf(currDate).equals(q2) + " 2");
        System.out.println(String.valueOf(currDate).equals(q3) + " 3");
        System.out.println(String.valueOf(currDate).equals(q4) + " 4");


        //finds and saves the quarterly winners as required
        if(String.valueOf(currDate).equals(q1)){
            saveWinners(PointSystem.quarterWinners(), "quarter1.txt");
        } else if (String.valueOf(currDate).equals(q2)){
            saveWinners(PointSystem.quarterWinners(), "quarter2.txt");
        } else if (String.valueOf(currDate).equals(q3)){
            saveWinners(PointSystem.quarterWinners(), "quarter3.txt");
        } else if (String.valueOf(currDate).equals(q4)){
            saveWinners(PointSystem.quarterWinners(), "quarter4.txt");
        }
   
        //setup the frame
        JFrame frame = new JFrame("Home Page");
        Main.setMainFrame(frame);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(1280, 720);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        frame.setBackground(Color.white);

        //titlebar
        JPanel titlebar = new JPanel();
        titlebar.setBackground(Color.decode("#76BEE8"));
        titlebar.setLayout(new BorderLayout());
        titlebar.setBorder(new EmptyBorder(10, 10, 10, 80));
        titlebar.setBounds(0, 0, 1280, 60);
        titlebar.add(Common.getImage("logo_small.png"), BorderLayout.EAST);
        frame.add(titlebar);
        
        JTextArea prize1 = new JTextArea("Between 0 - 59 points: \nPencil!");
        prize1.setWrapStyleWord(true);
        prize1.setLineWrap(true);
        prize1. setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        prize1. setForeground(Color.white);
        prize1.setBounds(25, 120, 200, 75);
        prize1.setBackground(Color.decode("#3E3F40"));

        frame.add(prize1);

        JTextArea prize2 = new JTextArea("Between 60 - 119 points: \n$15 Starbucks Giftcard!");
        prize2.setWrapStyleWord(true);
        prize2.setLineWrap(true);
        prize2.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        prize2.setForeground(Color.white);
        prize2.setBackground(Color.decode("#3E3F40"));
        prize2.setBounds(25, 240, 200, 75);

        frame.add(prize2);

        JTextArea prize3 = new JTextArea("Between 120 - 199 points: \n$25 Starbucks Giftcard!");
        prize3.setWrapStyleWord(true);
        prize3.setLineWrap(true);
        prize3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        prize3.setForeground(Color.white);
        prize3.setBounds(25, 360, 200, 75);
        prize3.setBackground(Color.decode("#3E3F40"));

        frame.add(prize3);

        JTextArea prize4 = new JTextArea("200+ Points: \nFree day off from school!");
        prize4.setWrapStyleWord(true);
        prize4.setLineWrap(true);
        prize4.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
        prize4.setForeground(Color.white);
        prize4.setBackground(Color.decode("#3E3F40"));
        prize4.setBounds(25, 480, 200, 75);

        frame.add(prize4); 
       
        //sidebar
        JPanel sidebar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        sidebar.setBorder( new EmptyBorder(15, 15, 15, 15));
        sidebar.setBackground(Color.decode("#3E3F40"));
        sidebar.setBounds(0, 60, 300, 720);
        JLabel stats = new JLabel("Prizes");
        stats.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 23));
        stats.setForeground(Color.white);
        sidebar.add(stats);
        frame.add(sidebar);

        //section that presents all the events + event info
        JLabel title1 = new JLabel("Current Events");
        title1.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title1.setForeground(Color.gray);
        title1.setBounds(325, 70, 150, 50);
        frame.add(title1);

        JPanel allEvents = new JPanel();
        allEvents.setLayout(new BoxLayout(allEvents, BoxLayout.Y_AXIS));
        allEvents.setBackground(Color.white);
        allEvents.setBorder(new EmptyBorder(15, 15, 15, 15));
        JScrollPane eventsPane = new JScrollPane(allEvents, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        eventsPane.setBounds(320, 110, 490, 555);
        eventsPane.getVerticalScrollBar().setUnitIncrement(15);
        
        //gets information from each event in the events list and adds it all to a single panel
        for (int i = 0; i < Event.eventList.size(); i++) {
            //creates a mini panel to hold all the info about a specific event
            JPanel eventInfoPane = new JPanel();
            eventInfoPane.setLayout(new BoxLayout(eventInfoPane, BoxLayout.Y_AXIS));
            eventInfoPane.setBackground(Color.white);
            eventInfoPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            eventInfoPane.setBorder(BorderFactory.createLineBorder(Color.gray));
            eventInfoPane.setMaximumSize(new Dimension(480, 220));

            //holds event points
            JTextField eventPointsInfo = new JTextField("WORTH " + Event.eventList.get(i).getPoints() + " POINTS!"); //gets the points the event is worth
            eventPointsInfo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 17));
            eventPointsInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventPointsInfo.setEditable(false);
            eventPointsInfo.setBackground(Color.decode("#76BEE8"));
			eventPointsInfo.setForeground(Color.white);
            eventPointsInfo.setBorder(new EmptyBorder(8, 5, 5, 0));

            //holds event name
            JTextField eventNameInfo = new JTextField(Event.eventList.get(i).getName()); //gets the event name
            eventNameInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            eventNameInfo.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventNameInfo.setEditable(false);
            eventNameInfo.setBackground(Color.white);
            eventNameInfo.setBorder(new EmptyBorder(8, 5, 0, 0));
            
            //holds the event description
            JTextArea eventDescInfo = new JTextArea(Event.eventList.get(i).getDesc());
            eventDescInfo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventDescInfo.setForeground(Color.gray);
            eventDescInfo.setEditable(false);
            eventDescInfo.setLineWrap(true);
            eventDescInfo.setWrapStyleWord(true);
            eventDescInfo.setBorder(new EmptyBorder(0, 5, 3, 0));

            //holds the event location
            JTextField eventLocation = new JTextField(Event.eventList.get(i).getLocation());
            eventLocation.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventLocation.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventLocation.setEditable(false);
            eventLocation.setBackground(Color.white);
            eventLocation.setBorder(new EmptyBorder(0, 5, 1, 0));

            //holds the event date, and time
			JTextField eventSetting = new JTextField(Event.eventList.get(i).getDate() //gets the event date
                                                    + "  @ " + Event.eventList.get(i).getTime()); //gets the event time
            eventSetting.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
            eventSetting.setBorder(javax.swing.BorderFactory.createEmptyBorder());
            eventSetting.setEditable(false);
            eventSetting.setBackground(Color.white);
            eventSetting.setBorder(new EmptyBorder(0, 5, 5, 0));

            //register in an event
            JButton register = new JButton("Register"); 
            register.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            register.setBackground(Color.decode("#76BEE8"));
            register.setOpaque(true);
            register.setCursor(new Cursor(Cursor.HAND_CURSOR));
            JTextPane registerPane = new JTextPane();
            registerPane.insertComponent(register);
            registerPane.setBackground(Color.white);
            registerPane.setBorder(new EmptyBorder(0, 5, 8, 0));
            int idx = i;
            register.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int result = JOptionPane.showConfirmDialog(frame, "Would you like to register for this event?", 
														"",
															JOptionPane.YES_OPTION,
															JOptionPane.QUESTION_MESSAGE);
					if (result == JOptionPane.YES_OPTION) {
                        int curPoints = curUser.getPoints();
                        curPoints += Event.eventList.get(idx).getPoints();
                        curUser.setPoints(curPoints);
					}
				}
			});
            eventInfoPane.add(eventPointsInfo);
			eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
			eventInfoPane.add(eventNameInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 5)));
            eventInfoPane.add(eventDescInfo);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(eventLocation);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(eventSetting);
            eventInfoPane.add(Box.createRigidArea(new Dimension(0, 6)));
            eventInfoPane.add(registerPane);
            eventInfoPane.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(eventInfoPane);
            allEvents.setAlignmentX(Component.LEFT_ALIGNMENT);
            allEvents.add(Box.createRigidArea(new Dimension(0, 20)));
        };
        frame.add(eventsPane);

        //leaderboard panel that shows an updated leaderboard everytime the page is opened
        JLabel title2 = new JLabel("Current Leaderboard");
        title2. setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title2. setForeground(Color.gray);
        title2.setBounds(825, 70, 200, 50);
        frame.add(title2);

        JPanel leaderboard = new JPanel();
        leaderboard.setLayout(new BoxLayout(leaderboard, BoxLayout.Y_AXIS));
        leaderboard.setBackground(Color.decode("#F66845"));
        leaderboard.setBounds(825, 110, 420, 250);
        leaderboard.setBorder( new EmptyBorder(15, 15, 15, 15));

        JTextArea nineWinner = new JTextArea("Grade 9: " + PointSystem.nineWinners().get(0).getName());
        for (int i = 1; i < PointSystem.nineWinners().size(); i++) {
            if (PointSystem.nineWinners().size() == 1) {
                continue;
            } else {
                nineWinner.setText(nineWinner.getText() + ", " + (PointSystem.nineWinners().get(i)).getName());
            } 
        }
        nineWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        nineWinner.setBackground(Color.decode("#F66845"));
        nineWinner.setForeground(Color.white);
        nineWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        nineWinner.setEditable(false);
        nineWinner.setLineWrap(true);
        nineWinner.setWrapStyleWord(true);
        leaderboard.add(nineWinner);

        JTextArea tenWinner = new JTextArea("Grade 10: " + PointSystem.tenWinners().get(0).getName());
        for (int i = 1; i < PointSystem.tenWinners().size(); i++) {
            if (PointSystem.tenWinners().size() == 1) {
                continue;
            } else {
                tenWinner.setText(tenWinner.getText() + ", " + (PointSystem.tenWinners().get(i)).getName());
            } 
        }
        tenWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        tenWinner.setBackground(Color.decode("#F66845"));
        tenWinner.setForeground(Color.white);
        tenWinner.setEditable(false);
        tenWinner.setLineWrap(true);
        tenWinner.setWrapStyleWord(true);
        tenWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(tenWinner);

        JTextArea elevenWinner = new JTextArea("Grade 11: " + PointSystem.elevenWinners().get(0).getName());
        for (int i = 1; i < PointSystem.elevenWinners().size(); i++) {
            if (PointSystem.elevenWinners().size() == 1) {
                continue;
            } else {
                elevenWinner.setText(elevenWinner.getText() + ", " + (PointSystem.elevenWinners().get(i)).getName());
            } 
        }
        elevenWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        elevenWinner.setBackground(Color.decode("#F66845"));
        elevenWinner.setForeground(Color.white);
        elevenWinner.setEditable(false);
        elevenWinner.setLineWrap(true);
        elevenWinner.setWrapStyleWord(true);
        elevenWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(elevenWinner);

        JTextArea twelveWinner = new JTextArea("Grade 12: " + PointSystem.twelveWinners().get(0).getName());
        for (int i = 1; i < PointSystem.twelveWinners().size(); i++) {
            if (PointSystem.twelveWinners().size() == 1) {
                continue;
            } else {
                twelveWinner.setText(twelveWinner.getText() + ", " + (PointSystem.twelveWinners().get(i)).getName());
            } 
        }
        twelveWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        twelveWinner.setBackground(Color.decode("#F66845"));
        twelveWinner.setForeground(Color.white);
        twelveWinner.setEditable(false);
        twelveWinner.setLineWrap(true);
        twelveWinner.setWrapStyleWord(true);
        twelveWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(twelveWinner);

        JTextArea randomWinner = new JTextArea("Random Winners: " + PointSystem.randomWinners().get(0).getName());
        for (int i = 1; i < PointSystem.randomWinners().size(); i++) {
            if (PointSystem.randomWinners().size() == 1) {
                continue;
            } else {
                randomWinner.setText(randomWinner.getText() + ", " + (PointSystem.randomWinners().get(i)).getName());
            } 
        }
        randomWinner.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        randomWinner.setBackground(Color.decode("#F66845"));
        randomWinner.setForeground(Color.white);
        randomWinner.setEditable(false);
        randomWinner.setLineWrap(true);
        randomWinner.setWrapStyleWord(true);
        randomWinner.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        leaderboard.add(randomWinner);

        frame.add(leaderboard);

        //leaderboard panel that shows the final winners at the end of the quarter or from the previous quarter
        JLabel title3 = new JLabel("Quarterly Leaderboard");
        title3.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
        title3.setForeground(Color.gray);
        title3.setBounds(825, 375, 300, 50);
        frame.add(title3);

        JPanel leaderboardFinal = new JPanel();
        leaderboardFinal.setLayout(new BoxLayout(leaderboardFinal, BoxLayout.Y_AXIS));
        leaderboardFinal.setBackground(Color.decode("#F66845"));
        leaderboardFinal.setBounds(825, 415, 420, 250); 
        leaderboardFinal.setBorder(new EmptyBorder(15, 15, 15, 15));
        
        //only generate winners for this leaderboard if it is the end of the quarter
        JTextArea msg = new JTextArea();
        msg.setBackground(Color.decode("#F66845"));
        msg.setForeground(Color.white);
        msg.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        msg.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        msg.setEditable(false);
        msg.setLineWrap(true);
        msg.setWrapStyleWord(true);

        System.out.println();
        System.out.println((String.valueOf(currDate).equals(q1)) || (currDate.isAfter(quarter1) && currDate.isBefore(quarter2)));
        System.out.println((String.valueOf(currDate).equals(q2)) || (currDate.isAfter(quarter2) && currDate.isBefore(quarter3)));
        System.out.println((String.valueOf(currDate).equals(q3)) || (currDate.isAfter(quarter3) && currDate.isBefore(quarter4)));
        System.out.println(String.valueOf(currDate).equals(q4));


        @SuppressWarnings("unchecked")
        ArrayList<String>[] s = new ArrayList[4];
        if(quarter1 == null || quarter2 == null || quarter3 == null || quarter4 == null){
            msg.append("Could not find winners. Check again later.");
            leaderboardFinal.add(msg);
        } else if(quarter1 != null && quarter2 != null && quarter3 != null && quarter4 != null) {
            System.out.println("in here");
            if(String.valueOf(currDate).equals(q1) || (currDate.isAfter(quarter1) && currDate.isBefore(quarter2))) {
                System.out.println("in q1");
                s = readWinners("quarter1.txt");
                writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(currDate).equals(q2) || (currDate.isAfter(quarter2) && currDate.isBefore(quarter3))) {
                System.out.println("in q2");
                s = readWinners("quarter2.txt");
                writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(currDate).equals(q3) || (currDate.isAfter(quarter3) && currDate.isBefore(quarter4))) {
                System.out.println("in q3");
                s = readWinners("quarter3.txt");
                System.out.println("ouii");
                writeWinners(msg, s);
                leaderboardFinal.add(msg);

            } else if(String.valueOf(currDate).equals(q4)) {
                System.out.println("in q4");
                s = readWinners("quarter4.txt");
                writeWinners(msg, s);
                leaderboardFinal.add(msg);
            
            } else if(currDate.isAfter(quarter4)) {
                msg.append("All winners have been selected for this year.");
                leaderboardFinal.add(msg);
            } else if(currDate.isBefore(quarter1)) {
                msg.append("No one winners have been selected yet. Check again later.");
                leaderboardFinal.add(msg);
            }
           
        } else {
            JOptionPane.showConfirmDialog(frame, "  Quarterly dates incorrectly entered or not saved. Please input quarterly end dates. ", 
								                    "Information Input Error",
															JOptionPane.OK_CANCEL_OPTION);
            new AdminView();
        }

        frame.add(leaderboardFinal);
        frame.setVisible(true);
    }
    
    protected static void saveUser() throws IOException {
		out = new BufferedWriter(new FileWriter("data\\students.txt"));
		
		for(Student s : Student.getStudents()) {
			out.write(s.getName() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getEmail() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getPassword() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getGrade() + " ");
		}
		out.newLine();
		for(Student s : Student.getStudents()) {
			out.write(s.getPoints() + " ");
		}
		out.newLine();
        for(Student s : Student.getStudents()) {
			out.write(s.getSalt() + " ");
			try (FileOutputStream fos = new FileOutputStream("data\\salts\\studentSalts.txt")) {
				fos.write(s.getSalt());
				//fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
			 }
		}
		out.newLine();
		out.close();
    }

    protected static void writeWinners(JTextArea ta, ArrayList<String>[] w){
        for(int i = 0; i < 4; i++){
            ta.append("Grade " + (i+9) + ": ");
            for(String winners : w[i]){
                ta.append(winners);
            }
            ta.append("\n\n");
        }
    }

    protected static void saveWinners(java.util.List<Student>[] winners, String f) throws IOException {
        out = new BufferedWriter(new FileWriter("data\\quarterlyWinners" + File.separator + f));
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < winners[i].size(); j++){
                //out.write((winners[i].get(j)).toString() + " ");
                out.write(Student.toString((winners[i].get(j))));
                out.newLine();
            }
        }
        out.close();
    }

    protected ArrayList<String>[] readWinners(String f) throws IOException {
        in = new BufferedReader(new FileReader("data\\quarterlyWinners" + File.separator + f));
        @SuppressWarnings("unchecked")
        ArrayList<String>[] winners = new ArrayList[4];
        String[] student;
        String stud = new String();

        //initializing winners array
        for (int i = 0; i < 4; i++) { 
            winners[i] = new ArrayList<String>(); 
        }
        ArrayList<String> wins9 = new ArrayList<String>();
        ArrayList<String> wins10 = new ArrayList<String>();
        ArrayList<String> wins11 = new ArrayList<String>();
        ArrayList<String> wins12 = new ArrayList<String>();

        while((stud = in.readLine()) != null){
            try {
                student = stud.split("\\|");
                if(student[1].equals("9")){
                    if(wins9.size() > 0){
                        wins9.add(", " + student[0]);
                    } else {
                        wins9.add(student[0]);
                    }
                } else if(student[1].equals("10")){
                    if(wins10.size() > 0){
                        wins10.add(", " + student[0]);
                    } else {
                        wins10.add(student[0]);
                    }
                } else if(student[1].equals("11")){
                    if(wins11.size() > 0){
                        wins11.add(", " + student[0]);
                    } else {
                        wins11.add(student[0]);
                    }
                } else if(student[1].equals("12")){
                    if(wins12.size() > 0){
                        wins12.add(", " + student[0]);
                    } else {
                        wins12.add(student[0]);
                    }
                } else {
                    System.out.println("Student does not have a registered grade.");
                }
            } catch (NullPointerException e1){
                e1.printStackTrace();
            }
        }
        
        for(String h : wins9){
            winners[0].add(h);
        }
        for(String h : wins10){
            winners[1].add(h);
        }
        for(String h : wins11){
            winners[2].add(h);
        }
        for(String h : wins12){
            winners[3].add(h);
        }

        in.close();
        return winners;
    }
}
