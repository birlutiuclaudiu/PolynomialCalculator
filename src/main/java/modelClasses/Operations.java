package modelClasses;

public class Operations {

    public static Polynomial addPolynomialOperation(final Polynomial p1, final Polynomial p2) {
        Polynomial result = new Polynomial(p1.getPolynomial());

        for (Monomial monomial : p2.getPolynomial()) {
            result.addElement(monomial);
        }
        return result;
    }

    public static Polynomial subPolynomialOperation(final Polynomial p1, final Polynomial p2) {
        Polynomial result = new Polynomial(p1.getPolynomial());
        for (Monomial monomial : p2.getPolynomial()) {
            result.addElement( monomial.changeSign());
        }
        return result;
    }

    public static Polynomial multiplyPolynomialOperation(final Polynomial p1, final Polynomial p2) {
        Polynomial result = new Polynomial();
        for (Monomial mon1 : p1.getPolynomial()) {
            for (Monomial mon2 : p2.getPolynomial()) {
                result.addElement(mon1.mulMonomial(mon2));
            }
        }
        return result;
    }

    public static Polynomial[] divPolynomialOperation(final Polynomial p1, final Polynomial p2)  throws NullPointerException, ArithmeticException{
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

    public static Polynomial dervPolynomialOperation( final Polynomial p ){
        Polynomial result=new Polynomial();
        Polynomial aux=new Polynomial(p.getPolynomial());
        aux.minMaxOrder();
        for(Monomial mon: aux.getPolynomial()){
            result.addElement(mon.dervMonomial());
        }
        return result;
    }
    public static Polynomial integratePolynomialOperation(final Polynomial p){
        Polynomial aux=new Polynomial(p.getPolynomial());
        Polynomial result=new Polynomial();
        aux.maxMinOrder();
        for(Monomial mon: aux.getPolynomial()){
            result.addElement(mon.integrateMonomial());
        }
        return result;
    }
}
