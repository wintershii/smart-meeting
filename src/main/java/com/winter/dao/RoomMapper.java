package com.winter.dao;

import com.winter.domain.Room;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Room> selectAll();

    String getRoomNameById(Integer roomId);


}