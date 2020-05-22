package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.MesasDAO;
import sample.Modelos.PuestosDAO;
import sample.Vistas.FrmMesa;
import sample.Vistas.FrmPuesto;

import java.util.Optional;

public class ButtonCellMs extends TableCell<MesasDAO,String> {
    private Button btnCelda;
    private MesasDAO objM;

    public ButtonCellMs(int opc){
        if( opc == 1 ){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                TableView<MesasDAO> tbvTemp;
                tbvTemp = ButtonCellMs.this.getTableView();
                objM = ButtonCellMs.this.getTableView().getItems().get(ButtonCellMs.this.getIndex());
                new FrmMesa(tbvTemp,objM);

                // ButtonCell.this.getTableView().getItems().get(ButtonCell.this.getIndex());
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
                    objM = ButtonCellMs.this.getTableView().getItems().get(ButtonCellMs.this.getIndex());
                    objM.delMesa();

                    // Refrescar la tabla
                    ButtonCellMs.this.getTableView().setItems(objM.selAllMesas());
                    ButtonCellMs.this.getTableView().refresh();
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
