/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author BeyzaNurCansever
 */
public class işlembilgi {
    private int işlemno;
    private String işlem;
    private int hedef;
    private int kaynak;
    private int tutar;
    private int tarih;
    private int kaynakbakiye;
    private int hedefbakiye;

    public işlembilgi(int işlemno, String işlem, int hedef, int kaynak, int tutar, int tarih, int kaynakbakiye, int hedefbakiye) {
        this.işlemno = işlemno;
        this.işlem = işlem;
        this.hedef = hedef;
        this.kaynak = kaynak;
        this.tutar = tutar;
        this.tarih = tarih;
        this.kaynakbakiye = kaynakbakiye;
        this.hedefbakiye = hedefbakiye;
    }

    public int getIşlemno() {
        return işlemno;
    }

    public void setIşlemno(int işlemno) {
        this.işlemno = işlemno;
    }

    public String getIşlem() {
        return işlem;
    }

    public void setIşlem(String işlem) {
        this.işlem = işlem;
    }

    public int getHedef() {
        return hedef;
    }

    public void setHedef(int hedef) {
        this.hedef = hedef;
    }

    public int getKaynak() {
        return kaynak;
    }

    public void setKaynak(int kaynak) {
        this.kaynak = kaynak;
    }

    public int getTutar() {
        return tutar;
    }

    public void setTutar(int tutar) {
        this.tutar = tutar;
    }

    public int getTarih() {
        return tarih;
    }

    public void setTarih(int tarih) {
        this.tarih = tarih;
    }

    public int getKaynakbakiye() {
        return kaynakbakiye;
    }

    public void setKaynakbakiye(int kaynakbakiye) {
        this.kaynakbakiye = kaynakbakiye;
    }

    public int getHedefbakiye() {
        return hedefbakiye;
    }

    public void setHedefbakiye(int hedefbakiye) {
        this.hedefbakiye = hedefbakiye;
    }
    
    
}
