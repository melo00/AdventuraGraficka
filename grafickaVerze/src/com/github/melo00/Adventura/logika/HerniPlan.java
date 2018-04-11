package com.github.melo00.Adventura.logika;

import java.util.Observable;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan extends Observable{
    private Prostor aktualniProstor;
    private Batoh batuzek;
    private Hra hra;
    private boolean zavrenaMistnost;
    private boolean hudba;
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan(Hra hra) {
        zalozProstoryHry();
        batuzek = new Batoh();
        this.hra = hra;
        zavrenaMistnost = false;
        hudba = false;
    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor pokoj = new Prostor("Harryho_pokoj","Harryho pokoj - Zde harry spí a bydlí",100,0);
        Prostor kabinet = new Prostor("Snapuv_kabinet","Kabinet profesora Snapa plný různých lektvarů a podivných věcí",0,50);
        Prostor knihovna = new Prostor("Knihovna","Obrovská knihovna plná knih všech tvarů / velikostí / žánrů",150,70);
        Prostor hagrid = new Prostor("Hagridův_dům","Zde bydlí hagrid s jeho psem. Dům je plný podivných věcí",230,130);
        Prostor quidditch = new Prostor("Herni_mistnost","Herní místnost ve které se hraje quidditch",150,180);
        Prostor castZamku = new Prostor("Pokoj_pred_komnatou","Uzavřená část bradavid, která není studentům přístupna",350,180);
        Prostor tajnaKomnata = new Prostor("Tajemná_komnata","Tajemná komnata v bradavicích - místo kde sídlí Voldemort.",420,250);
        
        
        
        // přiřazují se průchody mezi prostory (sousedící prostory)
        pokoj.setVychod(kabinet);
        pokoj.setVychod(knihovna);
        knihovna.setVychod(pokoj);
        knihovna.setVychod(hagrid);
        hagrid.setVychod(knihovna);
        hagrid.setVychod(quidditch);
        hagrid.setVychod(castZamku);
        castZamku.setVychod(hagrid);
        castZamku.setVychod(tajnaKomnata);
        
                
        aktualniProstor = pokoj;    
        
        Vec kniha = new Vec("kniha_nicolase_flaminga",true,"kniha1.jpg");
        Vec kniha2 = new Vec("časopis_svět_magie",true,"kniha2.jpg");
        Vec kniha3 = new Vec("kniha_černé_magie",true,"kniha3.jpg");
        Vec sperk = new Vec("tajemný_šperk",true,"sperk.jpg");
        Vec cd = new Vec("cd_s_hudbou",true,"cd.jpg");
        Vec zidle = new Vec("židle",false,"zidle.jpg");
        Vec stul = new Vec("stůl",false,"stul.jpg");
        
        hagrid.vlozVec(zidle);
        hagrid.vlozVec(stul);
        knihovna.vlozVec(kniha);
        knihovna.vlozVec(kniha2);
        knihovna.vlozVec(kniha3);
        pokoj.vlozVec(cd);
        tajnaKomnata.vlozVec(sperk);
        
        
        
    }
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;

    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
       aktualniProstor = prostor;
    }
    // Metoda vrací batoh
    public Batoh getBatoh() 
    {
       return this.batuzek;
    }
    // Metoda vrací hru kterou hrajeme
    public Hra getHra()
    {
       return this.hra;
    }
    
    // Metoda otevírá místnost
    public void Otevri()
    {
        this.zavrenaMistnost = true;
    }
    // Metoda vrací stav zavřené místnosti
    public boolean getZavrenaMistnost()
    {
        return this.zavrenaMistnost;
    }
    
    // Metoda zapíná hudbu
    public void zapniHudbu()
    {
        this.hudba = true;
    }
    // Metoda vrací stav hudby
    public boolean getHudba()
    {
        return this.hudba;
    }
    
    @Override
    public void notifyObservers(){
        setChanged();
        super.notifyObservers();
    }
    
    
    
    }

