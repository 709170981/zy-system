package com.zy.common.models.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * 用来包装一下角色名称
 */
@Data
@AllArgsConstructor
public class GunsAuthority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }

}