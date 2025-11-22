package com.example.boardservice.board.client;

import com.example.boardservice.board.dto.AddActivityScoreRequestDto;
import com.example.boardservice.board.dto.UserResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class UserClient {
    private final RestClient restClient;

    public UserClient(
            @Value("${client.user-service.url}") String userServiceUrl
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(userServiceUrl)
                .build();
    }

    public Optional<UserResponseDto> fetchUser(Long userId) { // 외부 요청 api (사용자 조회 api 요청)
       try {
           UserResponseDto userResponseDto = this.restClient.get()
                   .uri("/users/{userId}", userId)
                   .retrieve()
                   .body(UserResponseDto.class);
           return Optional.ofNullable(userResponseDto);
       }catch (RestClientException e){
          // log.error("사용자 정보 조회 실패: {}", e.getMessage(), e);
           return Optional.empty();
       }
    }

    public List<UserResponseDto> fetchUserByIds(List<Long> ids){
        try {
            return this.restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/users")
                            .queryParam("ids",ids)
                            .build()
                    )
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
        } catch (RestClientException e){
            // log.error("사용자 정보 조회 실패: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public void addActivityScore(Long userId, int score) {
        AddActivityScoreRequestDto addActivityScoreRequestDto
                = new AddActivityScoreRequestDto(userId, score);
        this.restClient.post()
                .uri("/users/activity-score/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(addActivityScoreRequestDto)
                .retrieve()
                .toBodilessEntity();
    }

}
