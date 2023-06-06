/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;
import Model.PrescriptionDetails;
import Model.Result;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author GIAHUY
 */
public class ResultTabController {
    PrescriptionDetails prescModel = new PrescriptionDetails();
    Result resultModel = new Result();
    public Result queryData(Integer result_id, String sql, List<PrescriptionDetails> listOfPres) throws SQLException {
        Result result = resultModel.getAResult(result_id);
        if (result != null ){
            prescModel.getListOfPresc(sql, listOfPres);
        }
        return result;
    }
    public void updateResult(Result updatedResult) throws SQLException {
        resultModel.updateResult(updatedResult);
    }
    public void deletePresc(Integer result_id, Integer medicine_id) throws SQLException {
        prescModel.deleltePresc(result_id, medicine_id);
    }
}
