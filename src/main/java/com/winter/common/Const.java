package com.winter.common;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 一些常量
 */
public class Const {

    public static  final String CURRENT_USER = "currentUser";

    public static final String EMAIL = "email";

    public static final String PHONE = "phone";

    //用户身份常量
    public interface Role {
        static final int USER = 0;
        static final int MANAGER = 1;
    }

    //会议室状态常量
    public interface RoomStatus{
        static final int FREE = 1;
        static final int USE = 2;
        static final int FIX = 3;
    }

    //会议进展状态
    public interface MeetingStatus{
        static final int OVER = 1;
        static final int ONGOING = 2;
        static final int NOTSTART = 3;
        static final int CANCEL = 4;
    }

    //会议时用户情况
    public interface UserPerform{
        static final int NORMAL = 1;
        static final int ABSENCE = 2;
        static final int LATE = 3;
        static final int LEAVE = 4;
    }

    public interface OnlineMeetingStatus{
        static final int ONGOING = 1;
        static final int OVER = 0;
    }
}
