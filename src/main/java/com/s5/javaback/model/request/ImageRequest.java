package com.s5.javaback.model.request;

import lombok.Data;

@Data
public class ImageRequest {
  private final String url, description;
}
