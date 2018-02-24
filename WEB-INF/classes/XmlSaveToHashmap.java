import java.io.*;
//import javax.servlet.*;
//import javax.servlet.http.*;
import java.text.ParseException;
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


public class XmlSaveToHashmap extends DefaultHandler {
    Item item;
    Accessory accessory;
    List<Item> items;
    List<Accessory> accessories;
    String itemXmlFileName;
    String elementValueRead;
    
    HashMap<String, Item> itemHashmap = new HashMap<String, Item>();
    HashMap<String, Accessory> accHashmap = new HashMap<String, Accessory>();

    public XmlSaveToHashmap(HashMap<String,Item> itemHashmap, HashMap<String, Accessory> accHashmap){
        this.itemHashmap = itemHashmap;
        this.accHashmap  = accHashmap;
        
        items = new ArrayList<Item>();
        items.addAll(itemHashmap.values());

        accessories = new ArrayList<Accessory>();
        accessories.addAll(accHashmap.values());
        
        hashmapSaveToXml();
    }
    
    public XmlSaveToHashmap(String itemXmlFileName) {
        this.itemXmlFileName = itemXmlFileName;
        items = new ArrayList<Item>();
        accessories = new ArrayList<Accessory>();
        parseDocument();
        for (Item item: items) {           
            itemHashmap.put(item.id, item);    //use id as the HashMap key. save item data into the HashMap
        }
        for (Accessory accessory: accessories) {           
            accHashmap.put(accessory.name, accessory);    //use id as the HashMap key. save item data into the HashMap
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
            File file = new File("C:\\apache-tomcat-7.0.34\\webapps\\HW1\\WEB-INF\\classes\\Product.xml");
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
            
            handler.startElement("", "", "Product", attr);
            for(Item item : items){
                attr.clear();
                attr.addAttribute("", "", "id", "", item.getId());
                handler.startElement("", "", "item", attr);
                
                attr.clear();
                handler.startElement("", "", "image", attr);
                handler.characters(item.getImage().toCharArray(), 0, item.getImage().toCharArray().length);
                handler.endElement("", "", "image");
                
                attr.clear();
                handler.startElement("", "", "name", attr);
                handler.characters(item.getName().toCharArray(), 0, item.getName().toCharArray().length);
                handler.endElement("", "", "name");
                
                attr.clear();
                handler.startElement("", "", "color", attr);
                handler.characters(item.getColor().toCharArray(), 0, item.getColor().toCharArray().length);
                handler.endElement("", "", "color");
                
                attr.clear();
                handler.startElement("", "", "price", attr);
                String tmp = Integer.toString(item.getPrice());
                handler.characters(tmp.toCharArray(), 0, tmp.toCharArray().length);
                handler.endElement("", "", "price");
                
                attr.clear();
                handler.startElement("", "", "accessories", attr);
                List<String> tmpList = item.getAccessories();
                for(String tmpAccessory : tmpList){
                    attr.clear();
                    handler.startElement("", "", "accessory", attr);
                    handler.characters(tmpAccessory.toCharArray(), 0, tmpAccessory.toCharArray().length);
                    handler.endElement("", "", "accessory");
                }
                handler.endElement("", "", "accessories");               
                handler.endElement("", "", "item");
            }

            //attr = new AttributesImpl();
            //handler.startElement("", "", "AccessoryCategory", attr);
            for(Accessory accessory : accessories){
                attr.clear();
                attr.addAttribute("", "", "id", "", accessory.getId());
                handler.startElement("", "", "accessorydetail", attr);
                
                attr.clear();
                handler.startElement("", "", "image", attr);
                handler.characters(accessory.getImage().toCharArray(), 0, accessory.getImage().toCharArray().length);
                handler.endElement("", "", "image");
                
                attr.clear();
                handler.startElement("", "", "name", attr);
                handler.characters(accessory.getName().toCharArray(), 0, accessory.getName().toCharArray().length);
                handler.endElement("", "", "name");
                
                attr.clear();
                handler.startElement("", "", "price", attr);
                String tmp = Integer.toString(accessory.getPrice());
                handler.characters(tmp.toCharArray(), 0, tmp.toCharArray().length);
                handler.endElement("", "", "price");

                handler.endElement("", "", "accessorydetail");
            }
            //handler.endElement("", "", "AccessoryCategory");
            handler.endElement("", "", "Product");
            
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

    private void parseDocument() {
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

    public HashMap<String, Item> getHashmap(){
        return itemHashmap;
    }
    
    public Item getItem(String key){
        return itemHashmap.get(key);
    }

    public Accessory getAccessory(String key){
        return accHashmap.get(key);
    }
    
    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {

        if (elementName.equalsIgnoreCase("item")) {
            item = new Item();
            item.setId(attributes.getValue("id"));
        }
        if (elementName.equalsIgnoreCase("accessorydetail")) {
            accessory = new Accessory();
            accessory.setId(attributes.getValue("id"));
        }

    }

    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
 
        if (element.equalsIgnoreCase("item")) {
            items.add(item);
            //item = null;
            return;
        }
        if (element.equalsIgnoreCase("accessorydetail")) {
            accessories.add(accessory);
            //accessory = null;
            return;
        }
        if (element.equalsIgnoreCase("image")) {
            if(null != item)item.setImage(elementValueRead);
            if(null != accessory)accessory.setImage(elementValueRead);
            return;
        }
        if (element.equalsIgnoreCase("name")) {
            if(null != item)item.setName(elementValueRead);
            if(null != accessory)accessory.setName(elementValueRead);
            return;
        }
        if(element.equalsIgnoreCase("color")){
            if(null != item)item.setColor(elementValueRead);
            return;
        }
        if(element.equalsIgnoreCase("price")){
            if(null != item)item.setPrice(Integer.parseInt(elementValueRead));
            if(null != accessory)accessory.setPrice(Integer.parseInt(elementValueRead));
            return;
        }
        if(element.equalsIgnoreCase("accessory")){
            item.getAccessories().add(elementValueRead);
           return;
        }

    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

    public static void main(String[] args) {
        new XmlSaveToHashmap("Product.xml");
    }

}