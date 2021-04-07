package com.example.demo.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

/**
 * 为微信的http请求增加 "text/plain" 格式的 Content-Type
 *
 * @author  qi.gou
 * 2021/03/05 21:00
 */

public class WXMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
    public WXMappingJackson2HttpMessageConverter() {
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);
    }
}
