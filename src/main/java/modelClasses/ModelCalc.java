package modelClasses;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ModelCalc {
    private Polynomial p1;
    private Polynomial p2;
    private Polynomial result;
    private Polynomial[] result2=new Polynomial[2];

    public ModelCalc(){
        result=new Polynomial();
    }

    public void addPolynomial(){
        this.result= Operations.addPolynomialOperation(p1,p2);
    }
    public void subPolynomial(){
        this.result= Operations.subPolynomialOperation(p1,p2);
    }
    public void multiplyPolynomial(){ this.result=Operations.multiplyPolynomialOperation(p1,p2); }
    public void divPolynomial() throws Exception{
        try {
            this.result2 = Operations.divPolynomialOperation(p1, p2);
        }catch(NullPointerException e) {
            this.result = new Polynomial();
            throw new Exception("Nu ati introdus valori");
        }catch(ArithmeticException ea){
            throw new Exception("Impartirea la 0 nu e posibila");
        }
    }
    public void dervPolynomial(){this.result=Operations.dervPolynomialOperation(p1 );}
    public void integratePolynomial(){this.result=Operations.integratePolynomialOperation(p1);}

     public void patternMonomialList(String s, int choice) throws NumberFormatException{
        ArrayList<Monomial> monomials=new ArrayList<>();
        String patternString = "([+-]?+(?:(?:\\d+(?:[Xx]|(?:\\*[xX]))(?:(?:\\^[1-9][0-9]*+)|(?:\\^[0]))?+)"+
                                                    "|(?:(?:[Xx])(?:(?:\\^[1-9][0-9]*+)|(?:\\^[0]))?+)|\\d+))";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            try {
                Integer[] monoCoeff=extractMonomial(matcher.group(1));
                monomials.add(new MonomialInt(monoCoeff[0], monoCoeff[1]));
            }catch (NumberFormatException exc){
                throw new NumberFormatException("Un numar a fost introdus gresit.\n " +
                                   "Verificati daca numerele introduse sunt de lungime maxim 10");
            }
        }
        if(choice==1)
            this.p1 = new Polynomial(monomials);

        else
            this.p2 = new Polynomial(monomials);
    }
    public String getResult(){
        return result.toString();
    }
    public String getCat(){
        return result2[0].toString();
    }
    public String getRest(){
        return result2[1].toString();
    }

    private Integer[] extractMonomial(String s) throws NumberFormatException {
        Integer[] monomCoeff = new Integer[2];
        monomCoeff[0] = 0;    //pentru coeficient
        monomCoeff[1] = 0;    //pentru grad
        String[] parts = s.split("((?:\\*[Xx]\\^)|(?:\\*[Xx])|(?:[Xx]\\^)|(?:[Xx]))");

        if (parts.length == 0) {
            monomCoeff[0] = 1;
            monomCoeff[1] = 1;
        } else if (parts.length == 1) {
            if (parts[0].equals("-") || parts[0].equals("+")) {
                monomCoeff[0] = Integer.parseInt(parts[0] + "1");
                monomCoeff[1] = 1;
            } else {
                monomCoeff[0] = Integer.parseInt(parts[0]);
                monomCoeff[1] = parts[0].equals(s) ? 0 : 1;
            }
        } else {
            if (parts[0].equals(""))
                monomCoeff[0] = 1;
            else if (parts[0].equals("-") || parts[0].equals("+")) {
                monomCoeff[0] = Integer.parseInt(parts[0] + "1");
            } else
                monomCoeff[0] = Integer.parseInt(parts[0]);
            if (parts[1].equals(""))
                monomCoeff[0] = 1;
            else
                monomCoeff[1] = Integer.parseInt(parts[1]);
        }
        return monomCoeff;
    }
}
