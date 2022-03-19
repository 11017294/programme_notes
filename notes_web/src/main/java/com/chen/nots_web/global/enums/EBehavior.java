package com.chen.nots_web.global.enums;

import com.chen.nots_web.global.BaseSysConf;
import com.chen.nots_web.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 行为枚举类
 * </p>
 *
 * @author: MaybeBin
 * @createTime: 2022-03-19
 */
@Slf4j
public enum EBehavior {

    /**
     * 用户行为
     */
    NOTE_TAG("点击标签", "note_tag"),
    NOTE_SORT("点击笔记分类", "note_sort"),
    NOTE_CONTENT("点击笔记", "note_content"),
    NOTE_PRAISE("点赞", "note_praise"),
    FRIENDSHIP_LINK("点击友情链接", "friendship_link"),
    NOTE_SEARCH("点击搜索", "note_search"),
    STUDY_VIDEO("点击学习视频", "study_video"),
    VISIT_PAGE("访问页面", "visit_page"),
    VISIT_CLASSIFY("点击笔记分类", "visit_classify"),
    VISIT_SORT("点击归档", "visit_sort"),
    NOTE_AUTHOR("点击作者", "note_author"),
    PUBLISH_COMMENT("发表评论", "publish_comment"),
    PUBLISH_NOTE("增加笔记", "publish_note"),
    DELETE_NOTE("删除笔记", "delete_note"),
    EDIT_NOTE("修改笔记", "edit_note"),
    DELETE_COMMENT("删除评论", "delete_comment"),
    REPORT_COMMENT("举报评论", "report_comment"),
    VISIT_TAG("点击笔记标签页面", "visit_tag");

    private String content;
    private String behavior;

    EBehavior(String content, String behavior) {
        this.content = content;
        this.behavior = behavior;
    }

    /**
     * 根据value返回枚举类型，主要在switch中使用
     *
     * @param value
     * @return
     */
    public static EBehavior getByValue(String value) {
        for (EBehavior behavior : values()) {
            if (behavior.getBehavior() == value) {
                return behavior;
            }
        }
        return null;
    }

    public static Map<String, String> getModuleAndOtherData(EBehavior behavior,
                                                            Map<String, Object> nameAndArgsMap,
                                                            String bussinessName) {
        String otherData = "";
        String moduleUid = "";
        switch (behavior) {
            case NOTE_AUTHOR: {
                // 判断是否是点击作者
                if (nameAndArgsMap.get(BaseSysConf.AUTHOR) != null) {
                    otherData = nameAndArgsMap.get(BaseSysConf.AUTHOR).toString();
                }
            }
            break;
            case NOTE_SORT: {
                // 判断是否点击笔记分类
                if (nameAndArgsMap.get(BaseSysConf.NOTE_SORT_UID) != null) {
                    moduleUid = nameAndArgsMap.get(BaseSysConf.NOTE_SORT_UID).toString();
                }
            }
            break;
            case NOTE_TAG: case VISIT_TAG:{
                // 判断是否点击笔记标签
                if (nameAndArgsMap.get(BaseSysConf.TAG_UID) != null) {
                    moduleUid = nameAndArgsMap.get(BaseSysConf.TAG_UID).toString();
                }
            }
            break;
            case NOTE_SEARCH: {
                // 判断是否进行搜索
                if (nameAndArgsMap.get(BaseSysConf.KEYWORDS) != null) {
                    otherData = nameAndArgsMap.get(BaseSysConf.KEYWORDS).toString();
                }
            }
            break;
            case VISIT_CLASSIFY: {
                // 判断是否点击分类
                if (nameAndArgsMap.get(BaseSysConf.NOTE_SORT_UID) != null) {
                    moduleUid = nameAndArgsMap.get(BaseSysConf.NOTE_SORT_UID).toString();
                }
            }
            break;
            case VISIT_SORT: {
                // 判断是否点击归档
                if (nameAndArgsMap.get(BaseSysConf.MONTH_DATE) != null) {
                    otherData = nameAndArgsMap.get(BaseSysConf.MONTH_DATE).toString();
                }
            }
            break;
            case NOTE_CONTENT: {
                // 判断是否笔记详情
                if (nameAndArgsMap.get(BaseSysConf.UID) != null) {
                    moduleUid = nameAndArgsMap.get(BaseSysConf.UID).toString();
                }
            }
            break;
            case VISIT_PAGE: {
                // 访问页面
                if (nameAndArgsMap.get(BaseSysConf.PAGE_NAME) != null) {
                    otherData = nameAndArgsMap.get(BaseSysConf.PAGE_NAME).toString();
                } else {
                    otherData = bussinessName;
                }
            }
            break;
            case PUBLISH_COMMENT: {
                // 发表评论
                Object object = nameAndArgsMap.get(BaseSysConf.COMMENT_VO);
                Map<String, Object> map = JsonUtils.objectToMap(object);
                if (map.get(BaseSysConf.CONTENT) != null) {
                    otherData = map.get(BaseSysConf.CONTENT).toString();
                }
            }
            break;
            case PUBLISH_NOTE: {
                // 增加笔记
                if (nameAndArgsMap.get(BaseSysConf.CONTENT) != null) {
                    otherData = nameAndArgsMap.get(BaseSysConf.CONTENT).toString();
                }
            }
            break;
            case DELETE_NOTE: {
                // 删除笔记
                if (nameAndArgsMap.get(BaseSysConf.UID) != null) {
                    otherData = nameAndArgsMap.get(BaseSysConf.UID).toString();
                }
            }
            break;
            case EDIT_NOTE: {
                // 修改笔记
                if (nameAndArgsMap.get(BaseSysConf.UID) != null) {
                    otherData = nameAndArgsMap.get(BaseSysConf.UID).toString();
                }
            }
            break;
            default: {
                log.info("其它行为");
            }
        }
        Map<String, String> result = new HashMap<>();
        result.put(BaseSysConf.MODULE_UID, moduleUid);
        result.put(BaseSysConf.OTHER_DATA, otherData);
        return result;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}
