package com.mycompany.palautus;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NumberClass {
        // pidetään luku Y suunnan summasta 
        static List<Integer> SUM_Y_LIST = new ArrayList<>();
        // luokalla neljä int tyyppistä ominaisuutta
        // sisäinen taulukkoa käytetään selvittämää
        // osallisuutta Ysuunnan summaan
        private int num1;
        private int num2;
        private int num3;
        private int num4;
        private  int []numbers;

        public NumberClass(int num1, int num2, int num3, int num4)  {
                this.num1 = num1;
                this.num2 = num2;
                this.num3 = num3;
                this.num4 = num4;
                this.numbers = setNumbersToArray();
                // päivittää Y suunnan summaa aina olion luodessa
                updateArray();
        }

        public int getNum1() {
                return num1;
        }

        public int getNum2() {
                return num2;
        }

        public int getNum3() {
                return num3;
        }

        public int getNum4() {
                return num4;
        }
        
        // palutattaa X suunnan summan
        private  int Sum(){
                int sum = this.getNum1() + this.getNum2()+ this.getNum3()+ this.getNum4();
                
                return sum;
         }
        //jokaisella luodulla oliolla on luokan sisäinen pirvaatti taulukko pitämässä kirjaa luvuista
        private int [] setNumbersToArray(){
              int [] nums = {getNum1(),getNum2(),getNum3(),getNum4()};
                return nums;
        }
        //päivittää Y suunnan summa  ArrayListä 
        // käytetty Javan valmista ArrayListä, koska päivittäminen on helpompaa
        private void updateArray(){
                int temp = 0;
                // ensimmäistä oliota tehdessä lisätään vain arvot ArrayListiin
                if(SUM_Y_LIST.size()==0){
                        for (int number : this.numbers) {
                                SUM_Y_LIST.add(temp);
                        }
                }
                // muuten olion Xsuunnan taulukon arvot listään vastaavaan indexiin Y suunnan taulukkoon
                for(int i =0; i<this.numbers.length;i++){
                        temp = this.numbers[i];
                        temp += SUM_Y_LIST.get(i);
                        SUM_Y_LIST.set(i, temp);
                }
                
        }
        
        // tulostaa Y suunnan summan
        public  void printYSum(){
                System.out.println("");
                for (Integer integer : SUM_Y_LIST) {
                        System.out.print("| " + integer + " |");
                }
                System.out.println("");
        }

        @Override
        public String toString() {
                int sum = this.Sum();
                return  "| " + num1 + "  |  " + num2 + "  |  " + num3 + "  |  " + num4+"  |  = " + sum ;
        }
        
        static  class OrderBySum implements Comparator<NumberClass>{
                // lajittelee oliot olion sisältämien arvojen summan mukaan.. siten että suurin ensimmäisenä
                @Override
                public int compare(NumberClass o1, NumberClass o2) {
                        int  sumO1 = o1.Sum();
                        int  sumO2 = o2.Sum();

                        return  Integer.compare(sumO2, sumO1);
                }
                
        }
        
        static class  OrderByYSum implements  Comparator<NumberClass>{
                // lajitellaan oliot Y suunnan summan mukaan, siten että jos olio on tekijänä suurempaa Ysuunnan
            
                
                @Override
                public int compare(NumberClass o1, NumberClass o2) {
                        //jos molemmat  tai jompi kumpi Xsuunnan summasta on nolla 
                        if(o1.Sum()==0&&o2.Sum()==0){return 0;}
                        if(o1.Sum()==0){return -1;}
                        if(o2.Sum()==0){return 1;}
                        
                        
                        // käytetään tarkastaessa suurinta arvoa Ysuunnassa
                        int hightestValue=0;
                        // lajittelijan palauttama arvo
                        int returnedValue=0;
                        
                        // luodaan Y-suunnan summa listasta kopio 
                        // jotta saadaa etsittyä arvoa suurimmasta pieninpään
                        // lista pitää kopioda jotta suurimman arvon nollaus onnistuu 
                        // vaikuttamatta luokan staattiseen listaan..
                        List<Integer>tempList = new ArrayList<>(SUM_Y_LIST);
                        
                        for(int i =0;i<SUM_Y_LIST.size();i++){
                                // etsitään listan suurinta arvoa
                                for (Integer integer : tempList) {
                                        if(integer>hightestValue){
                                                hightestValue=integer;
                                        }
                                        
                                if(hightestValue==0){break;}
                                }            
                                // käydään ensimmäinen olio(o1) läpi ja katsotaan osallistuuko Y suunnan 
                                // sen hetkiseen isoimpaan summaan
                                for (int a =0;a<o1.numbers.length;a++) {
                                        if(o1.numbers[a]!=0&&SUM_Y_LIST.get(a)==hightestValue){
                                                returnedValue=-1;
                                                break;
                                        }
                                }
                                // Sitten tarkastetaan seuraava olio(o2)
                                // o2 tarkastuksessa huomioitu myös molempien osallistuminen summaan 
                                // jolloin oliot ovat "yhtäsuuren" 
                                for (int a =0;a<o2.numbers.length;a++) {
                                        if(o2.numbers[a]!=0&&SUM_Y_LIST.get(a)==hightestValue&&returnedValue==0){
                                                returnedValue=1;
                                                break;
                                        }
                                        if(o2.numbers[a]!=0&&SUM_Y_LIST.get(a)==hightestValue&&returnedValue!=0){
                                                returnedValue=0;
                                                break;
                                        }
                                        
                                }
                                if(returnedValue!=0){break;}
                                // nollataan listan sen hetkinen suurin arvo
                                // kaikissa indekseissä
                                for (Integer integer : tempList) {
                                        if (integer==hightestValue){
                                                tempList.set(tempList.indexOf(integer), 0);
                                        }  
                                }
                                hightestValue=0;
                        }

                        return returnedValue;
                }
                
        }
        
}

