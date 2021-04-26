package com.house.demo.dao;

import com.house.demo.model.HouseComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xjj
 */
public interface MessageMapper {

    /**
     * @param id
     * @return
     */
    @Select("select comment_read from house_comment_message where message_comment_to_id = #{id} ")
    List<Integer> getMessageById(int id);


    @Select("select * from house_comment left join house_comment_message on message_comment_id = comment_id  where message_comment_to_id = #{id} ")
    List<HouseComment> getCommentList(int id);

    /**
     * @param id
     * @return
     */
    @Delete("delete from house_comment_message where message_comment_id = #{id}")
    int delMessageById(int id);


    /**
     * @param fromId
     * @param toId
     * @return
     */
    @Insert("insert into house_comment_message(message_comment_to_id, message_comment_from_id,message_comment_id,comment_read) values (#{toId} ,#{fromId},#{commentId} ,0 )")
    int insertMessage(@Param("fromId") int fromId , @Param("toId") int toId,@Param("commentId")int commentId);


    @Update("update house_comment_message set comment_read = 1 where message_comment_to_id =#{toId} ")
    int updateMessage(int toId);
}
