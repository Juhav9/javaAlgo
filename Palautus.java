package com.mycompany.palautus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class Palautus {

    public static void main(String[] args){
            Random rand = new Random();
            ArrayList <NumberClass> list = new ArrayList<>();
            
            int x,y,z,c = 0;
            
             for (int i = 0; i<8; i++){
                     
                     x=rand.nextInt(2);
                     y=rand.nextInt(2);
                     z=rand.nextInt(2);
                     c=rand.nextInt(2);
                     //luodaan oliota joilla on neljä int ominaisuutta 
                     // ominuuksille annetaan arvot 1 tai 0
                     // luodut oliot tallennetaan ArrayListiin 
                     NumberClass n  = new NumberClass(x, y, z, c);
                     list.add(n);
                     
             }
            // Oliot järjestetaan ensin X suunnan summan mukaan eli ominaisuuksien summan mukaan
            // sitten Y suunnan summan mukaan
             Collections.sort(list, new NumberClass.OrderBySum().thenComparing(new NumberClass.OrderByYSum()));
             
             
             // tulostetaan listan "taulukoksi"  josta käy ilmi olioitten summat 
             for (NumberClass numberClass : list) {
                     System.out.println(numberClass.toString());
             }
             
             // Y suunnan summan tulostus
          for(int i = 0; i<1;i++){
                  list.get(i).printYSum();
          }
    }
}
