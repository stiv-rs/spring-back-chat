package com.digital.chat.springback.controllers;

import com.digital.chat.springback.models.documents.Mensaje;
import com.digital.chat.springback.models.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private SimpMessagingTemplate webSocket;

    private String[] colores = {"red","green","blue","magenta","purple","orange"};
    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibirMensaje(Mensaje mensaje){
        mensaje.setFecha(new Date().getTime());
        if (mensaje.getTipo().equals("NUEVO_USUARIO")){
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("Nuevo usuario");
        }else {
            chatService.gusardar(mensaje);
        }
        return mensaje;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String avisarEscritura(String usrname){
        return usrname.concat(" Esta escribiendo...");
    }

    @MessageMapping("/historial")
    public void obtenerHistorial(String clienteId){
        webSocket.convertAndSend("/chat/historial/" + clienteId ,chatService.obtenerUltimos10Mensajes());
    }
}
