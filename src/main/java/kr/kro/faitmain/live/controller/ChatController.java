package kr.kro.faitmain.live.controller;

import kr.kro.faitmain.live.domain.ChatRoom;
import kr.kro.faitmain.live.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/")
    public String rooms(Model model){
        model.addAttribute("rooms",chatRoomRepository.findAllRoom());
        return "rooms";
    }

    @GetMapping("/rooms/{roomId}")
    public String room(@PathVariable String roomId, Model model){
        ChatRoom room = chatRoomRepository.findRoomById(roomId);
        model.addAttribute("room",room);
        return "room";
    }

    @GetMapping("/new")
    public String make(Model model){
        ChatRoomForm form = new ChatRoomForm();
        model.addAttribute("form",form);
        return "newRoom";
    }

    @PostMapping("/room/new")
    public String makeRoom(ChatRoomForm form){
        chatRoomRepository.createChatRoom(form.getName());

        return "redirect:/";
    }

}
