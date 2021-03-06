package com.liu.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.liu.common.api.CommonResult;
import com.liu.dto.BucketPolicyConfigDto;
import com.liu.dto.MinioUploadDto;
import com.liu.mbg.model.Video;
import com.liu.nosql.elasticsearch.document.EsVideo;
import com.liu.nosql.mongodb.document.VideoInfo;
import com.liu.service.EsVideoService;
import com.liu.service.VideoInfoService;
import com.liu.service.VideoService;
import io.minio.*;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/minio")
public class MinioController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinioController.class);
    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    @Autowired
    private VideoService videoService;

    @Autowired
    private VideoInfoService videoInfoService;

    @Autowired
    private EsVideoService esVideoService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult upload(@RequestParam("file") MultipartFile file,
                               @RequestParam("filename") String filename,
                               @RequestParam(value = "description",required = false) String description,
                               @RequestParam(value = "username",required = false) String username,
                               @RequestParam(value = "category",defaultValue = "0") String category) {
        try {
            //创建一个MinIO的Java客户端
            MinioClient minioClient = MinioClient.builder()
                    .endpoint(ENDPOINT)
                    .credentials(ACCESS_KEY, SECRET_KEY)
                    .build();
            boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
            if (isExist) {
                LOGGER.info("存储桶已经存在！");
            } else {
                //创建存储桶并设置只读权限
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
                BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(BUCKET_NAME);
                SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                        .bucket(BUCKET_NAME)
                        .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                        .build();
                minioClient.setBucketPolicy(setBucketPolicyArgs);
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            // 设置存储对象名称
            String objectName = filename;
            // 使用putObject上传一个文件到存储桶中
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .bucket(BUCKET_NAME)
                    .object(objectName)
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
            minioClient.putObject(putObjectArgs);
            LOGGER.info("文件上传成功!");
            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);

            /*
             *   首先将视频上传到视频服务器中
             *   然后将视频信息写入到mysql中
             *   最后将视频信息写入到elasticsearch中
             *
             *   这个地方代码有点冗余
             * */
            Video video=new Video();
            video.setName(filename);
            video.setUserName(username);
            video.setDescription(description);
            video.setPath(minioUploadDto.getUrl());
            video.setCreateTime(new Date());
            video.setModifiedTime(new Date());
            video.setCategory(category);
            videoService.createVideo(video);

            Long id = video.getId();
            LOGGER.info("视频信息已插入到elasticsearch中");
            //esVideoService.create(video);

            LOGGER.info("视频信息已插入到mongoDB中");
            VideoInfo videoInfo = createVideoInfo(video);
            videoInfo.setCategory(category);
            videoInfoService.create(videoInfo);
            return CommonResult.success(minioUploadDto);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("上传发生错误: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }

    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::" + bucketName + "/*.**").build();
        return BucketPolicyConfigDto.builder()
                .Version("2020-10-22")
                .Statement(CollUtil.toList(statement))
                .build();
    }

    private static VideoInfo createVideoInfo(Video video) {
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setId(String.valueOf(video.getId()));
        videoInfo.setUsername(video.getUserName());
        videoInfo.setVideoName(video.getName());
        videoInfo.setCategory(video.getCategory());
        videoInfo.setVideoPath(video.getPath());
        videoInfo.setDescription(video.getDescription());
        videoInfo.setCreateTime(video.getCreateTime());
        videoInfo.setModifiedTime(video.getModifiedTime());
        videoInfo.setViewCount(0L);
        return videoInfo;
    }
}
