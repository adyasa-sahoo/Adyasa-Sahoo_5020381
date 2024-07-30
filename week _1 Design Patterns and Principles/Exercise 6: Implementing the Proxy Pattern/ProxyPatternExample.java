package ProxyPatternExample;



interface Image {
 void display();
}


class RealImage implements Image {
 private String fileName;

 public RealImage(String fileName) {
     this.fileName = fileName;
     loadImageFromServer();
 }

 private void loadImageFromServer() {
     System.out.println("Loading " + fileName + " from remote server...");
   
     try {
         Thread.sleep(2000); 
     } catch (InterruptedException e) {
         e.printStackTrace();
     }
 }

 @Override
 public void display() {
     System.out.println("Displaying " + fileName);
 }
}


class ProxyImage implements Image {
 private String fileName;
 private RealImage realImage;

 public ProxyImage(String fileName) {
     this.fileName = fileName;
 }

 @Override
 public void display() {
     if (realImage == null) {
         realImage = new RealImage(fileName);
     }
     realImage.display();
 }
}


public class ProxyPatternExample {
 public static void main(String[] args) {
     Image image1 = new ProxyImage("photo1.jpg");
     Image image2 = new ProxyImage("photo2.jpg");

     
     System.out.println("First display call for photo1.jpg:");
     image1.display();
     System.out.println();

     
     System.out.println("Second display call for photo1.jpg:");
     image1.display();
     System.out.println();

     
     System.out.println("First display call for photo2.jpg:");
     image2.display();
     System.out.println();

     
     System.out.println("Second display call for photo2.jpg:");
     image2.display();
 }
}
