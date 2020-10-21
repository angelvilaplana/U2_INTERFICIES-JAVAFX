package orihuel.vilaplana.angel.controls.list;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    @FXML
    private ListView<String> listView;

    @FXML
    private TextField txtFieldAddElement;

    @FXML
    private TextField txtFieldDeleteElement;

    @FXML
    private Label labelElementSelect;

    @FXML
    private Label labelElementSelected;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> items = FXCollections.observableArrayList(
                "Minecraft", "Counter Strike", "Fallout", "Skyrim",
                "Fifa", "Grand Theft Auto", "Hearts of Iron", "Europa Universalis"
        );
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        labelElementSelected.setText("");
        labelElementSelect.setText("");

        txtFieldDeleteElement.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtFieldDeleteElement.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        listView.getItems().addListener((ListChangeListener<String>) c -> handleSelectItems());
    }

    @FXML
    private void handleGuardarBtn() {
        String element = txtFieldAddElement.getText();
        if (!element.isEmpty()) {
            listView.getItems().add(element);
            txtFieldAddElement.setText("");
        }
    }

    public void handleGuardarTxtField(KeyEvent key) {
        String element = txtFieldAddElement.getText();
        if (key.getCode().equals(KeyCode.ENTER) && !element.isEmpty()) {
            listView.getItems().add(element);
            txtFieldAddElement.setText("");
        }
    }

    public void handleEliminarBtn() {
        String element = txtFieldDeleteElement.getText();
        if (!element.isEmpty()) {
            int index = Integer.parseInt(element) - 1;
            if (index >= 0 && (listView.getItems().size() > index)) {
                listView.getItems().remove(index);
                txtFieldDeleteElement.setText("");
            }
        }
    }

    public void handleEliminarTxtField(KeyEvent key) {
        String element = txtFieldDeleteElement.getText();
        if (key.getCode().equals(KeyCode.ENTER) && !element.isEmpty()) {
            int index = Integer.parseInt(element) - 1;
            if (index >= 0 && (listView.getItems().size() > index)) {
                listView.getItems().remove(index);
                txtFieldDeleteElement.setText("");
            }
        }
    }

    public void handleSelectItems() {
        labelElementSelect.setText("Elemento seleccionado:");
        String element = listView.getSelectionModel().getSelectedItem();
        if (element != null) {
            labelElementSelected.setText(element);
        } else {
            labelElementSelected.setText("");
            labelElementSelect.setText("");
        }
    }

}
