package lk.brightenacademy.customer_demo.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDateTime;
public class RoleDetailDTO extends RoleDataWithPermissionDTO{
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private AuditUserDTO createdBy;
    private AuditUserDTO updatedBy;

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
        return createdBy;
    }

    public void setCreatedBy(AuditUserDTO createdBy) {
        this.createdBy = createdBy;
    }

    public AuditUserDTO getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(AuditUserDTO updatedBy) {
        this.updatedBy = updatedBy;
    }
}
