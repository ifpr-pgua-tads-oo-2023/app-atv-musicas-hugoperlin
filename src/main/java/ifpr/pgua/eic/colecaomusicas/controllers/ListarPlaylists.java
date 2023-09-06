package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.model.entities.Musica;
import ifpr.pgua.eic.colecaomusicas.model.entities.Playlist;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioMusicas;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioPlaylists;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class ListarPlaylists implements Initializable {

    @FXML
    private ListView<Musica> lstMusicas;

    @FXML
    private ListView<Playlist> lstPlaylists;


    private RepositorioPlaylists repositorio;

    
    public ListarPlaylists(RepositorioPlaylists repositorio) {
        this.repositorio = repositorio;
    }



    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    private void mostrarMusicas(){
        Playlist selecionada = lstPlaylists.getSelectionModel().getSelectedItem();

        if(selecionada != null){
            Resultado res = repositorio.carregarMusicas(selecionada);
            if(res.foiSucesso()){
                lstMusicas.getItems().clear();
                lstMusicas.getItems().addAll(selecionada.getMusicas());
            }
        }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Resultado rs = repositorio.listar(false);

        if(rs.foiErro()){
            Alert alert = new Alert(AlertType.ERROR,rs.getMsg());
            alert.showAndWait();
            return;
        }

        List<Playlist> lista = (List)rs.comoSucesso().getObj();
        
        lstPlaylists.getItems().addAll(lista);

    }

}
