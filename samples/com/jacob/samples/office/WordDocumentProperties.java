package com.jacob.samples.office;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComFailException;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * Submitted to the Jacob SourceForge web site as a sample 3/2005
 * 
 * @author Date Created Description Jason Twist 04 Mar 2005 Code opens a locally
 *         stored Word document and extracts the Built In properties and Custom
 *         properties from it. This code just gives an intro to JACOB and there
 *         are sections that could be enhanced
 */
public class WordDocumentProperties {
    //Declare word object
    private ActiveXComponent objWord;

    //Declare Word Properties
    private Dispatch custDocprops;

    private Dispatch builtInDocProps;

    //the doucments object is important in any real app but this demo doesn't use it
    //private Dispatch documents;

    private Dispatch document;

    private Dispatch wordObject;

    /**
     * Empty Constructor
     *  
     */
    public WordDocumentProperties() {
    }

    /**
     * Opens a document
     * 
     * @param filename
     */
    public void open(String filename) {
        //Instantiate objWord
        objWord = new ActiveXComponent("Word.Application");

        //Assign a local word object
        wordObject = objWord.getObject();

        //Create a Dispatch Parameter to hide the document that is opened
        Dispatch.put((Dispatch) wordObject, "Visible", new Variant(false));

        //Instantiate the Documents Property
        Dispatch documents = objWord.getProperty("Documents").toDispatch();

        //Open a word document, Current Active Document
        document = Dispatch.call(documents, "Open", filename).toDispatch();
    }

    /**
     * Creates an instance of the VBA CustomDocumentProperties property
     *  
     */
    public void selectCustomDocumentProperitiesMode() {
        //Create CustomDocumentProperties and BuiltInDocumentProperties
        // properties
        custDocprops = Dispatch.get(document, "CustomDocumentProperties")
                .toDispatch();
    }

    /**
     * Creates an instance of the VBA BuiltInDocumentProperties property
     *  
     */
    public void selectBuiltinPropertiesMode() {
        //Create CustomDocumentProperties and BuiltInDocumentProperties
        // properties
        builtInDocProps = Dispatch.get(document, "BuiltInDocumentProperties")
                .toDispatch();
    }

    /**
     * Closes a document
     *  
     */
    public void close() {
        //Close object
        Dispatch.call(document, "Close");
    }

    /**
     * Custom Property Name is passed in
     * 
     * @param cusPropName
     * @return String - Custom property value
     */
    public String getCustomProperty(String cusPropName) {
        try {
            cusPropName = Dispatch.call((Dispatch) custDocprops, "Item",
                    cusPropName).toString();
        } catch (ComFailException e) {
            // Do nothing
            cusPropName = null;
        }

        return cusPropName;
    }

    /**
     * Built In Property Name is passed in
     * 
     * @param builtInPropName
     * @return String - Built in property value
     */
    public String getBuiltInProperty(String builtInPropName) {
        try {
            builtInPropName = Dispatch.call((Dispatch) builtInDocProps, "Item",
                    builtInPropName).toString();
        } catch (ComFailException e) {
            // Do nothing
            builtInPropName = null;
        }

        return builtInPropName;
    }

    /**
     * simple main program that gets some properties and prints them out
     * @param args
     */
    public static void main(String[] args) {
        try {
            //Instantiate the class
            WordDocumentProperties jacTest = new WordDocumentProperties();

            //Open the word doc
            jacTest.open("\\\\Saturn\\documentstorage\\s.doc");

            //Set Custom Properties
            jacTest.selectCustomDocumentProperitiesMode();

            //Set Built In Properties 
            jacTest.selectBuiltinPropertiesMode();

            //Get custom Property Value 
            String custValue = jacTest.getCustomProperty("Information Source");

            //Get built in prroperty Property Value 
            String builtInValue = jacTest.getBuiltInProperty("Author");

            //Close Word Doc 
            jacTest.close();

            //Output data 
            System.out.println("Document Val One: " + custValue);
            System.out.println("Document Author: " + builtInValue);

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}