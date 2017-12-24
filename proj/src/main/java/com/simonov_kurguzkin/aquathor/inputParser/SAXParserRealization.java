/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.simonov_kurguzkin.aquathor.inputParser;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Евгений
 */
public class SAXParserRealization extends DefaultHandler {

    private Map<String, Object> resultMap;
    private List<Object> resultList;
    private String tmp;
    private boolean configFlag;
    private boolean entitiesFlag;
    private boolean streamsFlag;
    private boolean fishFlag;
    private boolean sharkFlag;
    private int streamsNum;

    public SAXParserRealization() {
        resultMap = new LinkedHashMap<>();
        resultList = new LinkedList<>();
        streamsNum = 0;
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public List<Object> getResultList() {
        return resultList;
    }

    @Override
    public void characters(char[] buffer, int start, int length) {
        tmp = new String(buffer, start, length);
    }

    @Override
    public void startElement(String uri, String localName,
            String qName, Attributes attributes) throws SAXException {
        tmp = "";
        if (qName.equalsIgnoreCase("configuration")) {
            configFlag = true;
        }
        if (qName.equalsIgnoreCase("entities")) {
            entitiesFlag = true;
        }
        if (entitiesFlag && qName.equalsIgnoreCase("stream")) {
            streamsFlag = true;
            streamsNum++;
        }
        if (entitiesFlag && qName.equalsIgnoreCase("fish")) {
            fishFlag = true;
        }
        if (entitiesFlag && qName.equalsIgnoreCase("shark")) {
            sharkFlag = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equalsIgnoreCase("configuration")) {
            configFlag = false;
        }
        if (qName.equalsIgnoreCase("entities")) {
            entitiesFlag = false;
        }
        if (qName.equalsIgnoreCase("streams")) {
            streamsFlag = false;
            resultList.add(0, streamsNum);
        }
        if (qName.equalsIgnoreCase("fish")) {
            fishFlag = false;
        }
        if (qName.equalsIgnoreCase("shark")) {
            sharkFlag = false;
        }
        handleTag(qName);
    }

    private void handleTag(String tagName) {
        if (configFlag) {
            if (tagName.equalsIgnoreCase("configuration")
                    || tagName.equalsIgnoreCase("field")) {
                return;
            }
            resultMap.put(tagName, tmp);
        }
        if (entitiesFlag) {
            if (tagName.equalsIgnoreCase("entities")
                    || tagName.equalsIgnoreCase("streams")
                    || tagName.equalsIgnoreCase("stream")
                    || tagName.equalsIgnoreCase("animals")
                    || tagName.equalsIgnoreCase("fish")
                    || tagName.equalsIgnoreCase("shark")) {
                return;
            }
            int tmpInt = Integer.parseInt(tmp);
            if (streamsFlag) {
                resultList.add(tmpInt);
            }
            if (fishFlag || sharkFlag) {
                if (tagName.equalsIgnoreCase("quantity")) {
                    resultList.add(resultList.size() - 4, tmpInt);
                } else {
                    resultList.add(tmpInt);
                }
            }
        }
    }
}