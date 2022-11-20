package com.s5.javaback.model.response;

import lombok.Data;

@Data
public class ImageResponse {
  private final Long id;
  private final String url, description;
}
