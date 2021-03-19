package model;

//clasa abstracta ce descrie modelul matematic de monom
public abstract class Monomial implements Comparable<Monomial>{

    //pentru a putea fi accesat de subclase
    protected Integer degree;

    //constructorul va fi apelat din  constructorii claselor descendente cu super(degree)
    public Monomial(Integer degree){
        this.degree=degree;
    }

    //metode abstracte ce definesc operatiile pe monoame
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

    //am pus testul de egalitate doar pe grad; simplifică adaugarea de monoame in polinom
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Monomial)) return false;
        Monomial monomial = (Monomial) o;
        return degree.equals(monomial.degree);
    }

    //pentru ordonare in ordine descrescatoare a gradelor;
    @Override
    public int compareTo(Monomial o) {
        return -this.degree.compareTo(o.getDegree());
    }


}
