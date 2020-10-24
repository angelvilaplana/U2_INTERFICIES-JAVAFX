package orihuel.vilaplana.angel.controls.tableview;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController {

    @FXML
    private TableView<Product> tableView;

    @FXML
    private TableColumn<Product, String> tableName;

    @FXML
    private TableColumn<Product, String> tableHight;

    @FXML
    private TableColumn<Product, String> tableWidth;

    @FXML
    private TableColumn<Product, String> tableLong;

    @FXML
    private TableColumn<Product, String> tableWeight;

    @FXML
    private TextField txtFieldName;

    @FXML
    private TextField txtFieldHigh;

    @FXML
    private TextField txtFieldWidth;

    @FXML
    private TextField txtFieldLong;

    @FXML
    private TextField txtFieldWeight;

    /**
     * Inicialitzem el controlador
     */
    @FXML
    private void initialize() {
        // Inicialitzem el tauler
        initializeTable();

        // Fem que els TextFields del programa puguen introduir sols
        // números decimals
        txtFieldDecimals(txtFieldHigh);
        txtFieldDecimals(txtFieldWidth);
        txtFieldDecimals(txtFieldLong);
        txtFieldDecimals(txtFieldWeight);
    }

    /**
     * Guardar les dades introduides en el tauler
     */
    @FXML
    private void handleSave() {
        String name = txtFieldName.getText();
        float height = Float.parseFloat(txtFieldHigh.getText());
        float width = Float.parseFloat(txtFieldWidth.getText());
        float longer = Float.parseFloat(txtFieldLong.getText());
        float weight = Float.parseFloat(txtFieldWeight.getText());

        tableView.getItems().add(new Product(name, height , width, longer, weight));

        txtFieldName.setText("");
        txtFieldHigh.setText("");
        txtFieldWidth.setText("");
        txtFieldLong.setText("");
        txtFieldWeight.setText("");
    }

    /**
     * Métode per a inicialitzar el tauler
     * amb valors de prova
     */
    private void initializeTable() {
        tableName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableHight.setCellValueFactory(new PropertyValueFactory<>("height"));
        tableWidth.setCellValueFactory(new PropertyValueFactory<>("width"));
        tableLong.setCellValueFactory(new PropertyValueFactory<>("longer"));
        tableWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));

        tableView.getColumns().forEach(this::restrictReorderAndResizeTableColumns);

        tableView.getItems().add(new Product("Producte A", 20.22f, 22.02f, 33.222f, 333));
        tableView.getItems().add(new Product("Producte B", 11.22f, 25.04f, 44.342f, 334.22f));
        tableView.getItems().add(new Product("Producte C", 12.25f, 21.22f, 55.44f, 1.22f));
        tableView.getItems().add(new Product("Producte D", 14.55f, 22.33f, 66.22f, 333.44f));
        tableView.getItems().add(new Product("Producte E", 11.33f, 25.02f, 77.11f, 222));
        tableView.getItems().add(new Product("Producte F", 12.44f, 223.33f, 88.33f, 111));
        tableView.getItems().add(new Product("Producte G", 13.44f, 223.44f, 99.22f, 125.22f));
    }

    /**
     * Métode per a que el TextField soles puga introduir
     * números decimals
     * @param textField TextField que volem que tinga efecte
     */
    private void txtFieldDecimals(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Per si el usuari introduiex la ',' ho cambie per el '.'
            newValue = newValue.replace(',','.');
            if (!newValue.matches("\\d*(\\.\\d*)?")) {
                textField.setText(oldValue);
            } else {
                textField.setText(newValue);
            }
        });
    }

    /**
     * Métode per a que les columnes de la TableView no es puga ni escalar
     * ni que es puguen moure
     * @param productTableColumn Columna de la TableView
     */
    private void restrictReorderAndResizeTableColumns(TableColumn<Product, ?> productTableColumn) {
        productTableColumn.setResizable(false);
        productTableColumn.setReorderable(false);
        // Per si en una columna hi han més columnes repetir el procediment
        // en les altres columnes
        productTableColumn.getColumns().forEach(this::restrictReorderAndResizeTableColumns);
    }

}
