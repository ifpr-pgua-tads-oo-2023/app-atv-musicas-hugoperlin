package ifpr.pgua.eic.colecaomusicas.model.entities;

public class Musica {
    
    //nome da música, o artista/banda, ano de lançamento, duração e gênero

    private int id;
    private String nome;
    private int anoLancamento;
    private int duracao;
    private Artista artista;
    private Genero genero;
    
    public Musica(int id, String nome, int anoLancamento, int duracao, Artista artista, Genero genero) {
        this.id = id;
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.duracao = duracao;
        this.artista = artista;
        this.genero = genero;
    }

    public Musica(String nome, int anoLancamento, int duracao, Artista artista, Genero genero) {
        this.nome = nome;
        this.anoLancamento = anoLancamento;
        this.duracao = duracao;
        this.artista = artista;
        this.genero = genero;
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

    public int getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(int anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String toString(){
        return nome+"("+this.artista.getNome()+")";
    }

    

    

}
