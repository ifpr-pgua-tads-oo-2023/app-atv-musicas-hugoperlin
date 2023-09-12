package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioMusicas;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class ListarMusicas implements Initializable {

    @FXML
    private TableView<Musica> tbMusica;

    @FXML
    private TableColumn<Musica, String> tbcAnoLancamento;

    @FXML
    private TableColumn<Musica, String> tbcArtista;

    @FXML
    private TableColumn<Musica, String> tbcDuracao;

    @FXML
    private TableColumn<Musica, String> tbcGenero;

    @FXML
    private TableColumn<Musica, String> tbcId;

    @FXML
    private TableColumn<Musica, String> tbcNome;


    private RepositorioMusicas repositorio;

    
    public ListarMusicas(RepositorioMusicas repositorio) {
        this.repositorio = repositorio;
    }



    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }



    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        //configurar a renderização das colunas
        tbcId.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getId()+""));
        tbcNome.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getNome()));
        tbcDuracao.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getDuracao()+""));
        tbcAnoLancamento.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getAnoLancamento()+""));
        tbcArtista.setCellValueFactory(celula->new SimpleStringProperty(celula.getValue().getArtista().getNome()));
        tbcGenero.setCellValueFactory(celula -> new SimpleStringProperty(celula.getValue().getGenero().getNome()));

        Resultado rs = repositorio.listar();

        if(rs.foiErro()){
            Alert alert = new Alert(AlertType.ERROR,rs.getMsg());
            alert.showAndWait();
            return;
        }

        List<Musica> lista = (List)rs.comoSucesso().getObj();
        
        tbMusica.getItems().addAll(lista);
    
    }

}
