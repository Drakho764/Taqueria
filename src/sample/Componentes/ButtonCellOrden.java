package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.CuentasDAO;
import sample.Modelos.OrdenDAO;
import sample.Vistas.FrmCuenta;

import java.util.Optional;

public class ButtonCellOrden extends TableCell<OrdenDAO,String> {
    private Button btnCelda;
    private OrdenDAO objC;
    public ButtonCellOrden(int opc){
        switch(opc){
            case 1:
                btnCelda = new Button("Editar");
                btnCelda.setOnAction(event -> {
                        TableView<OrdenDAO> tbvTemp;
                        tbvTemp = ButtonCellOrden.this.getTableView();
                        objC = ButtonCellOrden.this.getTableView().getItems().get(ButtonCellOrden.this.getIndex());
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
                        objC = ButtonCellOrden.this.getTableView().getItems().get(ButtonCellOrden.this.getIndex());
                        //objC.delOrden();

                        //Refrescar la tabla
                        ButtonCellOrden.this.getTableView().setItems(objC.selAllOrdenes());
                        ButtonCellOrden.this.getTableView().refresh();
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
