package com.digital.chat.springback.models.service;

import com.digital.chat.springback.models.documents.Mensaje;

import java.util.List;

public interface ChatService {

    public List<Mensaje> obtenerUltimos10Mensajes();
    public Mensaje gusardar(Mensaje mensaje);
}
