import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;




public class Calculator_2
{
    
    public static void createAndShowGUI()
    {
     JFrame jf = new JFrame("Calculator");
     
     
     
     
     //JTextArea t = new JTextArea("0");
     
     JTextField t = new JTextField("0");
     t.setHorizontalAlignment(JTextField.RIGHT);
     t.setEditable(false);
     ActionListener myact = new ActionListener()
     {
        public String zero(String input)
        {
            try
            {
                if(input.isEmpty())
                {
                    return "";
                }
                if(Double.parseDouble(input) % 1 == 0)
                {
                    input = String.valueOf((long)Double.parseDouble(input));
                }
                else
                {
                    input = String.valueOf(Double.parseDouble(input));
                }
                return input;
            }
            catch(Exception e)
            {
                return "ERROR: Divided by 0";
            }
        }
        public String doSomething(String a, String b, String symbol)
        {
            
            if(a.equals("")&&b.equals(""))
            {
                return "0";
            }
            if(a.equals(""))
            {
                return b;
            }
            if(b.equals(""))
            {
                return a;
            }
            switch(symbol)
            {
                case "+":
                return String.valueOf(Double.parseDouble(b)+Double.parseDouble(a));
                case "-":
                return String.valueOf(Double.parseDouble(b)-Double.parseDouble(a));
                case "*":
                return String.valueOf(Double.parseDouble(b)*Double.parseDouble(a));
                case "/":
                if(Double.parseDouble(a) == 0)
                {
                    return "ERROR: Divided by 0";
                }
                return String.valueOf(Double.parseDouble(b)/Double.parseDouble(a));
                
                
                
                default:
                return a; //bruh
                
            }



            
        }
        String input = "";
        String old_input = "";
        final String operators = "+-*/C=";
        String operator = "";
        boolean lastEq = false;
        final private String form = "%40s";
        

        

        public void actionPerformed(ActionEvent  e)
        {
            if(operators.contains(e.getActionCommand()))
            {
                if(e.getActionCommand().equals("C"))
                {
                    input = "0";
                    old_input = "";
                    operator = "";
                    lastEq = false;
                    input = String.format(form, input);
                    t.setText(input);
                
                }
                else
                {
                    if(lastEq && !e.getActionCommand().equals("="))
                    {
                        operator = "";
                        lastEq = false;
                        input = "";
                    }
                    else if(input.equals("") && e.getActionCommand().equals("=") && !operator.equals(""))
                    {
                        input = old_input;
                    }
                    old_input = String.format(form, zero(doSomething(input, old_input, operator)));
                    t.setText(old_input);

                    if(e.getActionCommand().equals("="))
                    {
                        lastEq = true;
                    }
                    else
                    {
                        operator = e.getActionCommand();
                        input = "";
                    }
                    if(old_input.equals(String.format(form, "ERROR: Divided by 0")))
                    {
                        input = "0";
                        old_input = "";
                        operator = "";
                        lastEq = false;
                    }
                }
                    
            }
            else
            {
                if(lastEq)
                {
                    input = "";
                    old_input = "";
                    operator = "";
                    lastEq = false;
                }
                input+=e.getActionCommand();
                input = String.format(form, zero(input));
                t.setText(input);
                
            }
            System.out.println("a: " + zero(input) + "  b: " + zero(old_input) + "  operator: " + operator);
            
            
            
            
            
        }
     };
     
     Font f1 = new Font("ARIAL",Font.BOLD, 30);
     Font f = new Font("ARIAL",0, 30);
     t.setFont(f1);
     
     
     
     JPanel jp1 = new JPanel();
     
     jf.getContentPane().add(jp1);

     JButton [] jb = new JButton[16];
     jb[0] = new JButton("1");jb[1] = new JButton("2");jb[2] = new JButton("3");jb[3] = new JButton("+");
     jb[4] = new JButton("4");jb[5] = new JButton("5");jb[6] = new JButton("6");jb[7] = new JButton("-");
     jb[8] = new JButton("7");jb[9] = new JButton("8");jb[10] = new JButton("9");jb[11] = new JButton("*");
     jb[12] = new JButton("0");jb[13] = new JButton("="); jb[14] = new JButton("C");jb[15] = new JButton("/");
     jf.getContentPane().add(t, BorderLayout.NORTH);

     

     for(int i = 0; i<jb.length;i++)
     {
        jb[i].setFont(f);
        jb[i].addActionListener(myact);
        jp1.add(jb[i]);
     }
     
     jp1.setLayout(new GridLayout(4,4));

     jf.setPreferredSize(new Dimension(400,300));
     jf.pack();
     jf.setResizable(false);
     jf.setLocationRelativeTo(null);
     //jf.setComponentOrientation(null);
     jf.setVisible(true);
     jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    }
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run(){createAndShowGUI();}
        });
        
    }
    
}
