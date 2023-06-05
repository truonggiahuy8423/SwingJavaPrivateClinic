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
    Result resultModel = new Result();
    public Result queryData(Long result_id, String sql, List<PrescriptionDetails> listOfPres) throws SQLException {
        Result result = resultModel.getAResult(result_id);
        if (result != null ){
            // 
        }
        return result;
    }
    public void updateResult(Result updatedResult) throws SQLException {
        resultModel.updateResult(updatedResult);
    }
    
}
