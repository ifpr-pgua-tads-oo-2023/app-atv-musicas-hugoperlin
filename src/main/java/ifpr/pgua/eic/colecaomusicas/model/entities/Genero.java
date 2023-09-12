package ifpr.pgua.eic.colecaomusicas.model.entities;

public class Genero {
    //atributos
    private int id;
    private String nome;
    
    //método construtor para quando puxar
    //do banco de dados
    public Genero(int id, String nome){
        this.id = id;
        this.nome = nome;
    }

    //método construtor para quando for
    //cadastrar um novo gênero
    public Genero(String nome){
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString(){
        return nome+" ("+id+")";
    }

}
