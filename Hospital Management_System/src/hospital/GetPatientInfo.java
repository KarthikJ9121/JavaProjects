package hospital;

import java.sql.ResultSet;

public class GetPatientInfo {
	private int id;
    private String name;
    private String gender;
    private String age;
    private String phoneNo;
    private String address;
    private String disease;
    private String doctorName;
    private String dateOfAppointment;
    private String status;
    private String dateOfReport;
    private String reportInfo;

    
    // Getters and setters for each field
    public int getPatientId()
    {
    	return id;
    }    
    public void setPatientId(int id)
    {
    	this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDateOfAppointment() {
        return dateOfAppointment;
    }

    public void setDateOfAppointment(String dateOfAppointment) {
        this.dateOfAppointment = dateOfAppointment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDateOfReport() {
        return dateOfReport;
    }

    public void setDateOfReport(String dateOfReport) {
        this.dateOfReport = dateOfReport;
    }

    public String getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(String reportInfo) {
        this.reportInfo = reportInfo;
    }

    public static GetPatientInfo getPatientDataFromDB(String patientName) {
        GetPatientInfo patient = new GetPatientInfo();

        try {
            DbConn conn = new DbConn();
            String query = "SELECT * FROM patients WHERE patient_name = '" + patientName + "';";
            ResultSet rs = conn.stmt.executeQuery(query);
            if (rs.next()) {
            	patient.setPatientId(rs.getInt("patient_id"));
                patient.setName(rs.getString("patient_name"));
                patient.setGender(rs.getString("gender"));
                patient.setAge(rs.getString("age"));
                patient.setPhoneNo(rs.getString("phone_no"));
                patient.setAddress(rs.getString("address"));
                patient.setDisease(rs.getString("disease"));
                patient.setDoctorName(rs.getString("doctor_name"));
                patient.setDateOfAppointment(rs.getString("date_of_appointment"));
                patient.setStatus(rs.getString("status"));
                patient.setDateOfReport(rs.getString("date_of_report"));
                patient.setReportInfo(rs.getString("report_info"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return patient;
    }
}
