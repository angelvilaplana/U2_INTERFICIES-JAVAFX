package orihuel.vilaplana.angel.contenidors;

import javafx.fxml.FXML;
import orihuel.vilaplana.angel.contenidors.anchor_pane.AnchorPaneScene;
import orihuel.vilaplana.angel.contenidors.border_pane.BorderPaneScene;
import orihuel.vilaplana.angel.contenidors.flow_pane.FlowPaneScene;
import orihuel.vilaplana.angel.contenidors.grid_pane.GridPaneScene;
import orihuel.vilaplana.angel.contenidors.hbox.HBoxScene;
import orihuel.vilaplana.angel.contenidors.layout.Layout;
import orihuel.vilaplana.angel.contenidors.vbox.VBoxScene;

public class ContenidorsController {

    private ContenidorsApp contenidorsApp;

    public void setMain(ContenidorsApp contenidorsApp) {
        this.contenidorsApp = contenidorsApp;
    }

    @FXML
    void handleAnchorPane() throws Exception {
        contenidorsApp.displayApplication(new AnchorPaneScene());
    }

    @FXML
    void handleBorderPane() throws Exception {
        contenidorsApp.displayApplication(new BorderPaneScene());
    }

    @FXML
    void handleFlowPane() throws Exception {
        contenidorsApp.displayApplication(new FlowPaneScene());
    }

    @FXML
    void handleGridPane() throws Exception {
        contenidorsApp.displayApplication(new GridPaneScene());
    }

    @FXML
    void handleHBox() throws Exception {
        contenidorsApp.displayApplication(new HBoxScene());
    }

    @FXML
    void handleLayout() throws Exception {
        contenidorsApp.displayApplication(new Layout());
    }

    @FXML
    void handleVBox() throws Exception {
        contenidorsApp.displayApplication(new VBoxScene());
    }

    @FXML
    void handleReturnMenu() throws Exception {
        contenidorsApp.displayMainApp();
    }

}
