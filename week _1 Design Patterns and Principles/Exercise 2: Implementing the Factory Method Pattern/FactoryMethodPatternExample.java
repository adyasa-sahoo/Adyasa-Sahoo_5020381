package FactoryMethodPatternExample;


interface Document {
 void open();
}

class WordDocument implements Document {
 @Override
 public void open() {
     System.out.println("Opening Word document.");
 }
}

class PdfDocument implements Document {
 @Override
 public void open() {
     System.out.println("Opening PDF document.");
 }
}

class ExcelDocument implements Document {
 @Override
 public void open() {
     System.out.println("Opening Excel document.");
 }
}

//Step 4: Implement the Factory Method
abstract class DocumentFactory {
 public abstract Document createDocument();
}

class WordDocumentFactory extends DocumentFactory {
 @Override
 public Document createDocument() {
     return new WordDocument();
 }
}

class PdfDocumentFactory extends DocumentFactory {
 @Override
 public Document createDocument() {
     return new PdfDocument();
 }
}

class ExcelDocumentFactory extends DocumentFactory {
 @Override
 public Document createDocument() {
     return new ExcelDocument();
 }
}


public class FactoryMethodPatternExample {
 public static void main(String[] args) {
    

     
     DocumentFactory wordFactory = new WordDocumentFactory();
     Document wordDoc = wordFactory.createDocument();
     wordDoc.open();

     
     DocumentFactory pdfFactory = new PdfDocumentFactory();
     Document pdfDoc = pdfFactory.createDocument();
     pdfDoc.open();

     
     DocumentFactory excelFactory = new ExcelDocumentFactory();
     Document excelDoc = excelFactory.createDocument();
     excelDoc.open();
 }
}
