package model;

//clasa ce are implementate a operațiile pe polinoame
//in lista de parametri a metodelor este specificat atributul final ce ii garantează utilizatorului că polinoamele trimise
//ca paramtri nu vor fi modificate
public class Operations {

    //operatia de adunare
    public static Polynomial addPolynomialOperation(final Polynomial p1, final Polynomial p2) {
        Polynomial result = new Polynomial(p1.getPolynomial());

        for (Monomial monomial : p2.getPolynomial()) {
            result.addElement(monomial);
        }
        return result;
    }

    //operația de scadere se realizează similar cu cea de adunare numai ca se schimba semnul monoamelor celui de-al doilea
    //polinom
    public static  Polynomial subPolynomialOperation(final Polynomial p1, final Polynomial p2) {
        Polynomial result = new Polynomial(p1.getPolynomial());
        for (Monomial monomial : p2.getPolynomial()) {
            result.addElement( monomial.changeSign());
        }
        return result;
    }

    //fiecare monom din lista lui p1 să se înmulțească cu fiecare monom din lista lui p2
    public static  Polynomial multiplyPolynomialOperation(final Polynomial p1, final Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial mon1 : p1.getPolynomial()) {
            for (Monomial mon2 : p2.getPolynomial()) {
                result.addElement(mon1.mulMonomial(mon2));
            }
        }
        return result;
    }

    //implementarea metodei se bazează pe algoritmul de împărțire
    //in cazul în care cel de-al doilea polinom econține doar monomul nul atuci se aruncă o excepțue de tipul ArithmeticException
    //pentru calculul restului în cazul iterațiilor buclei while se folosesc metodele de adunare, scădere și înmulțire
    // definite anterior
    public static  Polynomial[] divPolynomialOperation(final Polynomial p1, final Polynomial p2)  throws NullPointerException, ArithmeticException{
        if (!p2.isNull()) {
            Polynomial[] result = new Polynomial[2];
            result[0]=new Polynomial();                            //q
            result[1] = new Polynomial(p1.getPolynomial());        //r
            Polynomial d = new Polynomial(p2.getPolynomial());     //d

            while (!result[1].isNull() && result[1].maxDegree() >= d.maxDegree()) {
                Monomial t=result[1].leadingMon();
                t=t.divMonomial(d.leadingMon());
                Polynomial T=new Polynomial();
                T.addElement(t);
                result[0]=addPolynomialOperation(result[0],T);
                result[1] = subPolynomialOperation(result[1],multiplyPolynomialOperation(T,d));
            }
            return result;
        }
        throw new ArithmeticException();
    }
    //se face o copie a polinomului și se ordonează monoamele din listă în ordine crescătoare
    //implementarea de fața nu avea nevoie de această ordonare explicita, dar în cazul în care se dorește să se modifice
    //polinomul transmis ca parametru era nevoie
    public static  Polynomial dervPolynomialOperation( final Polynomial p ){
        Polynomial result=new Polynomial();
        Polynomial aux=new Polynomial(p.getPolynomial());
        aux.minMaxOrder();
        for(Monomial mon: aux.getPolynomial()){
            result.addElement(mon.dervMonomial());
        }
        return result;
    }

    //e similară cu metoda de derivare
    public static  Polynomial integratePolynomialOperation(final Polynomial p){
        Polynomial aux=new Polynomial(p.getPolynomial());
        Polynomial result=new Polynomial();
        aux.maxMinOrder();
        for(Monomial mon: aux.getPolynomial()){
            result.addElement(mon.integrateMonomial());
        }
        return result;
    }
}
