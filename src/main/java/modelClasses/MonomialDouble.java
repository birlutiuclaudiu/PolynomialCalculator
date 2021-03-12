package modelClasses;

public class MonomialDouble extends Monomial{

    private Double doubleCoeff=null;
    static final double epsilon=0.00001;

    public MonomialDouble(Double doubleCoeff, Integer degree){
        super(degree);
        this.doubleCoeff=doubleCoeff;
    }

    public MonomialDouble(Monomial m){
        super(m.getDegree());
        this.doubleCoeff=(Double) m.getCoefficient();
    }

    @Override
    public Monomial addMonomial(Monomial m) {
        if(degree.equals(m.getDegree())){
            Double newCoeff;
            if(m instanceof MonomialInt) {
                 newCoeff=(Integer) m.getCoefficient() + this.doubleCoeff;
            }else{
                 newCoeff=(Double) m.getCoefficient() + this.doubleCoeff;
            }
            return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                        new MonomialDouble(newCoeff,this.degree);
            }
        return null;
    }

    @Override
    public Monomial subMonomial(Monomial m) {
        if(degree.equals(m.getDegree())){
            Double newCoeff;
            if(m instanceof MonomialInt) {
                newCoeff=-1.0*(Integer) m.getCoefficient() + this.doubleCoeff;
            }else{
                newCoeff=-1.0*(Double) m.getCoefficient() + this.doubleCoeff;
            }
            return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                    new MonomialDouble(newCoeff,this.degree);
        }
        return null;
    }

    @Override
    public Monomial mulMonomial(Monomial m) {
        Double newCoeff;
        if(m instanceof MonomialInt) {
            newCoeff=(Integer) m.getCoefficient() * this.doubleCoeff;
        }else{
            newCoeff=(Double) m.getCoefficient() * this.doubleCoeff;
        }
        return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                    new MonomialDouble(newCoeff,this.degree+m.getDegree());
    }

    @Override
    public Monomial divMonomial(Monomial m) {
        Double newCoeff;
        if(m instanceof MonomialInt) {
            newCoeff= this.doubleCoeff/(Integer) m.getCoefficient();
        }else{
            newCoeff= this.doubleCoeff/(Double) m.getCoefficient() ;
        }
        return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                      new MonomialDouble(newCoeff,this.degree-m.getDegree());
    }

    @Override
    public Monomial dervMonomial() {
        return null;
    }

    @Override
    public Monomial integrateMonomial() {
        return null;
    }

    @Override
    public Monomial changeSign() {
        return new MonomialDouble((-1.0)*this.doubleCoeff,this.degree);
    }

    @Override
    public Number getCoefficient(){
        return this.doubleCoeff;
    }

    @Override
    public boolean isNull(){
        if(Math.abs(this.doubleCoeff)<=epsilon)
            return true;
        return false;
    }

    @Override
    public String toString(){
        String partCoeff="";

        if(Math.abs(this.doubleCoeff)<=epsilon){
            return "";
        }
        if( Math.abs(this.doubleCoeff-1.0)<=epsilon) {
                partCoeff = this.degree == 0 ? "+1" : "+";
        }else {
                partCoeff=this.doubleCoeff>0.0? "+":"";
                Double str=Math.round(doubleCoeff*100.0)/100.0;
                partCoeff += str.toString();
        }
        String partDegree= degree==0? "":degree==1? "X":"X^"+degree.toString();
        return partCoeff+partDegree;
    }
}
