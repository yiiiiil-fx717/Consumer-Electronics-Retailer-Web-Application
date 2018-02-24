import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

public class XMLHashmapUser extends DefaultHandler{
    HashMap<String,User> usersHashMap = new HashMap<String, User>();;
    List<User> usersList;

    User userRead;
    String elementValueRead;
    
    //String itemXmlFileName;
    
    public XMLHashmapUser(HashMap<String,User> usersHashMap){//for save
        this.usersHashMap = usersHashMap;
        
        usersList = new ArrayList<User>();
        usersList.addAll(usersHashMap.values());
        
        hashmapSaveToXml();
    }
    public XMLHashmapUser(String itemXmlFileName){//for read
        usersList = new ArrayList<User>();
        
        parseDocument(itemXmlFileName);
        for (User user: usersList) {           
            usersHashMap.put(user.getUserId(), user);//use id as the HashMap key. save item data into the HashMap
        }
    }
    
    public void hashmapSaveToXml(){
        SAXTransformerFactory sf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        OutputStream in = null;
        try {
            TransformerHandler handler = sf.newTransformerHandler();
            Transformer transformer = handler.getTransformer();
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            File file = new File("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\User.xml");
            if(!file.exists()){
                if(!file.createNewFile()){
                    throw new FileNotFoundException("fail to create the file");
                }
            }
            
            in = new FileOutputStream(file);
            Result result = new StreamResult(in);
            handler.setResult(result);
            handler.startDocument();
            AttributesImpl attr = new AttributesImpl();
            
            handler.startElement("", "", "UserMap", attr);
            
            for(User user : usersList){
                attr.addAttribute("", "", "id", "", user.getUserId());
                handler.startElement("", "", "User", attr);
                
                attr.clear();
                handler.startElement("", "", "password", attr);
                handler.characters(user.getPassword().toCharArray(), 0, user.getPassword().toCharArray().length);
                handler.endElement("", "", "password");
                
                attr.clear();
                handler.startElement("", "", "type", attr);
                handler.characters(user.getType().toCharArray(), 0, user.getType().toCharArray().length);
                handler.endElement("", "", "type");
                
                handler.endElement("", "", "User");
            }
            
            
            handler.endElement("", "", "UserMap");
            
            handler.endDocument();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }            
    }

    private void parseDocument(String itemXmlFileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(itemXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.out.println("IO error");
        }
    }
    
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("user")) {
            userRead = new User();
            userRead.setUserId(attributes.getValue("id"));
        }
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equalsIgnoreCase("user")) {
            usersList.add(userRead);
            return;
        }
        if (element.equalsIgnoreCase("password")) {
            userRead.setPassword(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("type")) {
            userRead.setType(elementValueRead);
            return;
        }
    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

    public User getUser(String key){
        return usersHashMap.get(key);
    }

    //public static void main(String[] args) {
        //new XMLHashmapUser("User.xml");
    //}
}