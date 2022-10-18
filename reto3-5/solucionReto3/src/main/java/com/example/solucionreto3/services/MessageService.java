package com.example.solucionreto3.services;

import com.example.solucionreto3.entities.Client;
import com.example.solucionreto3.entities.Message;
import com.example.solucionreto3.repository.ClientRepository;
import com.example.solucionreto3.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll(){
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage (int id){
        return messageRepository.getMessage(id);
    }

    public Message save(Message p){
        if(p.getIdMessage()==null){
            return  messageRepository.save(p);
        }else{
            Optional<Message> e = messageRepository.getMessage(p.getIdMessage());
            if(e.isPresent()){
                return p;
            }else{
                return  messageRepository.save(p);
            }
        }
    }

    public Message update (Message p) {
        if (p.getIdMessage() != null) {
            Optional<Message> q = messageRepository.getMessage(p.getIdMessage());
            if (!q.isEmpty()) {
                if (p.getMessageText() != null) {
                    q.get().setMessageText(p.getMessageText());
                }
                messageRepository.save(q.get());
                return q.get();
            }else{
                return p;
            }
        }else{
            return p;
        }
    }

    public boolean delete(int id){
            boolean flag = false;
            Optional<Message> p = messageRepository.getMessage(id);
            if (p.isPresent()) {
                messageRepository.delete(p.get());
                flag = true;
            }
            return flag;
        }

}
