package com.digital.chat.springback.controllers;

import com.digital.chat.springback.models.documents.Mensaje;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.Date;
@Controller
public class ChatController {

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibirMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());

        if (mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setTexto("Nuevo usuario");
        }
//        mensaje.setTexto("Recibido por el broker: "+ mensaje.getTexto());
        return mensaje;
    }

}
