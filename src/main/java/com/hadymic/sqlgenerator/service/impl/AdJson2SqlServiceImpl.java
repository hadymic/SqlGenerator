package com.hadymic.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hadymic.sqlgenerator.mapper.splash.SplashMapper;
import com.hadymic.sqlgenerator.mapper.splash.SplashVideoInfoMapper;
import com.hadymic.sqlgenerator.model.ad.*;
import com.hadymic.sqlgenerator.model.splash.*;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import com.hadymic.sqlgenerator.service.ad.IAdFilterWordService;
import com.hadymic.sqlgenerator.utils.ObjectUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log
@Service
public class AdJson2SqlServiceImpl implements IAdJson2SqlService {

    @Autowired
    private IAdFilterWordService adFilterWordService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SplashVideoInfoMapper splashVideoInfoMapper;

    @Autowired
    private SplashMapper splashMapper;

    @Override
    public boolean saveSplash(SplashJsonRootBean root) {
        if (root == null || root.getData() == null || root.getData().getSplash() == null) {
            return true;
        }
        List<Splash> splashs = root.getData().getSplash();
        boolean flag = true;
        for (Splash splash : splashs) {
            //如果已经存过该splash了，则不重复存储
            if (splash.getId() != null && splashMapper.selectCount(new QueryWrapper<Splash>().eq("id", splash.getId())) > 0) {
                continue;
            }
            //imageInfo
            if (splash.getImage_info() != null) {
                SplashImageInfo imageInfo = splash.getImage_info();
                //将内部url_list对象转化成String
                if (imageInfo.getUrl_list() != null) {
                    List<String> urls = imageInfo.getUrl_list().stream().map(SplashUrlList::getUrl).collect(Collectors.toList());
                    imageInfo.setUrls(urls.toString());
                }
                //保存
                if (autoSave(imageInfo)) {
                    splash.setImage_info_id(imageInfo.getId());
                }
            }
            //logExtra
            if (splash.getLog_extra() != null) {
                SplashLogExtra logExtra = splash.getLog_extra();
                //将内部style_ids数组转化成String
                if (logExtra.getStyle_ids() != null) {
                    logExtra.setStyle_ids_list(logExtra.getStyle_ids().toString());
                }
                //保存
                if (autoSave(logExtra)) {
                    splash.setLog_extra_id(logExtra.getId());
                }
            }
            //shareInfo
            if (splash.getShare_info() != null && autoSave(splash.getShare_info())) {
                splash.setShare_info_id(splash.getShare_info().getId());
            }
            //skipInfo
            if (splash.getSkip_info() != null && autoSave(splash.getSkip_info())) {
                splash.setSkip_info_id(splash.getSkip_info().getId());
            }
            //videoInfo
            if (splash.getVideo_info() != null) {
                SplashVideoInfo videoInfo = splash.getVideo_info();
                videoInfo.setVideo_urls(videoInfo.getVideo_url_list().toString());
                videoInfo.setPlay_track_urls(videoInfo.getPlay_track_url_list().toString());
                videoInfo.setPlayover_track_urls(videoInfo.getPlayover_track_url_list().toString());
                videoInfo.setAction_track_urls(videoInfo.getAction_track_url_list().toString());

                //如果存在id则更新
                if (videoInfo.getVideo_id() != null && splashVideoInfoMapper.selectCount(new QueryWrapper<SplashVideoInfo>().eq("video_id", videoInfo.getVideo_id())) > 0) {
                    splashVideoInfoMapper.updateById(videoInfo);
                    splash.setVideo_info_id(videoInfo.getVideo_id());
                } else if (autoSave(videoInfo)) {//否则自动保存
                    splash.setVideo_info_id(videoInfo.getVideo_id());
                }
            }
            splash.setClick_track_urls(splash.getClick_track_url_list().toString());
            splash.setTrack_urls(splash.getTrack_url_list().toString());
            //保存splash
            flag &= autoSave(splash);
        }
        return flag;
    }

