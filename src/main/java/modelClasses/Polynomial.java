package modelClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial {
  private ArrayList<Monomial> polynomial=new ArrayList<>();
  static final double epsilon=0.00001;

    public Polynomial(){
        this.polynomial.add(new MonomialInt(0,0));
    }

    public Polynomial(ArrayList<Monomial> monomials){
        for(Monomial m:monomials) {
            this.addNewElement(m);
        }
        if(this.polynomial.isEmpty()){
            polynomial.add(new MonomialInt(0,0));
        }
    }

    public void addElement(Monomial m){
        if(m instanceof MonomialInt )
             this.addNewElement(new MonomialInt(m));
        else
            this.addNewElement(new MonomialDouble(m));

        if(this.polynomial.isEmpty()){
            polynomial.add(new MonomialInt(0,0));
        }
    }

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
    public Integer maxDegree(){
        Monomial m=this.leadingMon();
        return m.getDegree();
    }

    public boolean isNull(){
       Monomial m=this.leadingMon();
       if(m.isNull()){
           return true;
       }
       return false;
    }

    public void minMaxOrder(){
        Collections.sort(polynomial,new MinMaxDegreeComp());
    }
    public void maxMinOrder(){
        Collections.sort(polynomial);
    }


    //comparator pentru ordonare grade de la cel mai mic la cel mai mare; folositor pentru derivare
    public class MinMaxDegreeComp implements Comparator<Monomial> {
        @Override
        public int compare(Monomial o1, Monomial o2) {
            return -o1.compareTo(o2);
        }
    }
}
