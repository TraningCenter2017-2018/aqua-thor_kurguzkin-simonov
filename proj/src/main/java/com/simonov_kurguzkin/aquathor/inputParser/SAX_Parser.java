/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simonov_kurguzkin.aquathor.inputParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import javax.xml.parsers.SAXParser;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Евгений
 */
public class SAX_Parser extends Parser {

    @Override
    public Map<String, Object> parseConfigure(String fileLink) throws ParserConfigurationException, SAXException, IOException {
        Map<String, Object> result = new LinkedHashMap<>();

        SAXParserFactory saxpf = SAXParserFactory.newInstance();
        SAXParser parser = saxpf.newSAXParser();
        SAX_Parser handler = new SAX_Parser();
        //parser.parse(fileLink, handler);

        return result;
    }

    @Override
    public Map<String, Object> parseInput(String fileLink) throws ParserConfigurationException, SAXException, IOException {
        List<Object> tmp = new ArrayList<>();

        return listTomap(tmp);
    }

}