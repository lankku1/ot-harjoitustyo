
package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void rahamaaraOikea() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullistenLounaidenMaaraOikea() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaittenLounaidenMaaraOikea() {
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEdulliselleLounaalleToimii() {
        int vaihtoraha = kassa.syoEdullisesti(250);
        assertEquals(10, vaihtoraha);
        assertEquals(100240, kassa.kassassaRahaa());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoMaukkaalleLounaalleToimii() {
        int vaihtoraha = kassa.syoMaukkaasti(410);
        assertEquals(10, vaihtoraha);
        assertEquals(100400, kassa.kassassaRahaa());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoEdulliselleLounaalleEiToimi() {
        int vaihtoraha = kassa.syoEdullisesti(200);
        assertEquals(200, vaihtoraha);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoMaukkaalleLounaalleEiToimi() {
        int vaihtoraha = kassa.syoMaukkaasti(350);
        assertEquals(350, vaihtoraha);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoEdulliselleLounaalleToimii() {
        boolean tila = kassa.syoEdullisesti(kortti);
        assertEquals(true, tila);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiostoMaukaalleLounaalleToimii() {
        boolean tila = kassa.syoMaukkaasti(kortti);
        assertEquals(true, tila);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiostoEdulliselleLounaalleEiToimi() {
        Maksukortti k = new Maksukortti(200);
        boolean tila = kassa.syoEdullisesti(k);
        assertEquals(false, tila);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(200, k.saldo());
    }
    
    @Test
    public void korttiostoMaukaalleLounaalleEiToimi() {
        Maksukortti k = new Maksukortti(200);
        boolean tila = kassa.syoMaukkaasti(k);
        assertEquals(false, tila);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(200, k.saldo());
    }
    
    @Test
    public void kortinSaldoMuuttuu() {
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
        assertEquals(100100, kassa.kassassaRahaa());
    }
    
}
