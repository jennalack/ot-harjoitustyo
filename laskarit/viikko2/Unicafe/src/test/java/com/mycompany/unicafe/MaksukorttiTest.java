package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosRahaaTarpeeksi() {
        Maksukortti kortti2 = new Maksukortti(10000);
        kortti2.otaRahaa(240);
        assertEquals("saldo: 97.60", kortti2.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi(){
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaTrueJosRahatRiittava() {
        Maksukortti kortti2 = new Maksukortti(10000);
//        kortti2.otaRahaa(240);
        assertEquals(true, kortti2.otaRahaa(240));
    }
    
    @Test
    public void metodiPalauttaaFalseJosRahatEivatRiita() {
        assertEquals(false, kortti.otaRahaa(10230));
    }
}
