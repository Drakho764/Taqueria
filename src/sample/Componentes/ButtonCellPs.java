package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.EmpleadosDAO;
import sample.Modelos.PuestosDAO;
import sample.Vistas.FrmEmpleado;
import sample.Vistas.FrmPuesto;

import java.util.Optional;

public class ButtonCellPs extends TableCell<PuestosDAO,String> {
    private Button btnCelda;
    private PuestosDAO objP;

    public ButtonCellPs(int opc){
        if( opc == 1 ){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                TableView<PuestosDAO> tbvTemp;
                tbvTemp = ButtonCellPs.this.getTableView();
                objP = ButtonCellPs.this.getTableView().getItems().get(ButtonCellPs.this.getIndex());
                new FrmPuesto(tbvTemp,objP);

                // ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
            });
        }
        else{
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Confirmando Acción");
                alert.setContentText("¿Deseas eliminarlo el Puesto?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    objP = ButtonCellPs.this.getTableView().getItems().get(ButtonCellPs.this.getIndex());
                    objP.delPuesto();

                    // Refrescar la tabla
                    ButtonCellPs.this.getTableView().setItems(objP.selAllPuestos());
                    ButtonCellPs.this.getTableView().refresh();
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
