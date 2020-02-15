package com.example.springauth.utility;

import com.example.springauth.model.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        Map<String,Object> info=new HashMap<>(accessToken.getAdditionalInformation());

        if (user!=null){

            Integer userId=user.getUserId();

            info.put("id",userId);
            info.put("mail",user.getUserEmail());

        }
        DefaultOAuth2AccessToken customOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        customOAuth2AccessToken.setAdditionalInformation(info);
        return super.enhance(customOAuth2AccessToken, authentication);
    }


}
