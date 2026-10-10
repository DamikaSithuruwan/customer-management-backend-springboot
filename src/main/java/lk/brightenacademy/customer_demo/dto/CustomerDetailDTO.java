package lk.brightenacademy.customer_demo.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerDetailDTO extends CustomerDataDTO{
    private String gender;
    private LocalDate dob;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private  AuditUserDTO CreatedBy;
    private AuditUserDTO UpdatedBy;

    public CustomerDetailDTO(){

    }

    public CustomerDetailDTO(Integer id, String name, String mobile, String nic, String city, String gender, LocalDate dob) {
        super(id, name, mobile, nic, city);
        this.gender = gender;
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AuditUserDTO getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(AuditUserDTO createdBy) {
        CreatedBy = createdBy;
    }

    public AuditUserDTO getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(AuditUserDTO updatedBy) {
        UpdatedBy = updatedBy;
    }
}

