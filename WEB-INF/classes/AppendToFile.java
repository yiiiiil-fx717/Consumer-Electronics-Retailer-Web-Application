import java.io.*;
import java.util.*;

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

public class AppendToFile extends DefaultHandler
{
    XmlSaveToHashmap productHashmap = new XmlSaveToHashmap("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\Product.xml");
    //String filename;
    File file;
    FileWriter fileWritter;
    BufferedWriter bufferWritter;

    List<ItemOrder> itemsList;
    List<AccessoryOrder> accsList;

    ItemOrder itemRead;
    AccessoryOrder accRead;

    String elementValueRead;

    public AppendToFile(String path, String filename){
        itemsList = new ArrayList<ItemOrder>();
        accsList  = new ArrayList<AccessoryOrder>();
        parseDocument(path + "\\" + filename);
    }
    public AppendToFile(String path, String filename, ShoppingCart cart, ShoppingCartAcc cartAcc, int year, int mon, int date,String enddate, String name,String address, String cardNum) throws IOException{
        File filpath = new File(path);
        filpath.mkdirs();

        file = new File(path + "\\" + filename);
        if(!file.exists()){
            file.createNewFile();
        }

        cartSaveToXml(cart,cartAcc, year, mon, date, enddate, name, address, cardNum);
        //System.out.println("Done");
    }

    public void cartSaveToXml(ShoppingCart cart, ShoppingCartAcc cartAcc, int year, int mon, int date, String enddate, String name,String address, String cardNum){
        SAXTransformerFactory sf = (SAXTransformerFactory) SAXTransformerFactory.newInstance();
        OutputStream in = null;
        try {
            TransformerHandler handler = sf.newTransformerHandler();
            Transformer transformer = handler.getTransformer();
            transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            
            in = new FileOutputStream(file);
            Result result = new StreamResult(in);
            handler.setResult(result);
            handler.startDocument();
            String str;
            AttributesImpl attr = new AttributesImpl();
            
            handler.startElement("", "", "Order", attr);

            /*{
                attr.clear();
                handler.startElement("", "", "Info", attr);
    
                attr.clear();
                str = Integer.toString(year);
                handler.startElement("", "", "year", attr);
                handler.characters(str.toCharArray(), 0, str.toCharArray().length);
                handler.endElement("", "", "year");
    
                attr.clear();
                str = Integer.toString(mon);
                handler.startElement("", "", "month", attr);
                handler.characters(str.toCharArray(), 0, str.toCharArray().length);
                handler.endElement("", "", "month");
    
                attr.clear();
                str = Integer.toString(date);
                handler.startElement("", "", "date", attr);
                handler.characters(str.toCharArray(), 0, str.toCharArray().length);
                handler.endElement("", "", "date");
                
                attr.clear();
                str = enddate;
                handler.startElement("", "", "enddate", attr);
                handler.characters(str.toCharArray(), 0, str.toCharArray().length);
                handler.endElement("", "", "enddate");
                
                attr.clear();
                str = name;
                handler.startElement("", "", "name", attr);
                handler.characters(str.toCharArray(), 0, str.toCharArray().length);
                handler.endElement("", "", "name");
    
                attr.clear();
                str = address;
                handler.startElement("", "", "address", attr);
                handler.characters(str.toCharArray(), 0, str.toCharArray().length);
                handler.endElement("", "", "year");
    
                attr.clear();
                str = cardNum;
                handler.startElement("", "", "cardNum", attr);
                handler.characters(str.toCharArray(), 0, str.toCharArray().length);
                handler.endElement("", "", "cardNum");
                
                handler.endElement("", "", "Info");
            }*/

            ArrayList itemsOrdered = (ArrayList)cart.getItemsOrdered();
            ArrayList accsOrdered  = (ArrayList)cartAcc.getAccsOrdered();
            
            for(int i=0; i<itemsOrdered.size(); i++) {//id num
                ItemOrder order = (ItemOrder)itemsOrdered.get(i);
                
                attr.addAttribute("", "", "id", "", order.getItem().id);
                handler.startElement("", "", "Item", attr);
                
                String num = Integer.toString(order.getNumItems());
                attr.clear();
                handler.startElement("", "", "num", attr);
                handler.characters(num.toCharArray(), 0, num.toCharArray().length);
                handler.endElement("", "", "num");
                
                handler.endElement("", "", "Item");
            }
            for(int i=0; i<accsOrdered.size(); i++) {
                AccessoryOrder order = (AccessoryOrder)accsOrdered.get(i);

                attr.addAttribute("", "", "name", "", order.getAccessory().name);
                handler.startElement("", "", "Accessory", attr);
                
                String num = Integer.toString(order.getNumAccessories());
                attr.clear();
                handler.startElement("", "", "num", attr);
                handler.characters(num.toCharArray(), 0, num.toCharArray().length);
                handler.endElement("", "", "num");
                
                handler.endElement("", "", "Accessory");
            }
            
            
            handler.endElement("", "", "Order");
            
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

        if (elementName.equalsIgnoreCase("item")) {
            Item item = productHashmap.getItem(attributes.getValue("id"));
            itemRead = new ItemOrder(item,0);
        }
        if (elementName.equalsIgnoreCase("Accessory")) {
            Accessory accessory = productHashmap.getAccessory(attributes.getValue("name"));
            accRead = new AccessoryOrder(accessory,0);
        }
    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equalsIgnoreCase("item")) {
            itemsList.add(itemRead);
            //itemRead = null;
            return;
        }
        if (element.equalsIgnoreCase("Accessory")) {
            accsList.add(accRead);
            //accRead = null;
            return;
        }
        if (element.equalsIgnoreCase("num")) {
            if(itemRead != null)itemRead.setNumItems(Integer.parseInt(elementValueRead));
            if(accRead != null)accRead.setNumAccessories(Integer.parseInt(elementValueRead));
            return;
        }
    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

    public List<ItemOrder> getItemsList(){
        return itemsList;
    }

    public List<AccessoryOrder> getAccsList(){
        return accsList;
    }
}