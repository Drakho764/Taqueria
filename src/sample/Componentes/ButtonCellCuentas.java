package sample.Componentes;

import javafx.scene.control.*;
import sample.Modelos.CuentasDAO;
import sample.Modelos.EmpleadosDAO;
import sample.Vistas.CRUDPago;
import sample.Vistas.FrmCuenta;
import sample.Vistas.FrmEmpleado;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class ButtonCellCuentas extends TableCell<CuentasDAO,String> {
    private Button btnCelda;
    private CuentasDAO objC;
    int idEmpleado;
    String tipoEm;
    public ButtonCellCuentas(int opc,int idEmpleado2,String tipoEm){
        this.tipoEm=tipoEm;
        this.idEmpleado=idEmpleado2;
        switch(opc){
            case 1:
                btnCelda = new Button("Pagar");
                btnCelda.setOnAction(event -> {
                    TableView<CuentasDAO> tbvTemp;
                    tbvTemp = ButtonCellCuentas.this.getTableView();
                    objC = ButtonCellCuentas.this.getTableView().getItems().get(ButtonCellCuentas.this.getIndex());
                    new CRUDPago(objC);
                });
                break;
            case 2:
                btnCelda = new Button("Editar");
                btnCelda.setOnAction(event -> {
                        TableView<CuentasDAO> tbvTemp;
                        tbvTemp = ButtonCellCuentas.this.getTableView();
                        objC = ButtonCellCuentas.this.getTableView().getItems().get(ButtonCellCuentas.this.getIndex());
                        new FrmCuenta(tbvTemp,objC,1,idEmpleado,tipoEm);
                });
                break;
            case 3:
                btnCelda = new Button("Borrar");
                btnCelda.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Mensaje del Sistema :)");
                    alert.setHeaderText("Confirmando Acción");
                    alert.setContentText("¿Deseas eliminar la cuenta?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        objC = ButtonCellCuentas.this.getTableView().getItems().get(ButtonCellCuentas.this.getIndex());
                        objC.delCuenta();

                        //Refrescar la tabla
                        ButtonCellCuentas.this.getTableView().setItems(objC.obtenerCuentasActivas());
                        ButtonCellCuentas.this.getTableView().refresh();
                    }
                });
                break;
            case 4:
                btnCelda = new Button("Pagada");
                btnCelda.setOnAction(event -> {
                    objC = ButtonCellCuentas.this.getTableView().getItems().get(ButtonCellCuentas.this.getIndex());

                    Date fech=new Date();
                    SimpleDateFormat objDate=new SimpleDateFormat("yyyy-MM-dd");
                    String fecha=objDate.format(fech);
                    float totalCajaAnt=objC.getTotalCaja(fecha);
                    System.out.println("$"+totalCajaAnt);
                    float totalT=objC.getTotalCuenta();
                    float totalCajaNuevo=totalCajaAnt+totalT;
                    System.out.println("$"+totalCajaNuevo);
                    objC.ActualizarCaja(totalCajaNuevo,fecha,objC.getIdMesa());

                    objC.ActualizarCuentaAPagada(objC.getIdCuenta());
                    int hor,min,seg;
                    Calendar calendar = Calendar.getInstance();
                    hor =calendar.get(Calendar.HOUR_OF_DAY);
                    min = calendar.get(Calendar.MINUTE);
                    seg = calendar.get(Calendar.SECOND);
                    String horasal= hor+":"+min+":"+seg;

                    objC.ActualizarTotalCuenta(objC.getIdCuenta(),totalT,horasal);
                });
                break;
            case 6:
                btnCelda = new Button("Borrar");
                btnCelda.setOnAction(event -> {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Mensaje del Sistema :)");
                    alert.setHeaderText("Confirmando Acción");
                    alert.setContentText("¿Deseas eliminar la cuenta?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        objC = ButtonCellCuentas.this.getTableView().getItems().get(ButtonCellCuentas.this.getIndex());
                        objC.delCuenta();

                        //Refrescar la tabla
                        ButtonCellCuentas.this.getTableView().setItems(objC.selAllCuentas());
                        ButtonCellCuentas.this.getTableView().refresh();
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
