package rami.dev.ramiblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rami.dev.ramiblog.dto.user.UserRequest;
import rami.dev.ramiblog.model.user.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    // insert, update, delete -> try catch 처리
    @Transactional
    public void 회원가입(UserRequest.JoinInDTO joinInDTO){
        try{
            // 1. 패스워드 암호화
            joinInDTO.setPassword(passwordEncoder.encode(joinInDTO.getPassword()));
            // 2. DB 저장
            userRepository.save(joinInDTO.toEntity());
        }catch (Exception e){
            throw new RuntimeException("회원가입 오류 : "+e.getMessage());
        }

    } // 더티체킹, DB 세션 종료 (OSIV=false)
}
