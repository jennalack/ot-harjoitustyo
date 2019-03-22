/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jennalaakkonen
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(10000);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void kassassaRahaa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void tulostaaTrueKunSyoEdullisestiJaKortillaRahaa() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void tulostaaFalseKunSyoEdullisestiJaKortillaEiRahaa() {
        Maksukortti kortti2 = new Maksukortti(10);
        assertEquals(false, kassa.syoEdullisesti(kortti2));
    }
    
    @Test
    public void LounasEiKasvaKunSyoEdullisestiJaKortillaEiRahaa() {
        Maksukortti kortti2 = new Maksukortti(10);
        kassa.syoEdullisesti(kortti2);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void LounasEiKasvaKunSyoMaukkaastiJaKortillaEiRahaa() {
        Maksukortti kortti2 = new Maksukortti(10);
        kassa.syoMaukkaasti(kortti2);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
     @Test
    public void tulostaaVaihtorahanKunSyoEdullisestiJaKortillaRahaa() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }
    
     @Test
    public void kassanRahaKasvaaKunSyoEdullisestiJaKortillaRahaa() {
        kassa.syoEdullisesti(300);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
     @Test
    public void kassanRahaKasvaaKunSyoMaukkaastiJaKortillaRahaa() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void tulostaaMaksunKunSyoEdullisestiJaEiTarpeeksiRahaa() {
        assertEquals(230, kassa.syoEdullisesti(230));
    }
    
     @Test
    public void tulostaaTrueKunSyoMaukkaastiJaKortillaRahaa() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void tulostaaLounaatKunSyoMaukkaasti() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void tulostaaLounaatKunSyoEdullisesti() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void tulostaaLounaatKunSyoMaukkaastiKateisella() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void tulostaaLounaatKunSyoEdullisestiKateisella() {
        kassa.syoEdullisesti(1000);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void tulostaaFalseKunSyoMaukkaastiJaKortillaEiRahaa() {
        Maksukortti kortti2 = new Maksukortti(10);
        assertEquals(false, kassa.syoMaukkaasti(kortti2));
    }
    
     @Test
    public void tulostaaVaihtorahanKunSyoMaukkaastiJaKortillaRahaa() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }
    
    @Test
    public void tulostaaMaksunKunSyoMaukkaastiJaEiTarpeeksiRahaa() {
        assertEquals(230, kassa.syoMaukkaasti(230));
    }
    
    @Test
    public void ladataanRahaaKortille() {
        kassa.lataaRahaaKortille(kortti, 10);
        assertEquals(100010, kassa.kassassaRahaa());
    }
    
    @Test
    public void ladataanNegatiivinenSummaKortille() {
        kassa.lataaRahaaKortille(kortti, -10);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void paljonMaukkaitaMyyty() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void paljonEdullisiaMyyty() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
}
