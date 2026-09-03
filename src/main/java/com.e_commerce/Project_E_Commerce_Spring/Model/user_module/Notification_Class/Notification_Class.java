package com.e_commerce.Project_E_Commerce_Spring.Model.user_module.Notification_Class;

public enum Notification_Class {
    INFO,      // Avisos gerais, promoções
    SUCCESS,   // Pagamento aprovado, pedido entregue
    WARNING,   // Boleto prestes a vencer, alteração cadastral
    ERROR      // Falha no pagamento, erro na entrega
}

