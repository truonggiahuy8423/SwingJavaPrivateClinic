/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package adminRole.controller;

import Model.Medicine;
import adminRole.view.MedicinePage;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MedicinePageController {
    private MedicinePage view;
    private Medicine medicineModel = new Medicine();

    public MedicinePageController(MedicinePage view) {
         this.view = view;
    }
    
    public void queryData(String sql, List<Medicine> listOfMedicine){
        medicineModel.getListOfMedicine(sql, listOfMedicine);
    }
    
    public void addData(Medicine addMedicine, Integer unitID){
        medicineModel.addMedicine(addMedicine, unitID);
    }
        
    public void deleteData(String medicineID){
        medicineModel.deleteMedicine(medicineID);
    }
    
    public void updateData(Medicine updateMedicine, Medicine currentMedicine, Integer unitID){
        medicineModel.updateMedicine(updateMedicine, currentMedicine, unitID);
    }
    
    public void searchData(Medicine searchMedicine, List<Medicine> listSearchMedicine){
        medicineModel.searchMedicine(searchMedicine, listSearchMedicine);
    }
}
