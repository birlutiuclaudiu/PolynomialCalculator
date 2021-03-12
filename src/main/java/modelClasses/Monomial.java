package modelClasses;
import java.text.DecimalFormat;
import java.util.Objects;

public abstract class Monomial implements Comparable<Monomial>{

    protected Integer degree;
    public Monomial(Integer degree){
        this.degree=degree;
    }

    public abstract Monomial addMonomial(Monomial m);
    public abstract Monomial subMonomial(Monomial m);
    public abstract Monomial mulMonomial(Monomial m);
    public abstract Monomial divMonomial(Monomial m);
    public abstract Monomial dervMonomial();
    public abstract Monomial integrateMonomial();
    public abstract Monomial changeSign();
    public abstract Number getCoefficient();
    public abstract boolean isNull();

    public Integer getDegree(){
        return this.degree;
    }

    //am pus testul de egalitate doar pe grad; astfel incat pot sa fac opertiile de adaugare in lista de de monoame a
    //polinomului mai usor
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monomial)) return false;
        Monomial monomial = (Monomial) o;
        return degree.equals(monomial.degree);
    }

    //ordonare in ordidne descrescatoare
    @Override
    public int compareTo(Monomial o) {
        return -this.degree.compareTo(o.getDegree());
    }


}
