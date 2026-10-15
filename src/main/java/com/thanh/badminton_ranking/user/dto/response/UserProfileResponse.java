package com.thanh.badminton_ranking.user.dto.response;

import com.thanh.badminton_ranking.common.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public record UserProfileResponse(String username,
                                  Role role) {

}
