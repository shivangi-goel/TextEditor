import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.Element;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hp
 */
public class TextEditor extends JFrame implements ActionListener,ItemListener{
    //Declaring Global variables
    private Object highlightTag;
    JTextPane E=new JTextPane();
    Font myFont=new Font("Tahoma",Font.PLAIN,16);
    Color co=new Color(242, 242,242);
    boolean bx=true;
    JPanel wc=new JPanel();
    JLabel jl=new JLabel();
    String fs="Times New Roman";
    int i,jj=26;
    JFrame js;
    int X,Y,X1,Y1;             
    MutableAttributeSet asNew ;
    DefaultHighlightPainter highlightPainter =new DefaultHighlightPainter(Color.yellow);
    //Creating Constructor for the textEditor
    TextEditor(){
        
        JButton Shape=new JButton("Shape");
        
        
        //Declaring and creating MenuBar and MenuItems
        JMenuBar mb=new JMenuBar();
        JMenuItem f1=new JMenuItem("New                         ");
        JMenuItem f2=new JMenuItem("Open                        ");
        JMenuItem f3=new JMenuItem("Save                        ");
        JMenuItem f5=new JMenuItem("Close                       ");
        JMenuItem e1=new JMenuItem("Cut                         ");
        JMenuItem e2=new JMenuItem("Copy                        ");
        JMenuItem e3=new JMenuItem("Paste                       ");
        JMenuItem e4=new JMenuItem("Find                        ");
        JMenuItem e5=new JMenuItem("Replace                     ");
        JMenu m1=new JMenu("File");
        JMenu m2=new JMenu("Edit");
        JPanel j=new JPanel();
        mb.setBackground(Color.white);
        
        //Declaring and creating Toolbar
        JToolBar tb=new JToolBar();
        JLabel l1=new JLabel("Font Style");
        JLabel l2=new JLabel("Size");
        l1.setFont(myFont);
        l2.setFont(myFont);
        tb.setPreferredSize(new Dimension(100,38));
        tb.setLayout(new FlowLayout(0, 0, 0));
        tb.setBackground(co);
        
        GraphicsEnvironment e=GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontnames=e.getAvailableFontFamilyNames();
        
        //Declaring and creating ScrollPane
        JScrollPane sc=new JScrollPane(E);
        sc.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sc.setPreferredSize(new Dimension(250, 145));
        sc.setMinimumSize(new Dimension(10, 10));
        
        //Creating choice and adding fontnames
        Choice c=new Choice();
        for(int i=0;i<fontnames.length;i++)
        c.add(fontnames[i]);
        
        //Adding Menuitems in the MenuBar
        mb.add(m1);
        mb.add(m2);
        add(mb);
        setTitle("TextEditor");
        
        //Creating choice for font size
        Choice cf=new Choice();
        cf.add("8");
        cf.add("9");
        cf.add("10");
        cf.add("11");
        cf.add("12");
        cf.add("14");
        cf.add("16");
        cf.add("18");
        cf.add("20");
        cf.add("24");
        cf.add("36");
        cf.add("48");
        cf.add("72");
        cf.add("84");
        cf.add("96");
        cf.add("100");
        
        
        
        
        
        
        //Adding itemListener to the choice
        cf.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                StyledDocument doc = (StyledDocument) E.getDocument();
                int selectionEnd = E.getSelectionEnd();
                int selectionStart = E.getSelectionStart();
                if (selectionStart == selectionEnd) {
                    return;
                }
                Element element = doc.getCharacterElement(selectionStart);
                AttributeSet as = element.getAttributes();
                asNew = new SimpleAttributeSet(as.copyAttributes());
                
                String size=e.getItem().toString();
                jj=Integer.parseInt(size);
                StyleConstants.setFontFamily(asNew, fs);    
                StyleConstants.setFontSize(asNew,jj);
                
                doc.setCharacterAttributes(selectionStart, E.getSelectedText().length(), asNew, true);
            }
        });
        
        //Creating Buttons for case
        JButton A=new JButton("A");
        JButton a=new JButton("a");
        A.setFont(myFont);
        a.setFont(myFont);
        
        
        //Adding Buttons for Changing Case
        A.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                String s=E.getSelectedText();
                String temp=s.toUpperCase();
                E.replaceSelection(temp);
           }
        });
        
        //Adding Buttons for Changing Case
        a.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                String s=E.getSelectedText();
                String temp=s.toLowerCase();
                E.replaceSelection(temp);
            }
        });
        
        
        j.setBackground(co);
        //Function for Word count and character Count
        E.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                jl.setText("");
                
            }

            @Override
            public void mousePressed(MouseEvent e) {}

            @Override
            public void mouseReleased(MouseEvent e) {
                if(E.getSelectedText()!=null){
                String s=E.getSelectedText();
                String words[]=s.split("\\s");
                jl.setText("Words: "+words.length+" Characters: "+s.length());
                       
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });
        
        //Adding Components to the Panel
        j.add(Shape);
         j.add(A);
        j.add(a);
        j.add(l1);
        j.add(c);
        j.add(l2);
        j.add(cf);
        
       
        
        Shape.setFont(myFont);
        
        //Adding ActionListener to the Shape Panel
        Shape.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                js=new JFrame();
                js.setSize(800, 700);
                js.setVisible(true);
                js.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                js.add(new ButtonPanel());
            }
        });
        tb.add(j);
        add(tb,BorderLayout.PAGE_START);
        wc.add(jl);
        add(wc,BorderLayout.PAGE_END);
        
        //Setting the font to the components
        f1.setFont(myFont);
        f2.setFont(myFont);
        f3.setFont(myFont);
        f5.setFont(myFont);
        e1.setFont(myFont);
        e2.setFont(myFont);
        e3.setFont(myFont);
        e4.setFont(myFont);
        e5.setFont(myFont);
        m1.setFont(myFont);
        m2.setFont(myFont);
        
        add(sc);
        c.addItemListener(this);
        m1.add(f1);
        m1.add(f2);
        m1.add(f3);
        m1.add(f5);
        
        f1.addActionListener(this);
        f2.addActionListener(this);
        f3.addActionListener(this);
        f5.addActionListener(this);
        E.setFont(new Font("Times New Roman",Font.PLAIN,26));
        m2.add(e1);
        m2.add(e2);
        m2.add(e3);
        m2.add(e4);
        m2.add(e5);
        e1.addActionListener(this);
        e2.addActionListener(this);
        e3.addActionListener(this);
        e4.addActionListener(this);
        e5.addActionListener(this);
        this.setJMenuBar(mb);
        
        //Setting Properties of the frame
        setSize(1000, 800);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String arg[]){
    //Creating TextEditor
        TextEditor e=new TextEditor();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    String s=e.getActionCommand();
    //Adding Functions for the MenuItem 
    if(s=="New                         ")
        {
            TextEditor n=new TextEditor();
            n.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            
            
        }
    else if(s=="Open                        "){ 
	JFileChooser j = new JFileChooser("f:"); 
        int r = j.showOpenDialog(null); 
	if (r == JFileChooser.APPROVE_OPTION) {
            File fi = new File(j.getSelectedFile().getAbsolutePath()); 
                try {  
		String s1 = "", sl = "";  
                FileReader fr = new FileReader(fi);
		BufferedReader br = new BufferedReader(fr);
		sl = br.readLine(); 
		while ((s1 = br.readLine()) != null) { 
		sl = sl + "\n" + s1; 
		}
		E.setText(sl); 
                } 
		catch (Exception evt) { 
		System.out.println("Try Again");
		} 
	}
	else
	     System.out.println("Try Again");
        } 
    else if(s=="Save                        ") { 			
	JFileChooser j = new JFileChooser("d:");			
	int r = j.showSaveDialog(null);
	if (r == JFileChooser.APPROVE_OPTION) { 				
        File fi = new File(j.getSelectedFile().getAbsolutePath()); 
	try { 
            FileWriter wr = new FileWriter(fi, false);
            BufferedWriter w = new BufferedWriter(wr); 
            w.write(E.getText()); 
            w.flush(); 
            w.close(); 
	} 
        catch (Exception evt) { 
	    System.out.println("Try Again");
	} 
	}else System.out.println("Try Again");
    } 
    else if(s=="Close                       ")
        {
            dispose();
        }
    else if(s=="Cut                         ")
        {
            E.cut();
        }
    else if(s=="Copy                        ")
        {
            E.copy();
        }   
    else if(s=="Paste                       ")
        {
            E.paste();
        }
    else if(s=="Find                        ")
        {
            JFrame fi=new JFrame("Find");
            JButton b=new JButton("Find");
            JButton b1=new JButton("Find Next");
            JPanel p1=new JPanel();
            JTextField tf=new JTextField();
            JLabel l=new JLabel("Find What");
            tf.setColumns(100);
            fi.setSize(400, 150);
            fi.setVisible(true);
            
            b1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                E.getHighlighter().removeHighlight(highlightTag);
                Document d = E.getDocument();                    
                        try{
                            String find = tf.getText();
                            bx=true;
                            while(i+find.length()-1<d.getLength() && bx)
                            {
                                String match=d.getText(i,find.length());
                                
                                if(find.equals(match)){
                                    
                                   highlightTag= E.getHighlighter().addHighlight(i, i+find.length(), highlightPainter);
                                    bx=false;
                            }
                            i++;
                                    
                            }
                            
                        }
                        catch(BadLocationException ex)
                        {
                            System.out.println("Exception occured");
                        }
                
                }
            });
            
        
            //Adding components and setting font 
            fi.add(p1);
            l.setFont(myFont);
            b.setFont(myFont);
            tf.setColumns(10);
            p1.add(l);
            p1.add(tf);
            p1.add(b);
            b1.setFont(myFont);
            p1.add(b1);
            
            
            
           
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                     
                    Document d = E.getDocument();                    
                        try{
                            String find = tf.getText();
                            bx=true;
                            while(i+find.length()-1<d.getLength() && bx)
                            {
                                String match=d.getText(i,find.length());
                                System.out.println(match);
                                if(find.equals(match)){
                                    
                                   highlightTag= E.getHighlighter().addHighlight(i, i+find.length(), highlightPainter);
                                    bx=false;
                            }
                            i++;
                                    
                            }
                            
                        }
                        catch(BadLocationException ex)
                        {
                            System.out.println("Exception occured");
                        }
                   
                }
            });      
    }
        else if(s=="Replace                     ")
        {
           JFrame rp=new JFrame("Replace");
           JTextField tf=new JTextField();
           JTextField tf1=new JTextField();
           JLabel jl=new JLabel("Replace What");
           JLabel jl1=new JLabel("Replace with");
           JButton b1=new JButton("Replace");
           JButton b2=new JButton("Replace All");
           JPanel j1=new JPanel();
           JPanel j2=new JPanel();
           rp.setLayout(new GridLayout(2,0));
           j1.setLayout(new FlowLayout(0, 20, 5));
           j1.add(jl);
           tf.setColumns(10);
           j1.add(tf);
           j1.add(b1);
           j2.setLayout(new FlowLayout(0,20,5));
           j2.add(jl1);
           tf1.setColumns(10);
           j2.add(tf1);
           j2.add(b2);
           rp.add(j1);
           rp.add(j2);
           rp.setVisible(true);
           rp.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
           rp.setSize(500, 125);
           b1.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                    StringBuffer s=new StringBuffer(E.getText());
                    int begin=s.indexOf(tf.getText(),0);
                    int end=begin+tf.getText().length();
                    s.replace(begin, end, tf1.getText());
                    E.setText(s.toString());
                                  }
           });
           b2.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {                
                  
                    String s=new String(E.getText());
                    String temp=s.replaceAll(tf.getText(), tf1.getText());
                    E.setText(temp);                  
               }
           });
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        fs=e.getItem().toString();
        StyledDocument doc = (StyledDocument) E.getDocument();
    int selectionEnd = E.getSelectionEnd();
    int selectionStart = E.getSelectionStart();
      
    if (selectionStart == selectionEnd) {
      return;
    }
    Element element = doc.getCharacterElement(selectionStart);
    AttributeSet as = element.getAttributes();

    asNew = new SimpleAttributeSet(as.copyAttributes());
    
    StyleConstants.setFontFamily(asNew,fs);
    StyleConstants.setFontSize(asNew, jj);
    doc.setCharacterAttributes(selectionStart, E.getSelectedText().length(), asNew, true);
        
   
        
    }
    class ButtonPanel extends JPanel {

    JButton b1, b2, b3,b4,b5;
    int x;

    public ButtonPanel() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(300, 300));
        JPanel pan=new JPanel();
        add(pan,BorderLayout.PAGE_START);
        JButton r1=new JButton("Rectangle");
        JButton r2=new JButton("Square");
        JButton r3=new JButton("Oval");
        JButton r4=new JButton("Circle");
        JButton r5=new JButton("Line");
        pan.add(r1);
        pan.add(r2);
        pan.add(r3);
        pan.add(r4);
        pan.add(r5);
        r1.setFont(myFont);
        r2.setFont(myFont);
        r3.setFont(myFont);
        r4.setFont(myFont);
        r5.setFont(myFont);
        
        r1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x=1;
                repaint();
            }
        });
        r2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x=2;
                repaint();
            }
        });
        r3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x=3;
                repaint();
            }
        });
        r4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x=4;
                repaint();
            }
        });
        r5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    x=5;
                repaint();
            }
        });
        
            this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                X=e.getX();
                Y=e.getY();
            }
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        this.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                X1=e.getX();
                Y1=e.getY();
                repaint();
            }
            
            @Override
            public void mouseMoved(MouseEvent e) {}
        });
        
    }
    public void drawing() {
        repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       
        if (x == 1) {
            g.setColor(Color.red);
            g.fillRect(X, Y, X1-X, Y1-Y);
        }
        if (x == 2) {
            g.setColor(Color.MAGENTA);
            g.fillRect(X, Y, X1-X, X1-X);
        }
        if (x == 3) {
           g.setColor(Color.GREEN);
            g.fillOval(X, Y, X1-X, Y1-Y);
        }
        if(x==4){
        g.setColor(Color.black);
        g.fillOval(X, Y, Y1-Y, Y1-Y);
        }
        if(x==5){
        g.setColor(Color.BLUE);
        g.drawLine(X, Y, X1, Y1);
        }
        
    }
}

}
