package com.kelvn.controller.external;

import com.kelvn.controller.SecuredRestController;
import com.kelvn.dto.external.response.S3PresignedResponseDTO;
import com.kelvn.enums.ContentDisposition;
import com.kelvn.service.external.S3Service;
import com.kelvn.utils.RegexUtils;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
   * @apiNote to add signedHeaders into header request
   */
  @GetMapping
  public ResponseEntity<S3PresignedResponseDTO> getPresignedGetUrl(
      @RequestParam String key, @RequestParam ContentDisposition contentDisposition) {
    return ResponseEntity.ok(s3Service.getPresignedUrl(key, contentDisposition));
  }

  /**
   * @param params user-defined metadata
   * @apiNote select binary tab in Body section to upload file while using with postman
   * @apiNote to add signedHeaders into header request
   */
  @PutMapping
  public ResponseEntity<S3PresignedResponseDTO> getPresignedPutUrl(
      @Parameter(schema = @Schema(pattern = RegexUtils.simpleFilename)) @RequestParam
          String filename,
      @RequestParam ObjectCannedACL acl,
      @RequestBody Map<String, String> params) {
    return ResponseEntity.ok(s3Service.getPresignedUrl(filename, acl, params));
  }
}
