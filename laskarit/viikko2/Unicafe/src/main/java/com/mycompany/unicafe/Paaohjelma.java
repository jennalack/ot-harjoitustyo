package com.mycompany.unicafe;

public class Paaohjelma {

    public static void main(String[] args) {
        Kassapaate unicafeExactum = new Kassapaate();
        Maksukortti kortti = new Maksukortti(10000);
        unicafeExactum.lataaRahaaKortille(kortti, 10);

        unicafeExactum.syoEdullisesti(kortti);
        
        System.out.println( unicafeExactum.edullisiaLounaitaMyyty() );
        System.out.println(kortti);
    }
}
