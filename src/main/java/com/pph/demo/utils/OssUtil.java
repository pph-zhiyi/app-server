package com.pph.demo.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author pph
 * @datetime 2020/8/5 20:06
 * @description
 */
public final class OssUtil {
    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(OssUtil.class);

    private OssUtil() {
    }

    @Value("${aliyun.oss.endpoint}")
    private String ossEndpoint;

    @Value("${aliyun.oss.ak}")
    private String ossAk;

    @Value("${aliyun.oss.sk}")
    private String ossSk;

    @PostConstruct
    public void init() {
        OssUtil.endpoint = ossEndpoint;
        OssUtil.accessKeyId = ossAk;
        OssUtil.accessKeySecret = ossSk;
    }

    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

    private static String accessKeyId = "LTAI4G6XzSoJ28eYaPSWJ8mi";

    private static String accessKeySecret = "O4PXxGrsXNrLF7GxUGJQ0JwP5cftsV";

    public static String uploadFile(String bucketName, String objectName, File file) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, file);

            // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
            LOGGER.info("*** uploadFile result: {}", result);

            // 过期时间 100 年
            Date expiration = new Date(TimeUnit.MILLISECONDS.convert(365 * 100, TimeUnit.DAYS));
            String url = ossClient.generatePresignedUrl(bucketName, objectName, expiration).toString();
            LOGGER.info("*** uploadFile url: {}", url);

            return url;
        } catch (Exception e) {
            LOGGER.error("*** uploadFile error: {}", e.getMessage());
            throw new RuntimeException(String.format("uploadFile error: %s", e.getMessage()));
        } finally {
            if (Objects.nonNull(ossClient)) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    public static String uploadFile(String bucketName, String objectName, InputStream inputStream) {
        OSS ossClient = null;
        try {
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, inputStream);

            // 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
            // ObjectMetadata metadata = new ObjectMetadata();
            // metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
            // metadata.setObjectAcl(CannedAccessControlList.Private);
            // putObjectRequest.setMetadata(metadata);

            // 上传文件。
            ossClient.putObject(putObjectRequest);

            // 过期时间 100 年
            Date expiration = new Date(TimeUnit.MILLISECONDS.convert(365 * 100, TimeUnit.DAYS));
            String url = ossClient.generatePresignedUrl(bucketName, objectName, expiration).toString();
            LOGGER.info("*** uploadFile url: {}", url);

            return url;
        } catch (Exception e) {
            LOGGER.error("*** uploadFile error: {}", e.getMessage());
            throw new RuntimeException(String.format("uploadFile error: %s", e.getMessage()));
        } finally {
            if (Objects.nonNull(ossClient)) {
                // 关闭OSSClient。
                ossClient.shutdown();
            }
        }
    }

    public static void main(String[] args) {
        File file = new File("/Users/pph/Desktop/all/img/java8/Lambda.jpg");
        System.out.println(uploadFile("gzd-img", "Lambda.jpg", file));
    }
}
