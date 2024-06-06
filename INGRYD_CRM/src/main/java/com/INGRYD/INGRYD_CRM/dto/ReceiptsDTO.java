package com.INGRYD.INGRYD_CRM.dto;

import com.INGRYD.INGRYD_CRM.model.Sale;

public record ReceiptsDTO(Long id, String receiptDate, Double amount, Sale sales) {

}
