package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial {
    //se respectă modelul matematic al unui polinom ca fiind format din monoame
  private ArrayList<Monomial> polynomial=new ArrayList<>();
  

  //se adauga monomul nul
    public Polynomial(){
        this.polynomial.add(new MonomialInt(0,0));
    }

    public Polynomial(ArrayList<Monomial> monomials){
        for(Monomial m:monomials) {
            //pot exista duplicate la nivel de grad; in acest caz, metoda private addNewElement va rezolva acest caz
            this.addNewElement(m);
        }
        if(this.polynomial.isEmpty()){
            polynomial.add(new MonomialInt(0,0));
        }
    }

    //metoda publica care adauga o copie a unui monom in lista de monoame
    public void addElement(final Monomial m){
        if(m instanceof MonomialInt )
             this.addNewElement(new MonomialInt(m));
        else
            this.addNewElement(new MonomialDouble(m));

        if(this.polynomial.isEmpty()){
            polynomial.add(new MonomialInt(0,0));
        }
    }
    //auxiliar pentru adaugare element in lista de polinoame
    //in cazul in care exista deja un element cu gradul acelasi ca al monomului transmis ca parametru se va face
    //operatia de adunare intre aceste monoame
    private void addNewElement(Monomial m){
        if(polynomial.contains(m)) {
            int index = polynomial.indexOf(m);
            Monomial aux = m.addMonomial(polynomial.get(index));
            polynomial.remove(index);
            if (!aux.isNull())
                polynomial.add(index, aux);
        }else{
             polynomial.add(m);
        }
        this.maxMinOrder();
    }

    public ArrayList<Monomial> getPolynomial() {
        return polynomial;
    }


    //returneaza o copie a termenului dominant
    public Monomial leadingMon(){
        Monomial m=null;
        Integer maxDegree=0;
        for(Monomial monomial: this.polynomial){
            if(monomial.getDegree()>=maxDegree){
                maxDegree=monomial.getDegree();
                m=monomial;
            }
        }
        if(m instanceof MonomialInt){
            return new MonomialInt(m);
        }else if(m instanceof MonomialDouble)
            return new MonomialDouble(m);
        return  null;
    }

    //gradul polinomului este gradul termenului dominant
    public Integer maxDegree(){
        Monomial m=this.leadingMon();
        return m.getDegree();
    }

    //verificaă daca polinomul e nul; adică are termenul dominant monomul null
    public boolean isNull(){
       Monomial m=this.leadingMon();
       if(m.isNull()){
           return true;
       }
       return false;
    }

    //ordonează monoamele polinomului în ordine crescătoare a gradelor
    public void minMaxOrder(){
        Collections.sort(polynomial,new MinMaxDegreeComp());
    }
    //ordonează monoamele polinomului în ordine descrescătoare a gradelor in lista
    public void maxMinOrder(){ Collections.sort(polynomial); }
    @Override
    public String toString(){
        //se ordoneaza in orddinea descrescatoare a gradelor
        Collections.sort(polynomial);
        String output="";
        for(Monomial m:polynomial){
            output=output+m;
        }
        if(output.equals(""))
            return "0";
        return output;
    }

    //comparator pentru ordonare grade de la cel mai mic la cel mai mare; folositor pentru derivare
    //clasă internă
    public class MinMaxDegreeComp implements Comparator<Monomial> {
        @Override
        public int compare(Monomial o1, Monomial o2) {
            return -o1.compareTo(o2);
        }
    }
}
