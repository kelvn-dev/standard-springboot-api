package com.kelvn.controller.external;

import com.kelvn.controller.SecuredRestController;
import com.kelvn.enums.ContentDisposition;
import com.kelvn.service.external.S3Service;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URL;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;

@Tag(name = "S3")
@RestController
@RequestMapping("/s3/presigned-url")
@RequiredArgsConstructor
public class S3Controller implements SecuredRestController {

  private final S3Service s3Service;

  /**
   * @apiNote postman cannot download, use browser to receive correct behavior
   */
  @GetMapping
  public ResponseEntity<URL> getPresignedGetUrl(
      @RequestParam String key, @RequestParam ContentDisposition contentDisposition) {
    URL url = s3Service.getPresignedUrl(key, contentDisposition);
    return ResponseEntity.ok(url);
  }

  /**
   * @param params user-defined metadata (to add header request x-amz-meta-${key}: ${value})
   * @apiNote select binary tab in Body section to upload file while using with postman
   */
  @PutMapping
  public ResponseEntity<URL> getPresignedPutUrl(
      @RequestParam String contentType,
      @RequestParam ObjectCannedACL acl,
      @RequestBody Map<String, String> params) {
    URL url = s3Service.getPresignedUrl(contentType, acl, params);
    return ResponseEntity.ok(url);
  }
}
