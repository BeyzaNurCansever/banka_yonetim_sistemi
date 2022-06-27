/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author BeyzaNurCansever
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class bağlantı {
    
    public static String kullanici_adi = "root";
    public static String parola = "";
    
    public static String db_ismi = "banka";
    
    public static String host =  "localhost";
    
    public static int port = 3306;
    
    
    private Connection con = null;

    private Statement statement = null;
    private PreparedStatement preparedstatement = null;
    
    
     public ArrayList<temsilcibilgi> tablogetir(int temsilciid) {
        
        ArrayList<temsilcibilgi> cikti = new ArrayList<temsilcibilgi>();
         String sorgu =  "Select * From müşteri where temsilci_no = '"+temsilciid+"'";
         
        
        try {
            statement =  con.createStatement();
          ResultSet rs =  statement.executeQuery(sorgu);
            
            while(rs.next()) {
                
                String tcno=rs.getString("müşteri_tcno");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
                
                
                cikti.add(new temsilcibilgi(tcno, ad, soyad));
                
                
            }
            return cikti;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
        
        
        
        
    }
      public ArrayList<deadlock_bilgi>  deadlock_analiz(){
          int counter=0;
           ArrayList<deadlock_bilgi> cikti = new ArrayList<deadlock_bilgi>();
         String sorgu =  "Select h1.işlem_no,h2.işlem_no From işlem h1,işlem h2 where "+
                           "h1.kaynak=h2.hedef and h1.hedef=h2.kaynak and h1.tarih=h2.tarih";
         
        
        try {
            statement =  con.createStatement();
          ResultSet sonuc =  statement.executeQuery(sorgu);
            
            while(sonuc.next()) {
                
                int işlem_no1=sonuc.getInt("h1.işlem_no");
                int işlem_no2=sonuc.getInt("h2.işlem_no");
                
                counter++;
                cikti.add(new deadlock_bilgi(işlem_no1,işlem_no2,counter));
                
            }
            return cikti;
           
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            
            return null;
        }
          
         
      }
    
     
     public ArrayList<temsilcibilgi> MusteriGetir() {
        
        ArrayList<temsilcibilgi> cikti = new ArrayList<temsilcibilgi>();
        String sorgu =  "Select * From müşteri";
        
        try {
            statement =  con.createStatement();
            
            
            ResultSet rs =  statement.executeQuery(sorgu);
            
            while(rs.next()) {
                String tcno=rs.getString("müşteri_tcno");
                String ad = rs.getString("ad");
                String soyad = rs.getString("soyad");
               
                
                cikti.add(new temsilcibilgi(tcno, ad, soyad));
                
                
            }
            return cikti;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
        
        
        
        
    }
     public ArrayList<işlembilgi> işlem_tablo_getir()
     {
          ArrayList<işlembilgi> cikti = new ArrayList<işlembilgi>();
        String sorgu =  "Select * From işlem";
        
        try {
            statement =  con.createStatement();
            
            
            ResultSet rs =  statement.executeQuery(sorgu);
            
            while(rs.next()) {
                int işlem_no=rs.getInt("işlem_no");
                String işlem=rs.getString("işlem");
                int hedef=rs.getInt("hedef");
                int kaynak=rs.getInt("kaynak");
                int tutar=rs.getInt("tutar");
                int tarih=rs.getInt("tarih");
                int kaynak_bakiye=rs.getInt("kaynak_bakiye");
                int hedef_bakiye=rs.getInt("hedef_bakiye");
                
                
               
                
                cikti.add(new işlembilgi(işlem_no,işlem,hedef,kaynak,tutar,tarih,kaynak_bakiye,hedef_bakiye));
                
                
            }
            return cikti;
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
        
         
     }
    
    
    

    public boolean girisYap(String TCno, String şifre) {
        String sorgu = "Select * From temsilci where temsilci_tc=? and şifre=?";
        try {
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1, TCno);
            preparedstatement.setString(2, şifre);

            ResultSet result = preparedstatement.executeQuery();

            return result.next();
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public void müşteriekle(String tcno, String ad, String soyad, String adres, String eposta, String telno, String şifre, int temsilcino) {
        String sorgu = "Insert Into müşteri (müşteri_tcno,ad,soyad,eposta,adres,telefon,temsilci_no,şifre) VALUES(?,?,?,?,?,?,?,?)";
        try {
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1, tcno);
            preparedstatement.setString(2, ad);
            preparedstatement.setString(3, soyad);
            preparedstatement.setString(4, eposta);
            preparedstatement.setString(5, adres);
            preparedstatement.setString(6, telno );
            preparedstatement.setInt(7, temsilcino);
            preparedstatement.setString(8, şifre);
           
            
            preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    public void müşterisil(String tcno)
    {
        String sorgu = "Delete from müşteri where müşteri_tcno = ?";
        
        try {
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1, tcno);
            
            preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void hesapsil(String hesapno)
    {
         String sorgu="Delete from hesap where hesap_no = ?";
        try {
           
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1, hesapno);
            preparedstatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void hesapekle(String hesapno,String tcno,String parabirimi,int bakiye)
    {
        String sorgu = "Insert Into hesap (hesap_no,müşteri_tc,bakiye,para_birimi) VALUES(?,?,?,?)";
        try {
            
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1, hesapno);
            preparedstatement.setString(2, tcno);
            preparedstatement.setInt(3, bakiye);
            preparedstatement.setString(4, parabirimi);
            
            preparedstatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public boolean müşterigüncelle(String ad,String soyad,String tcno,String adres,String eposta,String telno)
    {
         String sorgu =  "Update müşteri set ad = ? , soyad= ? , eposta = ?, adres = ? , telefon = ?  where müşteri_tcno = ? ";
        try {
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1,ad);
            preparedstatement.setString(2,soyad);
            preparedstatement.setString(3,eposta);
            preparedstatement.setString(4,adres);
            preparedstatement.setString(5,telno);
            
            
            preparedstatement.setString(6, tcno);
            
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
         return false;
    }
    public boolean kurgüncelle(String kur,int kur_degeri)
    {
        try {
            String sorgu="Update kur set kur= ? where para_birimi=? ";
            preparedstatement=con.prepareStatement(sorgu);
            preparedstatement.setInt(1, kur_degeri);
            preparedstatement.setString(2, kur);
            
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean maaşdeğiştir(int maaş)
    {
        String temsilci="temsilci";
        String sorgu="Update çalışan_maaş set maaş=? where çalışan_türü='"+temsilci+"' ";
        try {
            preparedstatement=con.prepareStatement(sorgu);
            preparedstatement.setInt(1, maaş);
            
            preparedstatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public int bakiye_gör(String hesapno)
    {
        int bakiye = 0;
        
        try {
            String sorgu="Select * from hesap where  hesap_no= ?";
            preparedstatement=con.prepareStatement(sorgu);
            
            preparedstatement.setString(1, hesapno);
            ResultSet result = preparedstatement.executeQuery();
           while(result.next())
           {
               bakiye=result.getInt("bakiye");
           }
            //System.out.println("bakiye"+bakiye);
            return bakiye;
        } catch (SQLException ex) {
            
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }    
    
    public boolean para_gönder(String kaynak_hesap_no,String hedef_hesap_no,int tutar,int tarih,String para_birimi)
    {
        int khesapno=Integer.valueOf(kaynak_hesap_no);
        int hhesapno=Integer.valueOf(hedef_hesap_no);
        int kaynak_bakiye = 0;
        int yeni_tutar;
        int yeni_bakiye;
        try {
            String sorgu="Select * from hesap where  hesap_no= ?";
            preparedstatement=con.prepareStatement(sorgu);
            
            preparedstatement.setString(1, kaynak_hesap_no);
            ResultSet result = preparedstatement.executeQuery();
           while(result.next())
           {
               kaynak_bakiye=result.getInt("bakiye");
           }
            //System.out.println("bakiye"+bakiye);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        yeni_bakiye=kaynak_bakiye-tutar;
        bakiye_güncelle(yeni_bakiye,kaynak_hesap_no);
        //hedef_bakiye_bul(hedef_hesap_no,tutar,para_birimi);
        int hedef_bakiye = 0;
        int yeni_bakiye2 = 0;
        try {
            String sorgu="Select * from hesap where  hesap_no= ?";
            preparedstatement=con.prepareStatement(sorgu);
            
            preparedstatement.setString(1, hedef_hesap_no);
            ResultSet result = preparedstatement.executeQuery();
           while(result.next())
           {
               hedef_bakiye=result.getInt("bakiye");
           }
            //System.out.println("bakiye"+bakiye);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        if(para_birimi.equals("TL"))
        {
            yeni_bakiye2=hedef_bakiye+tutar;
        }
        if(para_birimi.equals("DOLAR"))
        {
            yeni_tutar=tutar/17;
            yeni_bakiye2=hedef_bakiye+tutar;
        }
        if(para_birimi.equals("EURO"))
        {
            yeni_tutar=tutar/15;
            yeni_bakiye2=hedef_bakiye+tutar;
        }
        bakiye_güncelle(yeni_bakiye2,hedef_hesap_no);
        işlem_tablo_ekle_gönder(hhesapno,khesapno,tutar,tarih,kaynak_bakiye,hedef_bakiye);
        return true;
    }
     public void işlem_tablo_ekle_gönder(int hedef_hesap_no,int kaynak_hesap_no,int tutar,int tarih,int kaynak_bakiye,int hedef_bakiye)
     {
         String paragönder="Para Gönderme";
         String sorgu = "Insert Into işlem (işlem,hedef,kaynak,tutar,tarih,kaynak_bakiye,hedef_bakiye) VALUES(?,?,?,?,?,?,?)";
        try {
            
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1, paragönder);
            preparedstatement.setInt(2, hedef_hesap_no);
            preparedstatement.setInt(3, kaynak_hesap_no);
            preparedstatement.setInt(4, tutar);
            preparedstatement.setInt(5, tarih);
            preparedstatement.setInt(6, kaynak_bakiye);
            preparedstatement.setInt(7, hedef_bakiye);
            
            
            preparedstatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
     
     public boolean para_yatır(String hesap_no,int tutar,int tarih)
     {
         int bakiye = 0;
        int yeni_bakiye;
        int kaynak_hesap_no=Integer.valueOf(hesap_no);
        try {
            String sorgu="Select * from hesap where  hesap_no= ?";
            preparedstatement=con.prepareStatement(sorgu);
            
            preparedstatement.setString(1, hesap_no);
            ResultSet result = preparedstatement.executeQuery();
           while(result.next())
           {
               bakiye=result.getInt("bakiye");
           }
            //System.out.println("bakiye"+bakiye);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        yeni_bakiye=bakiye+tutar;
        bakiye_güncelle(yeni_bakiye,hesap_no);
        işlem_tablo_ekle_yatır(kaynak_hesap_no,tutar,tarih,bakiye);
        return true;
     }
     public void işlem_tablo_ekle_yatır(int kaynak_hesap_no,int tutar,int tarih,int bakiye)
     {
         String parayatır="Para Yatırma";
         int hedef_hesap_no=0;
         int hedef_bakiye=0;
         String sorgu = "Insert Into işlem (işlem,hedef,kaynak,tutar,tarih,kaynak_bakiye,hedef_bakiye) VALUES(?,?,?,?,?,?,?)";
        try {
            
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1, parayatır);
            preparedstatement.setInt(2, hedef_hesap_no);
            preparedstatement.setInt(3, kaynak_hesap_no);
            preparedstatement.setInt(4, tutar);
            preparedstatement.setInt(5, tarih);
            preparedstatement.setInt(6, bakiye);
            preparedstatement.setInt(7, hedef_bakiye);
            
            
            preparedstatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        
     }
    
    
    public boolean para_çek(String kaynak_hesap_no,int tutar,int tarih)
    {
        //bakiyeyi alıyorum burada
        
        int bakiye = 0;
        int yeni_bakiye;
        try {
            String sorgu="Select * from hesap where  hesap_no= ?";
            preparedstatement=con.prepareStatement(sorgu);
            
            preparedstatement.setString(1, kaynak_hesap_no);
            ResultSet result = preparedstatement.executeQuery();
           while(result.next())
           {
               bakiye=result.getInt("bakiye");
           }
            //System.out.println("bakiye"+bakiye);
            
        } catch (SQLException ex) {
            
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        yeni_bakiye=bakiye-tutar;
        bakiye_güncelle(yeni_bakiye,kaynak_hesap_no);
        işlem_tablo_ekle(kaynak_hesap_no,tutar,tarih,bakiye);
        return true;
    }
    public void bakiye_güncelle(int bakiye,String hesap_no)
    {
         try {
            String sorgu="Update hesap set bakiye= ? where hesap_no= ? ";
            preparedstatement=con.prepareStatement(sorgu);
            preparedstatement.setInt(1, bakiye);
            preparedstatement.setString(2, hesap_no);
            
            preparedstatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
           
        }
    }
    
    public void işlem_tablo_ekle(String kaynak_hesap_no,int tutar,int tarih,int bakiye)
    {
        int kaynakhesapno=Integer.valueOf(kaynak_hesap_no);
        String paraçekme="Para Çekme";
        int nulll=0;
        int nulll2=0000;
        String sorgu = "Insert Into işlem (işlem,hedef,kaynak,tutar,tarih,kaynak_bakiye,hedef_bakiye) VALUES(?,?,?,?,?,?,?)";
        try {
            
            preparedstatement = con.prepareStatement(sorgu);
            preparedstatement.setString(1, paraçekme);
            preparedstatement.setInt(2, nulll);
            preparedstatement.setInt(3, kaynakhesapno);
            preparedstatement.setInt(4, tutar);
            preparedstatement.setInt(5, tarih);
            preparedstatement.setInt(6, bakiye);
            preparedstatement.setInt(7, nulll2);
            
            
            preparedstatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(bağlantı.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
   
    
    
    

    public bağlantı() {

        String url = "jdbc:mysql://" + bağlantı.host + ":" + bağlantı.port + "/" + bağlantı.db_ismi + "?useUnicode=true&characterEncoding=utf8";

        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Bulunamadı....");
        }

        try {
            con = DriverManager.getConnection(url, bağlantı.kullanici_adi, bağlantı.parola);
            System.out.println("Bağlantı Başarılı...");

        } catch (SQLException ex) {
            System.out.println("Bağlantı Başarısız...");
            //ex.printStackTrace();
        }

    }
    
    
    
   
    
 
}