package one.digitalinovation.laboojava.entidade;

import one.digitalinovation.laboojava.entidade.constantes.Materias;

public class Caderno extends Produto {

    private Materias materias;

    public Caderno() {
    }

    public Caderno(Materias materias) {
        this.materias = materias;
    }


    public Materias getMaterias() {
        return materias;
    }

    public void setMaterias(Materias materias) {
        this.materias = materias;
    }

    @Override
    public String toString() {
        return "Caderno{" +
                "materias=" + materias +
                ", codigo='" + getCodigo() + '\'' +
                ", preço='" + getPreco() + '\'' +
                '}';
    }
}
