package orihuel.vilaplana.angel.controls.list;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Efectes incluits en el FXML:
 *
 *  - listView:
 *      - Efecte utilitzat: InnerShadow
 *      - Motiu: Per a veure la vora de la llista
 *
 */
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

    /**
     * Métode per a inicializar el nostre funcionament de
     * l'aplicació
     */
    @FXML
    private void initialize() {
        // Variable per a indicar que es veuen tots els elements
        // de la llista
        isListViewAll = true;

        // Elements que es veuran a la llista al iniciar
        // l'aplicació
        items = FXCollections.observableArrayList(
                "Minecraft", "Counter Strike", "Fallout", "Skyrim",
                "Fifa", "Grand Theft Auto", "Hearts of Iron", "Europa Universalis"
        );
        listView.setItems(items);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        // Actualizem els elements del "choiceIndexList" per a poder
        // seleccionar els elements de la llista
        updateChoiceIndexList();

        // No mostrem els elements de "selecció"
        // Sols els motrare depenent de la selecció
        btnSeleccionados.setVisible(false);
        btnSelePantalla.setVisible(false);
        labelElementSelected.setText("");
        labelElementSelect.setText("");

        // Per a que sols es puga introduir números en el
        // TextField d'eliminar elements per el index
        txtFieldDeleteElement.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                txtFieldDeleteElement.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Per a mostrar el botó de elegir sols seleccionats si hi ha més de un item
        // seleccionat
        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int selectedItems = listView.getSelectionModel().getSelectedItems().size();
            btnSeleccionados.setVisible(selectedItems > 1 || !isListViewAll);
        });

        // Listener per al listView per si cambia seleccionar el nou element
        // seleccionat
        listView.getItems().addListener((ListChangeListener<String>) c -> handleSelectItems());

        // Per a seleccionar el nou index de la llista si es major a 0
        // Ja que el element 0 es un text per a indicar al usuari
        choiceIndexList.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            int index = (int) newValue - 1;
            if (index >= 0) {
                listView.getSelectionModel().clearAndSelect(index);
                handleSelectItems();
            }
        });
    }

    /**
     * Botó per a guardar el element en la llista
     */
    @FXML
    private void handleGuardarBtn() {
        String element = txtFieldAddElement.getText();
        if (!element.isEmpty()) {
            listView.getItems().add(element);
            updateChoiceIndexList();
            txtFieldAddElement.setText("");
        }
    }

    /**
     * Comprobar si el usuari a introduit "ENTER"
     * per a guardar el resultat en la llista
     *
     * @param key La tecla que ha introduit l'usuari
     */
    @FXML
    private void handleGuardarTxtField(KeyEvent key) {
        String element = txtFieldAddElement.getText();
        if (key.getCode().equals(KeyCode.ENTER) && !element.isEmpty()) {
            listView.getItems().add(element);
            updateChoiceIndexList();
            txtFieldAddElement.setText("");
        }
    }

    /**
     * Botó per a borrar el element en la llista
     */
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

    /**
     * Comprobar si el usuari a introduit "ENTER"
     * per a borrar el resultat en la llista
     *
     * @param key La tecla que ha introduit l'usuari
     */
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

    /**
     * Mostrar el element seleccionat
     */
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

    /**
     * Mostrar tots els elements seleccionats
     */
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

    /**
     * Mostrar tots els elements seleccionats
     * en una altra finestra
     */
    @FXML
    private void handleSeleVentana() {
        listApp.showListSelectElements(listView.getItems());
    }

    /**
     * Actualizar el "choiceIndexList"
     */
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
