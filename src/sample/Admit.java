package sample;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="admission")
public class Admit {
	
	
	
	

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name="admission_id") private int id;
	@Column(name="patient_id")private int PatientId;
	@Column(name="ward_id")private int WardId;
	@Column(name="room_no")private int roomNo;
	@Column(name="reg_date")private String regDate;
	@Column(name="end_date")private String endDate;
	
	
	
	@Transient
	private String PatientName;
	@Transient
	private int PatientAge;
	@Transient
	private Patient p;
	@Transient
	private Wards w;
	@Transient
	private String WardName;
	
	public Admit(int id, int pid , int wardId, int roomNo ,String regDate, String endDate) {
		super();
		this.id = id;
		this.PatientId=pid;
		this.WardId = wardId;
		this.roomNo = roomNo;
		this.regDate = regDate;
		this.endDate = endDate;
		
	
	}
	
	
	public String getWardName() {
		return WardName;
	}
	public void setWardName(String wardName) {
		WardName = wardName;
	}
	public int getPatientId() {
		return PatientId;
	}
	public void setPatientId(int patientId) {
		PatientId = patientId;
	}
	public int getPatientAge() {
		return PatientAge;
	}
	public void setPatientAge(int patientAge) {
		PatientAge = patientAge;
	}
	public int getWardId() {
		return WardId;
	}
	public void setWardId(int wardId) {
		WardId = wardId;
	}
	public Admit(int id, int roomNo, String regDate, String endDate, String patientName, int patientId, int patientAge,
			int wardId,String wardName) {
		super();
		this.id = id;
		this.roomNo = roomNo;
		this.regDate = regDate;
		this.endDate = endDate;
		PatientName = patientName;
		PatientId = patientId;
		PatientAge = patientAge;
		WardId = wardId;
		this.WardName=wardName;
	}
	public String getPatientName() {
		return PatientName;
	}
	public void setPatientName(String patientName) {
		PatientName = patientName;
	}
	public int getRoomNo() {
		return roomNo;
	}

	public void setRoomNo(int roomNo) {
		this.roomNo = roomNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setPatient(Patient pt) {
		this.p=pt;
	}
	public void setWard(Wards wd) {
		this.w=wd;
	}
	public Patient getPatient() {
		return p;
	}
	public Wards getWard() {
		return w;
	}

}
