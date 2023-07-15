package com.kelvn.service.external;

import com.kelvn.dto.external.response.S3PresignedResponseDTO;
import com.kelvn.enums.ContentDisposition;
import com.kelvn.exception.BadRequestException;
import com.kelvn.utils.HelperUtils;
import com.kelvn.utils.MappingUtils;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;
import javax.naming.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ObjectCannedACL;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
@RequiredArgsConstructor
public class S3Service {

  @Value("${aws.s3.bucket.name}")
  private String s3BucketName;

  //  private final S3Client s3Client;
  private final S3Presigner s3Presigner;
  private final MappingUtils mappingUtils;

  /** For uploading */
  @SneakyThrows({IOException.class, ServiceUnavailableException.class})
  public S3PresignedResponseDTO getPresignedUrl(
      String filename, ObjectCannedACL acl, Map<String, String> metadata) {
    try {
      String[] parts = filename.split("\\.");
      if (parts.length != 2) {
        throw new BadRequestException("Invalid file name");
      }
      String contentType = Files.probeContentType(Paths.get(filename));
      if (contentType == null) {
        throw new BadRequestException("Invalid file extension");
      }

      String randomString = HelperUtils.getRandomString();
      String name = parts[0];
      String extension = parts[1];
      String key = String.format("%s_%s.%s", name, randomString, extension);

      PutObjectRequest objectRequest =
          PutObjectRequest.builder()
              .bucket(s3BucketName)
              .key(key)
              .metadata(metadata)
              .contentType(contentType)
              .acl(acl)
              .build();

      PutObjectPresignRequest presignRequest =
          PutObjectPresignRequest.builder()
              .signatureDuration(Duration.ofMinutes(10))
              .putObjectRequest(objectRequest)
              .build();

      PresignedPutObjectRequest presignedPutObjectRequest =
          s3Presigner.presignPutObject(presignRequest);

      S3PresignedResponseDTO responseDTO =
          mappingUtils.mapToDTO(presignedPutObjectRequest, S3PresignedResponseDTO.class);
      responseDTO.setSignedHeaders(
          HelperUtils.formatS3SignedHeaders(responseDTO.getSignedHeaders()));
      return responseDTO;
    } catch (S3Exception e) {
      throw new ServiceUnavailableException(e.getMessage());
    }
  }

  /** For retrieving */
  @SneakyThrows(ServiceUnavailableException.class)
  public S3PresignedResponseDTO getPresignedUrl(String key, ContentDisposition contentDisposition) {
    try {
      GetObjectRequest getObjectRequest =
          GetObjectRequest.builder()
              .bucket(s3BucketName)
              .key(key)
              .responseContentDisposition(
                  ContentDisposition.ATTACHMENT.equals(contentDisposition)
                      ? "attachment; filename=\"" + key.concat(".png") + "\""
                      : ContentDisposition.INLINE.toString())
              .build();

      GetObjectPresignRequest getObjectPresignRequest =
          GetObjectPresignRequest.builder()
              .signatureDuration(Duration.ofMinutes(10))
              .getObjectRequest(getObjectRequest)
              .build();

      PresignedGetObjectRequest presignedGetObjectRequest =
          s3Presigner.presignGetObject(getObjectPresignRequest);

      S3PresignedResponseDTO responseDTO =
          mappingUtils.mapToDTO(presignedGetObjectRequest, S3PresignedResponseDTO.class);
      responseDTO.setSignedHeaders(
          HelperUtils.formatS3SignedHeaders(responseDTO.getSignedHeaders()));
      return responseDTO;
    } catch (S3Exception e) {
      throw new ServiceUnavailableException(e.getMessage());
    }
  }
}
