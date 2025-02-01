package com.Api.dto;



public class DeleteEmployeeDTO {
    private Long id;
    private String deletedBy;

    // Constructors
    public DeleteEmployeeDTO() {}

    public DeleteEmployeeDTO(Long id, String deletedBy) {
        this.id = id;
        this.deletedBy = deletedBy;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }
}
