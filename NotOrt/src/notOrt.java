import java.util.Scanner;


public class notOrt {

    public static void main(String[] args) {
       double mat,turk,muzik,fizik,kimya,tarih;
       
       Scanner inp = new Scanner(System.in);
       
        System.out.println("Mat notunuzu giriniz: ");
        mat=inp.nextDouble();
        
        System.out.println("Türkçe notunuzu giriniz: ");
        turk=inp.nextDouble();
        
        System.out.println("Müzik notunuzu giriniz: ");
        muzik=inp.nextDouble();
        
        System.out.println("Fizik notunuzu giriniz: ");
        fizik=inp.nextDouble();
        
        System.out.println("Kimya notunuzu giriniz: ");
        kimya=inp.nextDouble();
        
        System.out.println("Tarih notunuzu giriniz: ");
        tarih=inp.nextDouble();
        
       double ort=(mat+turk+muzik+fizik+kimya+tarih)/6.0;
        System.out.println("Ortalamanız:"+ort);
        
       System.out.println(ort > 60 ? "Sınıfı Geçti" : "Sıfta Kaldı");         
    }
    
}

