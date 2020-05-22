package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.EmpleadosDAO;
import sample.Vistas.FrmEmpleado;

import java.util.Optional;

public class ButtonCell extends TableCell<EmpleadosDAO,String> {
    private Button btnCelda;
    private EmpleadosDAO objE;

    public ButtonCell(int opc){
        if( opc == 1 ){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                TableView<EmpleadosDAO> tbvTemp;
                tbvTemp = ButtonCell.this.getTableView();
                objE = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                new FrmEmpleado(tbvTemp,objE);

                // ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
            });
        }
        else{
            btnCelda = new Button("Borrar");
            btnCelda.setOnAction(event -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Mensaje del Sistema :)");
                alert.setHeaderText("Confirmando Acción");
                alert.setContentText("¿Deseas eliminarlo al Empleado?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    objE = ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
                    objE.delEmpleado();

                    // Refrescar la tabla
                    ButtonCell.this.getTableView().setItems(objE.selAllEmpleados());
                    ButtonCell.this.getTableView().refresh();
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
