

import java.io.File;
import java.io.InputStream;
import java.io.*;
import java.net.URLConnection;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.awt.*;


//name of the class
public class FM{
    
    File f;
    String but;
    JFileChooser c;
    
    //the constructor the frame and the buttons
    public FM()
    {
	final JFrame frame = new JFrame("File Sorter");
	JPanel panel = new JPanel();
	panel.setLayout(new GridLayout());
	Dimension minSize = new Dimension(570,200);
	frame.setMinimumSize(minSize);
	final JButton button = new JButton("Choose Folder");
	final JButton button2 = new JButton("Sort");
	// button2.setEnabled(false);
	
	ActionListener open = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
		    buttonclick();
		    button.setLabel(but);
		    
		    button2.setEnabled(true);
		    
		}
	    };
	
    	ActionListener open2 = new ActionListener() {
		public void actionPerformed(ActionEvent actionEvent) {
		    whichFiles();
		    moveMisc("miscellaneous");
		    JPanel p = new JPanel();
		    button2.setEnabled( false );
 
      }
    };

	//location of the image that is used in the background 
	ImageIcon graph = new ImageIcon("/Users/zack/Documents/wesleyan/year 2/comp211/picture.png");
	JLabel image = new JLabel(graph);
	button.addActionListener(open);
	button2.addActionListener(open2);
	panel.add(button);
	panel.add(image);
	panel.add(button2);
	panel.setBackground(Color.WHITE);
	frame.add(panel);
	frame.setVisible(true);
	frame.setResizable(false);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    }   

    public static void main(String[] args){

	new FM();

    }

   
    //the method that is ran when you click the choose folder button
    public  void buttonclick()
    {
	File fe;
	JFileChooser chooser = new JFileChooser();
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	int returnv =chooser.showOpenDialog(null);
	if(returnv == JFileChooser.APPROVE_OPTION) {
	    String p = chooser.getCurrentDirectory().getName() ;
	    System.out.println(p);
	    f = chooser.getSelectedFile();
	    but = chooser.getSelectedFile().getName();
	}
	
	
	System.out.println(f);
	
	
	
    }
    
    
    
    
    
    //the primary method of the program that scans through the files and puts them in the correct places
    //by calling the lower methods
    //method specific for internet media type in the if statement
    public  void whichFiles(){
	{
	  
	    String files;
	    File[] listOfFiles = f.listFiles(); 
	    
	    for (int i = 0; i < listOfFiles.length; i++) 
		{
		    files = listOfFiles[i].getName();
		    if (listOfFiles[i].isFile() && URLConnection.guessContentTypeFromName(listOfFiles[i].getAbsolutePath())!=null)
			{
			    
			    
			    
			    
			    String mimeType =  URLConnection.guessContentTypeFromName(listOfFiles[i].getAbsolutePath()); 
			    System.out.println("Filetype =" + mimeType);
			    System.out.println(files);  
			    if (mimeType.contains("image"))
				{
				    moveFiles("images", listOfFiles[i]);
				    
				}
			    if (mimeType.contains("alias"))
				{
				    moveFiles("alias", listOfFiles[i]);
				    
				}
			    if (mimeType.contains("audio"))
				{
				    moveFiles("audio", listOfFiles[i]);
				    
				}
			    if (mimeType.contains("video"))
				{
				    moveFiles("videos", listOfFiles[i]);
				    
				}
			    if (mimeType.contains("message"))
				{
				    moveFiles("messages", listOfFiles[i]);
				    
				}
			    if (mimeType.contains("text"))
				{
				    moveFiles("text", listOfFiles[i]);
				    
				}
			    
			    if (mimeType.contains("pdf"))
				{
				    moveFiles("pdf", listOfFiles[i]);
				    
				    
				    
				}
			    if (mimeType.contains("application") && !mimeType.contains("zip"))
			      {
				  moveFiles("applications", listOfFiles[i]);
			      }
			    
			    
			    
			    
			}
		    
		    
		    else if(listOfFiles[i].isFile())
			{
			    otherfiles();
			    
			}
		}
	    
	    
	}
	
	
    }
    
    //puts them in the correct place
    public void moveFiles(String intype, File f3){
	System.out.println(f.getPath());
	String foldernew = makeDir(intype);
	String files;
	
	
	
	files = f3.getName();
	if (URLConnection.guessContentTypeFromName(f3.getAbsolutePath())!=null){
	    if ((URLConnection.guessContentTypeFromName(f3.getAbsolutePath())).contains(intype.substring(0,intype.length()-2)))
		{
		    f3.renameTo(new File(foldernew, files));
		    System.out.println(files);
		}
	    
	}
    }
    
    //same as whichfiles but for extension files
    public void otherfiles()
    {
	String files;
	File[] listOfFiles = f.listFiles(); 
	
	for (int i = 0; i < listOfFiles.length; i++) 
	    {
		files = listOfFiles[i].getName();
		if ((listOfFiles[i].isFile() && URLConnection.guessContentTypeFromName(listOfFiles[i].getAbsolutePath())==null) && files.contains(".")|| (listOfFiles[i].isFile() && files.contains(".") && URLConnection.guessContentTypeFromName(listOfFiles[i].getAbsolutePath()).contains("application/zip"))) {
	  
		    
		    System.out.println(files);
		    
		    String ext =  files.substring(files.lastIndexOf("."));
		    
		    System.out.println("ext =" + ext);
		    System.out.println(files);
		    
		    if (ext.contains("mp3"))
			{
			    moveOthers("audio", "mp3", listOfFiles[i]); 
			}
		    if (ext.contains("MP3"))
			{
			    moveOthers("audio", "MP3", listOfFiles[i]);
			}
		    if (ext.contains("app"))
			{
			    moveOthers("applications", "app", listOfFiles[i]);
			}
		    if (ext.contains("mp4"))
			{
				  moveOthers("videos", "mp4", listOfFiles[i]);	  
			}
		    if (ext.contains("psd"))
			{
			    moveOthers("documents", "psd", listOfFiles[i]);   
			}
		    if (ext.contains("mov"))
			{
			    moveOthers("videos", "mov", listOfFiles[i]);
   
			}
		    if (ext.contains("jpg"))
			{
			    moveOthers("images", "jpg", listOfFiles[i]); 
			}
		    if (ext.contains("doc"))
			{
			    moveOthers("documents", "doc", listOfFiles[i]); 
			}
		    if (ext.contains("ichat"))
			{
			    moveOthers("documents", "ichat", listOfFiles[i]);
			    
			}
		    if (ext.contains("xls"))
			{
			    moveOthers("documents", "xls", listOfFiles[i]);
			      }
		    if (ext.contains("txt"))
			{
			    moveOthers("documents", "txt", listOfFiles[i]);

			      }
		    if (ext.contains("ppt"))
			{
			    moveOthers("documents", "ppt", listOfFiles[i]);
			}
		    if (ext.contains("m4a"))
			{
			    moveOthers("audio", "m4a", listOfFiles[i]);   
			}
		    if (ext.contains("aif"))
			{
			    moveOthers("audio", "aif", listOfFiles[i]);  
			}
		    if (ext.contains("mid"))
			{
			    moveOthers("audio", "mid", listOfFiles[i]);  
			}
		    if (ext.contains("m4v"))
			{
			    moveOthers("videos", "m4v", listOfFiles[i]);
			    
			    
			      }
		    if (ext.contains("rtf"))
			{
			    moveOthers("text", "rtf", listOfFiles[i]);
			    
			    
			}
		    if (ext.contains("aup"))
			{
			    moveOthers("audio", "aup", listOfFiles[i]);
			    
			    
			}
			       if (ext.contains("aup"))
				   {
				       moveOthers("audio", "aup", listOfFiles[i]);
				       
				       
				   }
			       if (ext.contains("log"))
				   {
				       moveOthers("text", "log", listOfFiles[i]);
				       
				       
				   }
			       if (ext.contains("bmp"))
			      {
				  moveOthers("images", "bmp", listOfFiles[i]);
				  
				  
			      }
			       if (ext.contains("zip"))
				   {
				       moveOthers("zip", "zip", listOfFiles[i]);
				       
				       
				   }
			       if (ext.contains("rar"))
				   {
				       moveOthers("rar", "rar", listOfFiles[i]);
				   }
	       
		}
	    }
    }
    //the method that moves files with extensions to folders
    public void moveOthers(String intype2, String ext2, File f2){
	System.out.println(f.getPath());
	String foldernew = makeDir(intype2);
	String files;

	files = f2.getName();
	if (URLConnection.guessContentTypeFromName(f2.getAbsolutePath())==null || (URLConnection.guessContentTypeFromName(f2.getAbsolutePath()).contains("application/zip")))
	    {
		if ((f2.getName().contains(ext2)))
		    {
			f2.renameTo(new File(foldernew, files));
			System.out.println(files);
		    }
		
	    }

    }
    

    public void moveMisc(String intype2){
	System.out.println(f.getPath());
	String foldernew = makeDir(intype2);
	File[] listOfFiles = f.listFiles(); 
	String files;
	
	for (int i = 0; i < listOfFiles.length; i++) 
	    {
		files = listOfFiles[i].getName();
		if (listOfFiles[i].isFile())
		    {
			files = listOfFiles[i].getName();
			
			{
			    listOfFiles[i].renameTo(new File(foldernew, files));
			    System.out.println(files);
			}
		    }
		
		
		
	    }
    }
    
    

    
    //method to create directorys that returns the name of the directory
    public  String makeDir(String in){
	String path = f.toString();
	File a = new File(path, in);
	System.out.println(a);
	if (a.exists()){}
	else{

	    a.mkdir();
	    
	}
	return a.getPath();
	
    }
    
    
}