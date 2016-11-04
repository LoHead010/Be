package com.lohead010.be;


import android.view.View;
import android.widget.ArrayAdapter;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

public class Verbs {

    String api = "?key=673af193-e9ea-414b-9466-53a230643844";
    String url = "http://www.dictionaryapi.com/api/v1/references/sd3/xml/";

    String Word;
    String x = url + Word + api;
    String Definition;








    public Verbs( String word, String definition) {
        //super();

        this.Word = word;
        this.Definition = definition();

    }
    //remove static
    public String definition() {
        String apiD= "?key=673af193-e9ea-414b-9466-53a230643844";
        String urlD = "http://www.dictionaryapi.com/api/v1/references/sd3/xml/";
        String word="";
        //View rootView = (View)findViewById(R.id.tvBe);
        String resultString = "";
        try {
            URL site = new URL(apiD + urlD + word);
            HttpURLConnection conn = (HttpURLConnection) site.openConnection();
            conn.setReadTimeout(100000);
            conn.setConnectTimeout(150000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            InputStream is = null;
            is = conn.getInputStream();
            Scanner scan = new Scanner(is);
            while (scan.hasNext()) {
                resultString += scan.nextLine();
            }
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            InputStream stream = new ByteArrayInputStream(resultString.getBytes());
            Document document = builder.parse(stream);
            XPath xpathObject = XPathFactory.newInstance().newXPath();
            String xpathQuery = "//entry[@id="+ "savvy[1]" + "]/hw/text()";
            String[] define = new String[2];
            NodeList nodes = (NodeList)xpathObject.evaluate(xpathQuery, document, XPathConstants.NODESET);
            define[0] = nodes.item(0).getTextContent();
            xpathQuery = "//entry[@id=" + "savvy[1]" + "]/fl/text()";
            nodes = (NodeList) xpathObject.evaluate(xpathQuery, document, XPathConstants.NODESET);
            define[1] = nodes.item(0).getTextContent();
            xpathQuery= "//entry[@id="+"savvy[1]"+"]/def/dt/sx/text()";
            nodes = (NodeList)xpathObject.evaluate(xpathQuery, document, XPathConstants.NODESET);
            define[2] =  nodes.item(0).getTextContent() + "\n" + nodes.item(1).getTextContent();

            //View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //  ArrayAdapter<String> definitionAdapter = new ArrayAdapter<String>(rootView.getContext(), +
            //      android.R.layout.simple_list_item_activated_1, define);

            resultString = define[0] + "\n" + define[1] + "\n" + define[2];
        } catch (Exception e) {
            System.console().writer().write("Error establishing connection: \n" + e.toString());
        }
        return resultString;



    }
    public static void main(String[] args){
        Verbs a = new Verbs("Savvy.", "Comprehend or" + "\n" + "Understanding");
        Verbs b = new Verbs("Thankful.", "feeling or showing thanks");
        Verbs c = new Verbs("Grateful.", "appreciative of " + "\n" + "benefits received");
        Verbs d = new Verbs("Selfless", "having no concern for self");
        Verbs e = new Verbs("Rewarding", "giving satisfaction");
        ArrayList<Verbs> words = new ArrayList<Verbs>();
        words.add(a);
        words.add(b);
        words.add(c);
        words.add(d);
        words.add(e);
    }
}
