package Collage;

import java.awt.*;
import java.awt.image.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.*;
import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CollageYvonne implements ICollage {
	
	public class ImgYvonne {
		public String thumb;
		public String image;
		
		public ImgYvonne(String t, String i) {
			this.thumb = t;
			this.image = i;
		}
		
		public String toString() {
			return "{t:'"+this.thumb+"',i:'"+this.image+"'}";
		}
	}

    private BufferedImage f_img;
    private int width;
    private int height;
    private String linkUrl = "http://pics.mytrapster.com/yvonne-list.php";
    private boolean rand = false;

    public CollageYvonne(int width, int height) {
    	this.width = width;
		this.height = height;
		this.f_img = null;
    }

	@Override
	public void Create() {
		// Initialize the area array to keep up with used space
		CollageArray ca = new CollageArray(this.width, this.height);
		
		// Initialize the image
		BufferedImage n_img = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_ARGB);
		Graphics g_img = n_img.getGraphics();
		g_img.setColor(Color.black);
		
		// Set cont to true to start loop
		boolean cont = true;
		
		// Load the images
		try {
			ArrayList<ImgYvonne> pics = ParseXML(LoadXML(linkUrl));
			int picCounter = 0;
			
			// Loop while true
			while(cont) {
				// Draw a square
				if (ca.IsAvailable()) {
					Thread.sleep(1000L);
					Point start = ca.GetOpenArea();					
					ImgYvonne iy;
					
					if (this.rand == false) {
						iy = pics.get(picCounter);
						picCounter++;
						if (picCounter == pics.size()) { picCounter = 0; }
					} else {
						iy = RandomPic(pics);
					}
					
					try {
						BufferedImage t_img = ImageIO.read(new URL(iy.thumb));
						g_img.drawImage(t_img, start.x, start.y, t_img.getWidth(), t_img.getHeight(), Color.black, null);
						ca.SetTakenArea(start.x, start.y, t_img.getWidth(), t_img.getHeight());
					} catch (Exception e) {
						throw new Exception(e);
					}
				} else {
					cont = false;
				}
			}
			
			// Copy the local image to the finished one
			g_img.finalize();
			this.f_img = n_img;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BufferedImage GetImage() {
		return this.f_img;
	}
	
	public void setRandom(boolean yn) {
		this.rand = yn;
	}
	
	private ImgYvonne RandomPic(ArrayList<ImgYvonne> pics) {
		Random r = new Random();
		return pics.get(r.nextInt(pics.size() - 1));
	}
	
	// Copypasta: http://stackoverflow.com/questions/4328711/read-url-to-string-in-few-lines-of-java-code
	private String LoadXML(String url) throws Exception {
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		return response.toString();
	}
	
	
	private ArrayList<ImgYvonne> ParseXML(String xml) throws ParserConfigurationException, SAXException, Exception {
		ArrayList<ImgYvonne> pics = new ArrayList<ImgYvonne>();
		
		InputSource is = new InputSource(new StringReader(xml));
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document doc = builder.parse(is);
	    
	    NodeList nodeList = doc.getDocumentElement().getChildNodes();
	    
	    for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
            	Element elem = (Element) node;
            	
            	String t = elem.getElementsByTagName("thumb").item(0).getChildNodes().item(0).getNodeValue();
            	String f = elem.getElementsByTagName("full").item(0).getChildNodes().item(0).getNodeValue();
            	
            	pics.add(new ImgYvonne(t, f));
            }
	    }
	    
	    if (pics.isEmpty()) { throw new Exception("No images"); }
	    
		return pics;
	}

}
