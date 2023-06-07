/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;
import java.sql.SQLException;
import Model.Medicine;
import Model.PrescriptionDetails;
import Model.Result;
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class AddPrescriptionDetailsFormController {
    Medicine medicineModel = new Medicine();
    Result resultModel = new Result();
    PrescriptionDetails prescModel = new PrescriptionDetails();
    public void queryData(String sql, List<Medicine> listOfMedicine) throws SQLException {
        medicineModel.getListOfMedicine(sql, listOfMedicine);
    }
    public Result getAResult(Integer result_id) throws SQLException {
        return resultModel.getAResult(result_id);
    }
    
    public void addAPresc(PrescriptionDetails presc) throws SQLException {
        prescModel.addPresc(presc);
    }
    
}
