package com.digital.chat.springback.models.service;

import com.digital.chat.springback.models.documents.Mensaje;
import com.digital.chat.springback.models.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRepository chatDao;

    @Override
    public List<Mensaje> obtenerUltimos10Mensajes() {
        return chatDao.findFirst10ByOrderByFechaDesc();
    }

    @Override
    public Mensaje gusardar(Mensaje mensaje) {
        return chatDao.save(mensaje);
    }
}
