package com.mycompany.todolist_system;

import java.awt.*;
import java.awt.event.*;

public class ToDoList_system implements ActionListener {
    
    private static Dialog dialog;  
    private Frame Login, todolist;
    private Label login, user, pass, messageLabel;
    private Button btn_login, addTaskButton, clearAllButton, logout;
    private TextField password, username;
    private Panel taskPanel, controlPanel;
    private int taskCount = 0;
    
    public ToDoList_system() {
        
        // LOG-IN PAGE
        
        dialog = new Dialog(Login , "ERROR!", true);  
        Login = new Frame();
        
        Font font = new Font("Helvetica", Font.BOLD, 40);
        Font font1 = new Font("Helvetica", Font.BOLD, 15);
        Font font2 = new Font("Helvetica", Font.PLAIN, 15);
        
        login = new Label();
        login.setFont(font);
        login.setText("LOG-IN FORM");   
        
        user = new Label();
        user.setText("USERNAME:");
        user.setFont(font1);
        
        messageLabel = new Label("", Label.CENTER);
        
        username = new TextField();
        username.setPreferredSize(new Dimension(300,30));
          
        pass = new Label();
        pass.setText("PASSWORD:");
        pass.setFont(font1);
        
        password = new TextField();
        password.setPreferredSize(new Dimension(300,30));
        password.setEchoChar('*'); 
        
        btn_login = new Button("LOG-IN");
        btn_login.addActionListener(this); // Registering ActionListener
     
        btn_login.setPreferredSize(new Dimension(100,27));
        btn_login.setFont(font2);
     
        Login.setSize(400, 600);
        Login.setLayout(new FlowLayout(FlowLayout.CENTER));
        Login.setResizable(false);
        Login.setTitle("LOG-IN");
        
        Login.add(login);
        Login.add(user);
        Login.add(username);
        Login.add(pass);
        Login.add(password);
        Login.add(btn_login);
        Login.add(messageLabel);
      
        
        // TO-DO-LIST PAGE
        
        todolist = new Frame();
        todolist.setLayout(new BorderLayout());
        todolist.setTitle("STAY PRODUCTIVE");
        todolist.setSize(400, 600);

        Panel titlePanel = new Panel();
        Label title = new Label("StayProductive", Label.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(title);
        todolist.add(titlePanel, BorderLayout.NORTH);

        taskPanel = new Panel();
        taskPanel.setLayout(new GridLayout(0, 1, 1, 1));
        taskPanel.setBackground(Color.WHITE);
        todolist.add(taskPanel, BorderLayout.CENTER);

        controlPanel = new Panel();
        addTaskButton = new Button("Add Task");
        clearAllButton = new Button("Clear All Tasks");
        logout = new Button("Log out");
        controlPanel.add(addTaskButton);
        controlPanel.add(clearAllButton);
        controlPanel.add(logout);
        todolist.add(controlPanel, BorderLayout.SOUTH);

      
        
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hide the initial frame
                todolist.setVisible(false);

                // Show the secondary frame
                Login.setVisible(true);
                
                 username.setText("");
                 password.setText("");
            }
        });
        
        addTaskButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        clearAllButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearTasks();
            }
        });

    
        Login.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        todolist.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

     
        Login.setVisible(true);
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String userText = username.getText(); 
        String passText = password.getText();
        
     
        if (userText.equals("admin") && passText.equals("admin")) {

            Login.setVisible(false);
            todolist.setVisible(true);
            
        } else {
            
        Font font = new Font("Helvetica", Font.BOLD, 13);
        
        dialog.setLayout( new FlowLayout() );  
        Button b = new Button ("OK");  
        b.addActionListener ( new ActionListener()  
        {  
            public void actionPerformed( ActionEvent e )  
            {  
                ToDoList_system.dialog.setVisible(false);  
            }  
        });  
        
        dialog.add( new Label ("INVALID!! PLEASE TRY AGAIN!"));  
        dialog.setFont(font);
        dialog.add(b);   
        dialog.setSize(250,150);    
        dialog.setVisible(true);  
                   
        }
        
        
    }
   

    private void addTask() {
        taskCount++;
        TextField taskField = new TextField("Enter task", 25);
        Button doneButton = new Button("Done");
        Button removeButton = new Button("Remove");
   
        
        Panel taskEntryPanel = new Panel();
        taskEntryPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        taskEntryPanel.add(new Label(String.valueOf(taskCount)));
        taskEntryPanel.add(taskField);
        taskEntryPanel.add(doneButton);
        taskEntryPanel.add(removeButton);
  

        taskPanel.add(taskEntryPanel);
        taskPanel.revalidate();
        
    

        doneButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskField.setText("Task Completed");
                taskField.setEditable(false);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskPanel.remove(taskEntryPanel);
                taskPanel.revalidate();
                taskPanel.repaint();
            }
        });
    }

    private void clearTasks() {
        taskPanel.removeAll();
        taskPanel.revalidate();
        taskPanel.repaint();
        taskCount = 0;
    }
        
    public static void main(String[] args) {
       new ToDoList_system();
    }
}