    @Override
    public boolean saveAd(AdJsonRootBean root) {

        if (root.getData() != null) {
            AdData data = root.getData();
            //AdAdslot
            if (data.getAdslot() != null && autoSave(data.getAdslot())) {
                data.setAdslot_id(data.getAdslot().getId());
            }
            //AdApp
            if (data.getApp() != null && autoSave(data.getApp())) {
                data.setApp_id(data.getApp().getId());
            }
            //AdClickArea
            if (data.getClick_area() != null && autoSave(data.getClick_area())) {
                data.setClick_area_id(data.getClick_area().getId());
            }
            //AdDeepLink
            if (data.getDeep_link() != null && autoSave(data.getDeep_link())) {
                data.setDeep_link_id(data.getDeep_link().getId());
            }
            //AdDownloadConf
            if (data.getDownload_conf() != null && autoSave(data.getDownload_conf())) {
                data.setDownload_conf_id(data.getDownload_conf().getId());
            }
            //AdExt
            if (data.getExt() != null && autoSave(data.getExt())) {
                data.setExt_id(data.getExt().getId());
            }
            //NewsadFilterWords
            if (data.getFilter_words() != null && saveFilterWords(data.getFilter_words())) {
                List<String> filterWordsIds = data.getFilter_words().stream().map(AdFilterWord::getId).collect(Collectors.toList());
                data.setFilter_words_ids(filterWordsIds.toString());
            }
            //AdIcon
            if (data.getIcon() != null && autoSave(data.getIcon())) {
                data.setIcon_id(data.getIcon().getId());
            }
            //AdImage
            if (data.getImage() != null) {
                autoSaveList(data.getImage());
                List<Integer> imageIds = data.getImage().stream().map(AdImage::getId).collect(Collectors.toList());
                data.setImage_ids(imageIds.toString());
            }
            //AdMediaExt
            if (data.getMedia_ext() != null && autoSave(data.getMedia_ext())) {
                data.setMedia_ext_id(data.getMedia_ext().getId());
            }
            //SessionParams
            if (data.getSession_params() != null && autoSave(data.getSession_params())) {
                data.setSession_params_id(data.getSession_params().getId());
            }
            //ShowUrl
            if (data.getShow_url() != null) {
                data.setShow_url_list(data.getShow_url().toString());
            }
            //AdTplInfo
            if (data.getTpl_info() != null && saveTplInfo(data.getTpl_info())) {
                data.setTpl_info_id(data.getTpl_info().getId());
            }
            //AdVideo
            if (data.getVideo() != null && autoSave(data.getVideo())) {
                data.setVideo_id(data.getVideo().getId());
            }
            autoSave(data);
            root.setAd_data_id(data.getId());
        }
        //event
        if (root.getEvent() != null && autoSave(root.getEvent())) {
            root.setEvent_id(root.getEvent().getId());
        }
        return autoSave(root);
    }

    /**
     * 自动识别传入对象，并将其存入数据库
     *
     * @param obj
     * @return
     */
    private boolean autoSave(Object obj) {
        //如果一个对象的所有属性都是null，如果还没有主键，不用存至数据库
        if (ObjectUtils.checkObjAllFieldIsNull(obj)) {
            return false;
        }

        String name = obj.getClass().getName();
        String simpleName = obj.getClass().getSimpleName();
        int index = name.lastIndexOf(".");
        //通过替换获得service包名
        String classPath = name.substring(0, index).replace("model", "service");

        //获取service对象
        IService service;
        try {
            Class serviceClass = Class.forName(classPath + ".I" + simpleName + "Service");
            //从spring容器中拿实例
            service = (IService) applicationContext.getBean(serviceClass);
        } catch (Exception e) {
            log.info(Arrays.toString(e.getStackTrace()));
            return false;
        }

        //保存至数据库
        boolean flag = service.saveOrUpdate(obj);
        String res = flag ? "success" : "fail";
        log.info("save2Sql: " + res + " : " + obj.toString());
        return flag;
    }

    /**
     * 自动识别传入对象，并将其存入数据库
     *
     * @param objList
     * @return
     */
    private boolean autoSaveList(List<?> objList) {
        Object obj = objList.get(0);
        String name = obj.getClass().getName();
        String simpleName = obj.getClass().getSimpleName();
        int index = name.lastIndexOf(".");
        //通过替换获得service包名
        String classPath = name.substring(0, index).replace("model", "service");

        //获取service对象
        IService service;
        try {
            Class serviceClass = Class.forName(classPath + ".I" + simpleName + "Service");
            //从spring容器中拿实例
            service = (IService) applicationContext.getBean(serviceClass);
        } catch (Exception e) {
            log.info(Arrays.toString(e.getStackTrace()));
            return false;
        }

        //保存至数据库
        boolean flag = true;
        for (Object o : objList) {
            //如果一个对象的所有属性都是null，如果还没有主键，不用存至数据库
            if (ObjectUtils.checkObjAllFieldIsNull(o)) {
                continue;
            }
            flag &= service.saveOrUpdate(o);
            String res = flag ? "success" : "fail";
            log.info("save2Sql: " + res + " : " + o.toString());
        }
        return flag;
    }

