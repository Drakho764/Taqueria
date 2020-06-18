package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.CajaDAO;
import sample.Modelos.OrdenDAO;

import java.util.Optional;

public class ButtonCellCaja extends TableCell<CajaDAO,String> {
    private Button btnCelda;
    private CajaDAO objC;
    public ButtonCellCaja(int opc){
        switch(opc){
            case 1:
                btnCelda = new Button("Editar");
                btnCelda.setOnAction(event -> {
                        TableView<CajaDAO> tbvTemp;
                        tbvTemp = ButtonCellCaja.this.getTableView();
                        objC = ButtonCellCaja.this.getTableView().getItems().get(ButtonCellCaja.this.getIndex());
                        //new FrmCuenta(tbvTemp,objC,1,idEmpleado);
                });
                break;
            case 2:
                btnCelda = new Button("Borrar");
                btnCelda.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Mensaje del Sistema :)");
                    alert.setHeaderText("Confirmando Acción");
                    alert.setContentText("¿Deseas eliminar la cuenta?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        objC = ButtonCellCaja.this.getTableView().getItems().get(ButtonCellCaja.this.getIndex());
                        //objC.delOrden();

                        //Refrescar la tabla
                        ButtonCellCaja.this.getTableView().setItems(objC.selAllCajas());
                        ButtonCellCaja.this.getTableView().refresh();
                    }
                });
                break;
        }
    }
    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        if( !empty )
            setGraphic(btnCelda);
    }
}
