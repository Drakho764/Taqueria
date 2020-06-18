package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.BebidasDAO;
import sample.Modelos.MesasDAO;
import sample.Vistas.FrmBebida;
import sample.Vistas.FrmMesa;

import java.util.Optional;

public class ButtonCellBeb extends TableCell<BebidasDAO,String> {
    private Button btnCelda;
    private BebidasDAO objM;

    public ButtonCellBeb(int opc){
        if( opc == 1 ){
            btnCelda = new Button("Editar");
            btnCelda.setOnAction(event -> {
                TableView<BebidasDAO> tbvTemp;
                tbvTemp = ButtonCellBeb.this.getTableView();
                objM = ButtonCellBeb.this.getTableView().getItems().get(ButtonCellBeb.this.getIndex());
                new FrmBebida(tbvTemp,objM);
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
                    objM = ButtonCellBeb.this.getTableView().getItems().get(ButtonCellBeb.this.getIndex());
                    objM.delBebida();

                    // Refrescar la tabla
                    ButtonCellBeb.this.getTableView().setItems(objM.selAllBebidas());
                    ButtonCellBeb.this.getTableView().refresh();
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
