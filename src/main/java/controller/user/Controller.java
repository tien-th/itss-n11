package controller.user;

import repository.user.RegisterServiceSaver;

import java.sql.SQLException;

public class Controller {


    public static String dangKyKham(int id, String ngayKham, int startHour) throws SQLException, ClassNotFoundException {
        RegisterServiceSaver dkyKham = new RegisterServiceSaver();
        if (dkyKham.dangKyKham(id, ngayKham, startHour)) {
            return "Đăng ký khám thành công";
        }
        else {
            return "Đăng ký khám thất bại - Khoảng thời gian đã hết chỗ";
        }
    }

    public static String dangKyTrongGiu(int id, String ngayKham, int startHour) throws SQLException, ClassNotFoundException {
        RegisterServiceSaver dkyDV = new RegisterServiceSaver();
        int lod_id = dkyDV.dangKyTrongGiu(id, ngayKham, startHour);
        if ( lod_id != 0) {
            return "Đăng ký dịch vụ chuồng " +  lod_id + " thành công";
        }
        else {
            return "Đăng ký dịch vụ thất bại - Khoảng thời gian này đã hết chỗ";
        }
    }
}
