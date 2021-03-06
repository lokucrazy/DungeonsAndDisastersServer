package com.mudndcapstone.server.services

import com.amazonaws.AmazonServiceException
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.DeleteObjectRequest
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.S3Object
import com.mudndcapstone.server.models.Map
import com.mudndcapstone.server.models.Session
import com.mudndcapstone.server.models.dto.MapDto
import com.mudndcapstone.server.repositories.MapRepository
import com.mudndcapstone.server.utils.Auditor
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.util.stream.Collectors

@Service
class MapService {

    @Autowired String awsS3AudioBucket
    @Autowired AmazonS3 amazonS3
    @Autowired MapRepository mapRepository
    @Autowired ModelMapper modelMapper

    Set<Map> getAllMaps() {
        mapRepository.findAll().toSet()
    }

    Map getMapById(String id) {
        mapRepository.findById(id).orElse(null)
    }

    Map upsertMap(Map map) {
        if (!map.session) return null

        Auditor.enableAuditing(map)
        mapRepository.save(map)
    }

    Map addImage(Map map, MultipartFile image) {
        String imageName = image.getOriginalFilename()

        try {
            File img = new File(imageName)
            FileOutputStream fos = new FileOutputStream(img)
            fos.write(image.getBytes())
            fos.close()

            PutObjectRequest putObjectRequest = new PutObjectRequest(awsS3AudioBucket, imageName, img)
            putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead)
            amazonS3.putObject(putObjectRequest)

            map.images == null ? map.images = [imageName] : map.images << imageName

            Auditor.enableAuditing(map)
            mapRepository.save(map)
        } catch (IOException | AmazonServiceException ex) {
            return null
        }
    }

    String getImage(String imageName) {
        try {
            amazonS3.getUrl(awsS3AudioBucket, imageName)
        } catch (IOException | AmazonServiceException ex) {
            return null
        }
    }

    boolean deleteImage(String imageName) {
        try {
            amazonS3.deleteObject(new DeleteObjectRequest(awsS3AudioBucket,imageName))
            true
        } catch (AmazonServiceException ex) {
            return false
        }
    }

    Map buildAndCreateMap(MapDto mapDto, Session session) {
        Map mapRequest = buildMapFrom(mapDto, session)

        upsertMap(mapRequest)
    }

    void deleteMap(String id) {
        mapRepository.deleteById(id)
    }

    Map buildMapFrom(MapDto mapDto, Session session) {
        Map map = modelMapper.map(mapDto, Map)

        map.session = session

        map
    }

    MapDto buildDtoFrom(Map map) {
        MapDto mapDto = modelMapper.map(map, MapDto)

        String sessionId = map.session ? map.session.identifier : null
        mapDto.setSessionId(sessionId)

        mapDto
    }

    Set<MapDto> buildDtoSetFrom(Set<Map> maps) {
        if (!maps) return []
        maps.stream().map({ map -> buildDtoFrom(map) }).collect(Collectors.toSet())
    }

}
