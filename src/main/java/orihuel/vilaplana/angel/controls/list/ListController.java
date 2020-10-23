package orihuel.vilaplana.angel.controls.list;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ListController {

    @FXML
    private ListView<String> listView;

    @FXML
    private VBox boxAddDel;

    @FXML
    private TextField txtFieldAddElement;

    @FXML
    private TextField txtFieldDeleteElement;

    @FXML
    private ChoiceBox<String> choiceIndexList;

    @FXML
    private Button btnSeleccionados;

    @FXML
    private Button btnSelePantalla;

    @FXML
    private Label labelElementSelect;

    @FXML
    private Label labelElementSelected;

    private ListApp listApp;

    private boolean isListViewAll;

    private ObservableList<String> items;

    public void setMain(ListApp listApp) {
        this.listApp = listApp;
    }

    @FXML
    private void initialize() {
        isListViewAll = true;

        items = FXCollections.observableArrayList(
                "Minecraft", "Counter Strike", "Fallout", "Skyrim",
                "Fifa", "Grand Theft Auto", "Hearts of Iron", "Europa Universalis"
        );
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        updateChoiceIndexList();

        btnSeleccionados.setVisible(false);
        btnSelePantalla.setVisible(false);
        labelElementSelected.setText("");
        labelElementSelect.setText("");

        txtFieldDeleteElement.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtFieldDeleteElement.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int selectedItems = listView.getSelectionModel().getSelectedItems().size();
            btnSeleccionados.setVisible(selectedItems > 1 || !isListViewAll);
        });
        listView.getItems().addListener((ListChangeListener<String>) c -> handleSelectItems());

        choiceIndexList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int index = (int) newValue - 1;
            if (index >= 0) {
                listView.getSelectionModel().clearAndSelect(index);
                handleSelectItems();
            }
        });
    }

    @FXML
    private void handleGuardarBtn() {
        String element = txtFieldAddElement.getText();
        if (!element.isEmpty()) {
            listView.getItems().add(element);
            updateChoiceIndexList();
            txtFieldAddElement.setText("");
        }
    }

    @FXML
    private void handleGuardarTxtField(KeyEvent key) {
        String element = txtFieldAddElement.getText();
        if (key.getCode().equals(KeyCode.ENTER) && !element.isEmpty()) {
            listView.getItems().add(element);
            updateChoiceIndexList();
            txtFieldAddElement.setText("");
        }
    }

    @FXML
    private void handleEliminarBtn() {
        String element = txtFieldDeleteElement.getText();
        if (!element.isEmpty()) {
            int index = Integer.parseInt(element) - 1;
            if (index >= 0 && (listView.getItems().size() > index)) {
                listView.getItems().remove(index);
                updateChoiceIndexList();
                txtFieldDeleteElement.setText("");
            }
        }
    }

    @FXML
    private void handleEliminarTxtField(KeyEvent key) {
        String element = txtFieldDeleteElement.getText();
        if (key.getCode().equals(KeyCode.ENTER) && !element.isEmpty()) {
            int index = Integer.parseInt(element) - 1;
            if (index >= 0 && (listView.getItems().size() > index)) {
                listView.getItems().remove(index);
                updateChoiceIndexList();
                txtFieldDeleteElement.setText("");
            }
        }
    }

    @FXML
    private void handleSelectItems() {
        labelElementSelect.setText("Elemento seleccionado:");
        String element = listView.getSelectionModel().getSelectedItem();
        if (element != null) {
            labelElementSelected.setText(element);
        } else {
            labelElementSelected.setText("");
            labelElementSelect.setText("");
        }
    }

    @FXML
    private void handleSeleccionados() {
        if (isListViewAll) {
            ObservableList<String> selected = FXCollections.observableArrayList(listView.getSelectionModel().getSelectedItems());
            listView.setItems(selected);
            updateChoiceIndexList();
            boxAddDel.setDisable(true);
            btnSeleccionados.setVisible(true);
            btnSelePantalla.setVisible(true);
            btnSeleccionados.setText("Mostrar todos");
            isListViewAll = false;
        } else {
            listView.setItems(items);
            updateChoiceIndexList();
            boxAddDel.setDisable(false);
            btnSeleccionados.setVisible(false);
            btnSelePantalla.setVisible(false);
            btnSeleccionados.setText("Mostrar seleccionados");
            isListViewAll = true;
        }
    }

    @FXML
    private void handleSeleVentana() {
        listApp.showListSelectElements(listView.getItems());
    }

    private void updateChoiceIndexList() {
        ArrayList<String> listIndex = new ArrayList<>();
        listIndex.add("Seleccionar índice");
        for (int i = 1; i <= listView.getItems().size(); i++) {
            listIndex.add(String.valueOf(i));
        }
        ObservableList<String> index = FXCollections.observableArrayList(listIndex);
        choiceIndexList.setItems(index);
        choiceIndexList.getSelectionModel().selectFirst();
    }

}
