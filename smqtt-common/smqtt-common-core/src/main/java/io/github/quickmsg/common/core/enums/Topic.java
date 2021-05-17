package io.github.quickmsg.common.core.enums;

import lombok.Getter;

/**
 * @ClassName: Topic
 * @Description:
 * @Date: 2021/3/30 14:19
 * @Author: songjg
 */
@Getter
public enum Topic {
    DATAS("datas"),
    TOPO_ADD("topo/add"),
    CONTAINER_COMMAND("container/command"),
    CONTAINER_REPLY("container/reply"),
    CONTAINER_DATA("container/data"),
    APP_COMMAND("app/command"),
    APP_REPLY("app/reply"),
    APP_DATA("app/data"),
    NULL("");
    private String type;
    Topic( String type){
        this.type = type;
    }
}
