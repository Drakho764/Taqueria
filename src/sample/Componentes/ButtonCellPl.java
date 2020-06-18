package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.BebidasDAO;
import sample.Modelos.PlatillosDAO;
import sample.Vistas.FrmBebida;
import sample.Vistas.FrmPlatillo;

import java.util.Optional;

public class ButtonCellPl extends TableCell<PlatillosDAO,String> {
    private Button btnCelda;
    private PlatillosDAO objM;

    public ButtonCellPl(int opc){
        if( opc == 1 ){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                TableView<PlatillosDAO> tbvTemp;
                tbvTemp = ButtonCellPl.this.getTableView();
                objM = ButtonCellPl.this.getTableView().getItems().get(ButtonCellPl.this.getIndex());
                new FrmPlatillo(tbvTemp,objM);
            });
        }
        else{
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Confirmando Acción");
                alert.setContentText("¿Deseas eliminar Mesa?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    objM = ButtonCellPl.this.getTableView().getItems().get(ButtonCellPl.this.getIndex());
                    objM.delPlatillo();

                    // Refrescar la tabla
                    ButtonCellPl.this.getTableView().setItems(objM.selAllPlatillos());
                    ButtonCellPl.this.getTableView().refresh();
                }
            });
        }
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if( !empty )
            setGraphic(btnCelda);
    }

}
