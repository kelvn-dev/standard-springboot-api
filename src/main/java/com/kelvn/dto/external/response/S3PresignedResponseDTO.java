package com.kelvn.dto.external.response;

import com.kelvn.dto.BaseDTO;
import java.time.Instant;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class S3PresignedResponseDTO implements BaseDTO {
  private String url;
  private Instant expiration;
  private Map<String, String> signedHeaders;
}