    private boolean saveFilterWords(List<AdFilterWord> filterWords) {
        boolean flag = true;
        for (AdFilterWord filterWord : filterWords) {
            if (filterWord.getOptions() != null) {
                for (AdFilterWord option : filterWord.getOptions()) {
                    flag &= adFilterWordService.saveOrUpdate(option);
                    String res = flag ? "success" : "fail";
                    log.info("save2Sql: " + res + " : " + option.toString());
                }
                List<String> optionIds = filterWord.getOptions().stream().map(AdFilterWord::getId).collect(Collectors.toList());
                filterWord.setOptions_ids(optionIds.toString());
            }
            flag &= adFilterWordService.saveOrUpdate(filterWord);
            String res = flag ? "success" : "fail";
            log.info("save2Sql: " + res + " : " + filterWord.toString());
        }
        return flag;
    }

    private boolean saveTplInfo(AdTplInfo tplInfo) {
        //tpl_info中的data
        if (tplInfo.getData() != null) {
            //将TplInfoData 根数据中的id原先为root，改为ASSIGN_ID作为主键
            tplInfo.getData().setId(null);
            if (saveTplInfoData(tplInfo.getData())) {
                tplInfo.setTpl_info_data_id(tplInfo.getData().getId());
            }
        }
        //tpl_info中的diff_data
        if (tplInfo.getDiff_data() != null && saveDiffData(tplInfo.getDiff_data())) {
            tplInfo.setDiff_data_id(tplInfo.getDiff_data().getId());
        }
        //tpl_info中的dynamic_creative
        if (tplInfo.getDynamic_creative() != null && autoSave(tplInfo.getDynamic_creative())) {
            tplInfo.setDynamic_creative_id(tplInfo.getDynamic_creative().getId());
        }
        return autoSave(tplInfo);
    }

    private boolean saveTplInfoData(AdTplInfoData tplInfoData) {
        if (tplInfoData.getChildren() == null) {
            if (tplInfoData.getValues() != null && saveValues(tplInfoData.getValues())) {
                tplInfoData.setValues_id(tplInfoData.getValues().getId());
            }
            return autoSave(tplInfoData);
        }

        //递归将所有children保存
        tplInfoData.getChildren().forEach(this::saveTplInfoData);

        if (tplInfoData.getValues() != null && saveValues(tplInfoData.getValues())) {
            tplInfoData.setValues_id(tplInfoData.getValues().getId());
        }

        List<String> ids = tplInfoData.getChildren().stream().map(AdTplInfoData::getId).collect(Collectors.toList());
        tplInfoData.setChildren_ids(ids.toString());
        return autoSave(tplInfoData);
    }

    private boolean saveValues(AdValues values) {
        if (values.getAnimations() != null) {
            autoSaveList(values.getAnimations());
            List<Integer> animationIds = values.getAnimations().stream().map(AdAnimation::getId).collect(Collectors.toList());
            values.setAnimations_ids(animationIds.toString());
        }
        return autoSave(values);
    }

    private boolean saveDiffData(AdDiffData diffData) {
        //diff_data
        if (diffData.getDiff_data() != null) {
            AdDiffData data = diffData.getDiff_data();
            //values
            if (data.getValues() != null && saveValues(data.getValues())) {
                data.setValues_id(data.getValues().getId());
            }
            //children
            if (data.getChildren() != null) {
                data.getChildren().forEach(this::saveDiffDataChildren);
                List<String> ids = data.getChildren().stream().map(AdDiffDataChildren::getId).collect(Collectors.toList());
                data.setChildren_ids(ids.toString());
            }

            autoSave(data);
            diffData.setDiff_data_id(data.getId());
        }
        return autoSave(diffData);
    }

    private boolean saveDiffDataChildren(AdDiffDataChildren diffDataChildren) {
        if (diffDataChildren.getChildren() == null) {
            return autoSave(diffDataChildren);
        }

        //递归将所有children保存
        diffDataChildren.getChildren().forEach(this::saveDiffDataChildren);

        List<String> ids = diffDataChildren.getChildren().stream().map(AdDiffDataChildren::getId).collect(Collectors.toList());
        diffDataChildren.setChildren_ids(ids.toString());
        return autoSave(diffDataChildren);
    }
}
