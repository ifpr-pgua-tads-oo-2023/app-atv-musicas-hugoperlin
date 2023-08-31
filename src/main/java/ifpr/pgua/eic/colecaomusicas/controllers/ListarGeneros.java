package ifpr.pgua.eic.colecaomusicas.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.github.hugoperlin.results.Resultado;

import ifpr.pgua.eic.colecaomusicas.App;
import ifpr.pgua.eic.colecaomusicas.model.entities.Genero;
import ifpr.pgua.eic.colecaomusicas.model.repositories.RepositorioGeneros;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Alert.AlertType;

public class ListarGeneros implements Initializable{

    @FXML
    private ListView<Genero> lstGeneros;

    private RepositorioGeneros repositorio;

    public ListarGeneros(RepositorioGeneros repositorio){
        this.repositorio = repositorio;
    }

    @FXML
    void voltar(ActionEvent event) {
        App.popScreen();
    }

    @FXML
    void mostrarSelecionados(){
        List<Genero> selecionados = lstGeneros
                                      .getSelectionModel()
                                      .getSelectedItems();
        
        String str = "";

        for(Genero genero:selecionados){
            str += genero.getNome()+";";
        }

        Alert alert = new Alert(AlertType.INFORMATION, str);
        alert.showAndWait();
    
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        lstGeneros.getItems().clear();
        
        lstGeneros.getSelectionModel()
              .setSelectionMode(SelectionMode.MULTIPLE);
        
        
        Resultado r = repositorio.listarGeneros();

        if(r.foiSucesso()){
            List<Genero> lista = (List)r.comoSucesso().getObj();
            lstGeneros.getItems().addAll(lista);
        }else{
            Alert alert = new Alert(AlertType.ERROR, r.getMsg());
            alert.showAndWait();
        }
    
    }

}
