package com.zhiyou100.video.mapper;

import com.zhiyou100.video.model.Video;
import com.zhiyou100.video.model.VideoExample;
import com.zhiyou100.video.model.VideoTimes;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoMapper {
    int countByExample(VideoExample example);

    int deleteByExample(VideoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    List<Video> selectByExample(VideoExample example);

    Video selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByExample(@Param("record") Video record, @Param("example") VideoExample example);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    
    
    
    
	int findAllVideoCount1(@Param("video_title")String video_title,@Param("speaker_id") int speaker_id,@Param("course_id") int course_id);

	List<Video> findAllVideoByConditions1(@Param("video_title")String video_title,@Param("speaker_id") int speaker_id,@Param("course_id") int course_id,@Param("currentPage") int currentPage);

	int findAllVideoCount(Video vd);

	List<Video> findAllVideoByConditions(Video vd);

	void deleteVideoByIds(int[] rowCheck);

	int findAverQianDuan();

	int findAverReact();

	int findAverUI();

	int findAverPython();

	int findAverJava();

	int[] getAverageVideoTimes();

	List<VideoTimes> getAverageVideoNames(VideoTimes vt);

	List<Video> findVideosByCourseId(@Param("id")Integer id);


}