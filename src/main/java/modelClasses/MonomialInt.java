package modelClasses;

public class MonomialInt extends Monomial{

    private Integer intCoeficient;
    static final double epsilon=0.00001;
    public MonomialInt(Integer coefficient, Integer degree){
        super(degree);
        this.intCoeficient=coefficient;
    }
    public MonomialInt(Monomial m){
        super(m.getDegree());
        this.intCoeficient=(Integer)m.getCoefficient();
    }

    @Override
    public Monomial addMonomial(Monomial m) {
        if(this.degree.equals(m.getDegree())){
            if(m instanceof MonomialDouble) {
                Double newCoeff=(Double) m.getCoefficient() + this.intCoeficient;
                return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                                                                               new MonomialDouble(newCoeff,this.degree);
            }
            Integer newCoeff=(Integer)m.getCoefficient()+this.intCoeficient;
            return newCoeff==0? new MonomialInt(0,0):new MonomialInt(newCoeff,this.degree);
        }
        return null;
    }

    @Override
    public Monomial subMonomial(Monomial m) {
        if(degree.equals(m.getDegree())){
            if(m instanceof MonomialDouble) {
                Double newCoeff= this.intCoeficient- (Double)m.getCoefficient();
                return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                        new MonomialDouble(newCoeff,this.degree);
            }
            Integer newCoeff=this.intCoeficient-(Integer)m.getCoefficient();
            return newCoeff==0? new MonomialInt(0,0):new MonomialInt(newCoeff,this.degree);
        }
        return null;
    }

    @Override
    public Monomial mulMonomial(Monomial m) {
        if(m instanceof MonomialDouble) {
            Double newCoeff= this.intCoeficient*(Double)m.getCoefficient();
            return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                    new MonomialDouble(newCoeff,this.degree+m.getDegree());
        }
        Integer newCoeff=this.intCoeficient*(Integer)m.getCoefficient();
        return newCoeff==0? new MonomialInt(0,0):new MonomialInt(newCoeff,this.degree+m.getDegree());
    }

    @Override
    public Monomial divMonomial(Monomial m) {
        if(m instanceof MonomialDouble) {
            Double newCoeff= this.intCoeficient/(Double)m.getCoefficient();
            return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                    new MonomialDouble(newCoeff,this.degree-m.getDegree());
        }
        if(this.intCoeficient%(Integer)m.getCoefficient()!=0) {
            Double newCoeff= 1.0*this.intCoeficient/(Integer)m.getCoefficient();
            return Math.abs(newCoeff)<=epsilon? new MonomialDouble(0.0,0):
                    new MonomialDouble(newCoeff,this.degree-m.getDegree());
        }
        Integer newCoeff=this.intCoeficient/(Integer)m.getCoefficient();
        return newCoeff==0? new MonomialInt(0,0):new MonomialInt(newCoeff,this.degree-m.getDegree());
    }

    @Override
    public Monomial dervMonomial() {
        if(this.degree==0){
            return new MonomialInt(0,0);
        }else{
            Integer newCoeff=this.intCoeficient*this.degree;
            return new MonomialInt(newCoeff, this.degree-1);
        }
    }

    @Override
    public Monomial integrateMonomial() {
        if(this.intCoeficient==0)
            return new MonomialInt(0,0);
        if(this.intCoeficient%(this.degree+1)==0){
            Integer newCoeff=this.intCoeficient/(this.degree+1);
            return new MonomialInt(newCoeff, this.degree+1);
        }
        Double newCoeff=1.0*this.intCoeficient/(this.degree+1);
        return new MonomialDouble(newCoeff,this.degree+1);
    }

    @Override
    public Monomial changeSign() {
        return new MonomialInt((-1)*this.intCoeficient,this.degree);
    }
    @Override
    public Number getCoefficient(){
        return this.intCoeficient;
    }
    @Override
    public boolean isNull(){
        return this.intCoeficient == 0;
    }

    @Override
    public String toString(){
        String partCoeff="";
        if(this.intCoeficient==0){
            return "";
        }
        if( this.intCoeficient==1) {
            partCoeff = this.degree == 0 ? "+1" :"+";}
        else if( this.intCoeficient==-1) {
                partCoeff = this.degree == 0 ? "-1" :"-";
        }else {
            partCoeff=this.intCoeficient>0? "+":"";
            partCoeff += this.intCoeficient;
        }
        String partDegree= degree==0? "":degree==1? "X":"X^"+degree.toString();
        return partCoeff+partDegree;
    }
}
