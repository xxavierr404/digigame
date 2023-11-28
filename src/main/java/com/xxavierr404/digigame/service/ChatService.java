package com.xxavierr404.digigame.service;

import com.xxavierr404.digigame.dao.ChatMemberRepository;
import com.xxavierr404.digigame.dao.ChatRepository;
import com.xxavierr404.digigame.dao.UserRepository;
import com.xxavierr404.digigame.domain.Chat;
import com.xxavierr404.digigame.domain.ChatMember;
import com.xxavierr404.digigame.dto.ChatDto;
import com.xxavierr404.digigame.dto.mapper.ChatMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository repository;
    private final UserRepository userRepository;
    private final ChatMemberRepository memberRepository;
    private final ChatMapper mapper;

    public void create(ChatDto chatDto, Long userId) {
        var chat = mapper.fromDto(chatDto);
        var savedChat = repository.save(chat);
        addToChat(userId, savedChat.getId(), "JOINED");
    }

    @Transactional
    public void addToChat(Long userId, Long chatId, String state) {
        var user = userRepository.findById(userId).orElseThrow();
        var chat = repository.findById(chatId).orElseThrow();
        var member = new ChatMember();
        member.setUser(user);
        member.setChat(chat);
        member.setJoinTime(LocalDateTime.now());
        member.setState(state);
        memberRepository.save(member);
    }

    public Optional<Chat> readOne(Long id) {
        return repository.findById(id);
    }

    public boolean update(Chat chat) {
        var searchResult = repository.findById(chat.getId());
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.save(chat);
        return true;
    }

    public boolean delete(Long id) {
        var searchResult = repository.findById(id);
        if (searchResult.isEmpty()) {
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public void inviteUserToChat(Long chatId, String nickname) {
        var chat = repository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found"));
        var user = userRepository.getByNickname(nickname).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (memberRepository
                .findAllByUserId(user.getId())
                .stream()
                .map(ChatMember::getChat)
                .noneMatch(chat1 -> chat.getId().equals(chat1.getId()))) {
            addToChat(user.getId(), chatId, "PENDING");
        }
    }

    @Transactional
    public void acceptInviteToChat(Long chatId, Long userId) {
        var chat = repository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found"));
        var user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (memberRepository
                .findAllByUserId(user.getId())
                .stream()
                .map(ChatMember::getChat)
                .anyMatch(chat1 -> chat.getId().equals(chat1.getId()))) {
            var chatMember = memberRepository.findByUserIdAndChatId(userId, chatId).orElseThrow();
            chatMember.setState("JOINED");
            memberRepository.save(chatMember);
        }
    }

    @Transactional
    public void declineInviteToChat(Long chatId, Long userId) {
        var chat = repository.findById(chatId).orElseThrow(() -> new EntityNotFoundException("Chat not found"));
        var user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        if (memberRepository
                .findAllByUserId(user.getId())
                .stream()
                .map(ChatMember::getChat)
                .anyMatch(chat1 -> chat.getId().equals(chat1.getId()))) {
            memberRepository.removeByUserIdAndChatId(userId, chatId);
        }
    }
}
