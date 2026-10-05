package com.example.erp.dto;

import com.example.erp.entity.PurchaseOrderStatus;
import jakarta.validation.constraints.NotNull;

public class PurchaseOrderStatusUpdateDTO {

    @NotNull(message = "Status is required")
    private PurchaseOrderStatus status;

    public PurchaseOrderStatusUpdateDTO() {}

    public PurchaseOrderStatusUpdateDTO(PurchaseOrderStatus status) {
        this.status = status;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }
}