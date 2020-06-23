package com.hadymic.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hadymic.sqlgenerator.model.*;
import com.hadymic.sqlgenerator.service.IAdFilterWordService;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import com.hadymic.sqlgenerator.utils.ObjectUtils;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log
@Service
public class AdJson2SqlServiceImpl implements IAdJson2SqlService {

    @Autowired
    private IAdFilterWordService adFilterWordService;

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public boolean save(JsonRootBean root) {

        if (root.getData() != null) {
            AdData data = root.getData();
            //Adslot
            if (data.getAdslot() != null && autoSave(data.getAdslot())) {
                data.setAdslot_id(data.getAdslot().getId());
            }
            //App
            if (data.getApp() != null && autoSave(data.getApp())) {
                data.setApp_id(data.getApp().getId());
            }
            //ClickArea
            if (data.getClick_area() != null && autoSave(data.getClick_area())) {
                data.setClick_area_id(data.getClick_area().getId());
            }
            //DeepLink
            if (data.getDeep_link() != null && autoSave(data.getDeep_link())) {
                data.setDeep_link_id(data.getDeep_link().getId());
            }
            //DownloadConf
            if (data.getDownload_conf() != null && autoSave(data.getDownload_conf())) {
                data.setDownload_conf_id(data.getDownload_conf().getId());
            }
            //Ext
            if (data.getExt() != null && autoSave(data.getExt())) {
                data.setExt_id(data.getExt().getId());
            }
            //FilterWords
            if (data.getFilter_words() != null && saveFilterWords(data.getFilter_words())) {
                List<String> filterWordsIds = data.getFilter_words().stream().map(FilterWord::getId).collect(Collectors.toList());
                data.setFilter_words_ids(filterWordsIds.toString());
            }
            //Icon
            if (data.getIcon() != null && autoSave(data.getIcon())) {
                data.setIcon_id(data.getIcon().getId());
            }
            //Image
            if (data.getImage() != null) {
                autoSaveList(data.getImage());
                List<Integer> imageIds = data.getImage().stream().map(Image::getId).collect(Collectors.toList());
                data.setImage_ids(imageIds.toString());
            }
            //MediaExt
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
            //TplInfo
            if (data.getTpl_info() != null && saveTplInfo(data.getTpl_info())) {
                data.setTpl_info_id(data.getTpl_info().getId());
            }
            //Video
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
        //最后一个'.'是实际类名，需减去包名model，即-5
        String classPath = name.substring(0, index - 5);

        //获取service对象
        IService service;
        try {
            Class serviceClass = Class.forName(classPath + "service.IAd" + simpleName + "Service");
            //从spring容器中拿实例
            service = (IService) applicationContext.getBean(serviceClass);
        } catch (Exception e) {
            log.throwing(this.getClass().toString(), "autoSave", e);
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
        //最后一个'.'是实际类名，需减去包名model，即-5
        String classPath = name.substring(0, index - 5);

        //获取service对象
        IService service;
        try {
            Class serviceClass = Class.forName(classPath + "service.IAd" + simpleName + "Service");
            //从spring容器中拿实例
            service = (IService) applicationContext.getBean(serviceClass);
        } catch (Exception e) {
            log.throwing(this.getClass().toString(), "autoSaveList", e);
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

    private boolean saveFilterWords(List<FilterWord> filterWords) {
        boolean flag = true;
        for (FilterWord filterWord : filterWords) {
            if (filterWord.getOptions() != null) {
                for (FilterWord option : filterWord.getOptions()) {
                    flag &= adFilterWordService.saveOrUpdate(option);
                    String res = flag ? "success" : "fail";
                    log.info("save2Sql: " + res + " : " + option.toString());
                }
                List<String> optionIds = filterWord.getOptions().stream().map(FilterWord::getId).collect(Collectors.toList());
                filterWord.setOptions_ids(optionIds.toString());
            }
            flag &= adFilterWordService.saveOrUpdate(filterWord);
            String res = flag ? "success" : "fail";
            log.info("save2Sql: " + res + " : " + filterWord.toString());
        }
        return flag;
    }

    private boolean saveTplInfo(TplInfo tplInfo) {
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

    private boolean saveTplInfoData(TplInfoData tplInfoData) {
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

        List<String> ids = tplInfoData.getChildren().stream().map(TplInfoData::getId).collect(Collectors.toList());
        tplInfoData.setChildren_ids(ids.toString());
        return autoSave(tplInfoData);
    }

    private boolean saveValues(Values values) {
        if (values.getAnimations() != null) {
            autoSaveList(values.getAnimations());
            List<Integer> animationIds = values.getAnimations().stream().map(Animation::getId).collect(Collectors.toList());
            values.setAnimations_ids(animationIds.toString());
        }
        return autoSave(values);
    }

    private boolean saveDiffData(DiffData diffData) {
        //diff_data
        if (diffData.getDiff_data() != null) {
            DiffData data = diffData.getDiff_data();
            //values
            if (data.getValues() != null && saveValues(data.getValues())) {
                data.setValues_id(data.getValues().getId());
            }
            //children
            if (data.getChildren() != null) {
                data.getChildren().forEach(this::saveDiffDataChildren);
                List<String> ids = data.getChildren().stream().map(DiffDataChildren::getId).collect(Collectors.toList());
                data.setChildren_ids(ids.toString());
            }

            autoSave(data);
            diffData.setDiff_data_id(data.getId());
        }
        return autoSave(diffData);
    }

    private boolean saveDiffDataChildren(DiffDataChildren diffDataChildren) {
        if (diffDataChildren.getChildren() == null) {
            return autoSave(diffDataChildren);
        }

        //递归将所有children保存
        diffDataChildren.getChildren().forEach(this::saveDiffDataChildren);

        List<String> ids = diffDataChildren.getChildren().stream().map(DiffDataChildren::getId).collect(Collectors.toList());
        diffDataChildren.setChildren_ids(ids.toString());
        return autoSave(diffDataChildren);
    }
}
