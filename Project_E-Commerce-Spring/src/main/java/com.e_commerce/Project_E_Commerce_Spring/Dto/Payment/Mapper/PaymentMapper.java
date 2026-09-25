package com.e_commerce.Project_E_Commerce_Spring.Dto.Payment.Mapper;


import com.e_commerce.Project_E_Commerce_Spring.Dto.Payment.PaymentRequestDto;
import com.e_commerce.Project_E_Commerce_Spring.Model.product_module.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

    @Mapping(source = "paymentType",target = "paymentType")
    PaymentRequestDto toRequestDto(Payment payment);

    @Mapping(source = "paymentType",target = "paymentType")
    Payment toEntity(PaymentRequestDto paymentRequestDto);
}
