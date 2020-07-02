package com.hadymic.sqlgenerator.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hadymic.sqlgenerator.constant.FileType;
import com.hadymic.sqlgenerator.mapper.*;
import com.hadymic.sqlgenerator.model.*;
import com.hadymic.sqlgenerator.service.IAdFilterWordService;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import com.hadymic.sqlgenerator.utils.FileUtils;
import com.hadymic.sqlgenerator.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdJson2SqlServiceImpl implements IAdJson2SqlService {
    private static Logger logger = LoggerFactory.getLogger(AdJson2SqlServiceImpl.class);

    @Autowired
    private IAdFilterWordService adFilterWordService;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AdDiffDataChildrenMapper adDiffDataChildrenMapper;

    @Autowired
    private AdDataMapper adDataMapper;

    @Autowired
    private AdImageMapper adImageMapper;

    @Autowired
    private AdInfoMapper adInfoMapper;

    @Autowired
    private AdIconMapper adIconMapper;

    @Autowired
    private AdAppMapper adAppMapper;

    @Override
    public boolean saveAd(AdJsonRootBean root) {
        //设置一个单独的对象用于存储关键信息
        AdInfo adInfo = new AdInfo();

        //保存流程
        if (root.getData() != null) {
            AdData data = root.getData();
            //如果存在相同的广告
            if (data.getAd_id() != null && adDataMapper.selectCount(new QueryWrapper<AdData>().eq("ad_id", data.getAd_id())) > 0) {
                //要存的list
                List<AdImage> toSaveImages = data.getImage();

                AdData adData = adDataMapper.selectImageIdsByAdId(data.getAd_id());
                String imageIds = adData.getImage_ids();
                String[] imageIdArr = imageIds.substring(1, imageIds.length() - 1).split(",");
                //查出来的已存的list
                List<AdImage> savedImages = adImageMapper.selectBatchIds(Arrays.asList(imageIdArr));
                //过滤已经有存过url的image
                List<AdImage> filterImages = toSaveImages.stream().filter(image -> {
                    List<String> urls = savedImages.stream().map(AdImage::getUrl).collect(Collectors.toList());
                    for (String url : urls) {
                        if (url.equals(image.getUrl()))
                            return false;
                    }
                    return true;
                }).collect(Collectors.toList());
                List<AdImage> savedFilterImages = new ArrayList<>();
                //保存过滤完成的image
                if (filterImages.size() > 0) {
                    //保存时需要去重
                    for (AdImage filterImage : filterImages) {
                        //如果已经存过了
                        if (adImageMapper.selectCount(new QueryWrapper<AdImage>().eq("url", filterImage.getUrl())) > 0) {
                            AdImage adImage = adImageMapper.selectOne(new QueryWrapper<AdImage>().eq("url", filterImage.getUrl()));
                            savedFilterImages.add(adImage);
                        } else {
                            //保存图片文件
                            String filePath = FileUtils.saveFileFromInternet(filterImage.getUrl(), FileType.FILE_TYPE_IMAGE, data.getInteraction_type());
                            filterImage.setPath(filePath);
                            autoSave(filterImage);
                            savedFilterImages.add(filterImage);
                        }
                    }
                }
                //所有要存的image
                savedImages.addAll(savedFilterImages);
                List<Integer> ids = savedImages.stream().map(AdImage::getId).collect(Collectors.toList());

                //保存adInfo
                adInfo.setId(adData.getId());
                adInfo.setImage_ids(ids.toString());
                int update = adInfoMapper.updateById(adInfo);
                String res = update > 0 ? "success" : "fail";
                logger.info("update AdInfo : " + res + " : AdInfo{id:" + adInfo.getId() + ", image_ids:" + ids.toString() + "}");

                //保存adData
                AdData newData = new AdData();
                newData.setId(adData.getId());
                newData.setImage_ids(ids.toString());
                update = adDataMapper.updateById(newData);
                res = update > 0 ? "success" : "fail";
                logger.info("update AdData : " + res + " : AdData{id:" + adInfo.getId() + ", image_ids:" + ids.toString() + "}");
                return update > 0;
            } else {
                //AdAdslot
                if (data.getAdslot() != null && autoSave(data.getAdslot())) {
                    data.setAdslot_id(data.getAdslot().getId());
                }
                //AdApp
                if (data.getApp() != null) {
                    AdApp app = data.getApp();
                    //如果已经存过了
                    if (adAppMapper.selectCount(new QueryWrapper<AdApp>().eq("download_url", app.getDownload_url())) > 0) {
                        AdApp adApp = adAppMapper.selectOne(new QueryWrapper<AdApp>().eq("download_url", app.getDownload_url()));
                        data.setApp_id(adApp.getId());
                    } else {
                        //保存Apk
                        String downloadPath = FileUtils.saveApkFromInternet(app.getDownload_url(), app.getPackage_name(), data.getInteraction_type());
                        adInfo.setDownload_path(downloadPath);
                        if (autoSave(app)) {
                            data.setApp_id(app.getId());
                        }
                    }

                    //adInfo
                    adInfo.setApp_name(data.getApp().getApp_name());
                    adInfo.setPackage_name(data.getApp().getPackage_name());
                    adInfo.setDownload_url(data.getApp().getDownload_url());
                }
                //AdClickArea
                if (data.getClick_area() != null && autoSave(data.getClick_area())) {
                    data.setClick_area_id(data.getClick_area().getId());
                }
                //AdDeepLink
                if (data.getDeep_link() != null && autoSave(data.getDeep_link())) {
                    data.setDeep_link_id(data.getDeep_link().getId());

                    //adInfo
                    adInfo.setDeeplink_url(data.getDeep_link().getDeeplink_url());
                    adInfo.setFallback_url(data.getDeep_link().getFallback_url());
                }
                //AdDownloadConf
                if (data.getDownload_conf() != null && autoSave(data.getDownload_conf())) {
                    data.setDownload_conf_id(data.getDownload_conf().getId());
                }
                //AdExt
                if (data.getExt() != null) {
                    //对vid进行排序
                    String vid = data.getExt().getVid();
                    String[] vids = vid.split(",");
                    Arrays.sort(vids);
                    String sortedVid = Arrays.toString(vids);
                    data.getExt().setVid(sortedVid.substring(1, sortedVid.length() - 1));
                    if (autoSave(data.getExt())) {
                        data.setExt_id(data.getExt().getId());
                    }

                    //adInfo
                    adInfo.setAd_id(data.getExt().getAd_id());
                    adInfo.setCreative_id(data.getExt().getCreative_id());
                    adInfo.setConvert_id(data.getExt().getConvert_id());
                }
                //AdFilterWords
                if (data.getFilter_words() != null && saveFilterWords(data.getFilter_words())) {
                    List<String> filterWordsIds = data.getFilter_words().stream().map(AdFilterWord::getId).collect(Collectors.toList());
                    data.setFilter_words_ids(filterWordsIds.toString());
                }
                //AdIcon
                if (data.getIcon() != null) {
                    AdIcon icon = data.getIcon();
                    //如果已经存过了
                    if (adIconMapper.selectCount(new QueryWrapper<AdIcon>().eq("url", icon.getUrl())) > 0) {
                        AdIcon adIcon = adIconMapper.selectOne(new QueryWrapper<AdIcon>().eq("url", icon.getUrl()));
                        data.setIcon_id(adIcon.getId());
                    } else {
                        //保存Icon文件
                        String iconPath = FileUtils.saveFileFromInternet(icon.getUrl(), FileType.FILE_TYPE_ICON, data.getInteraction_type());
                        adInfo.setIcon_path(iconPath);
                        if (autoSave(icon)) {
                            data.setIcon_id(icon.getId());
                        }
                    }

                    //adInfo
                    adInfo.setIcon_url(data.getIcon().getUrl());
                }
                //AdImage
                if (data.getImage() != null) {
                    //对image进行去重
                    List<AdImage> savedImages = new ArrayList<>();
                    for (AdImage image : data.getImage()) {
                        //如果已经存过了
                        if (adImageMapper.selectCount(new QueryWrapper<AdImage>().eq("url", image.getUrl())) > 0) {
                            AdImage adImage = adImageMapper.selectOne(new QueryWrapper<AdImage>().eq("url", image.getUrl()));
                            savedImages.add(adImage);
                        } else {
                            //保存图片文件
                            String filePath = FileUtils.saveFileFromInternet(image.getUrl(), FileType.FILE_TYPE_IMAGE, data.getInteraction_type());
                            image.setPath(filePath);
                            autoSave(image);
                            savedImages.add(image);
                        }
                    }
                    List<Integer> imageIds = savedImages.stream().map(AdImage::getId).collect(Collectors.toList());
                    data.setImage_ids(imageIds.toString());

                    //adInfo
                    adInfo.setImage_ids(imageIds.toString());
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

                    //adInfo
                    adInfo.setVideo_cover_url(data.getVideo().getCover_url());
                    adInfo.setEndcard(data.getVideo().getEndcard());
                    adInfo.setFile_hash(data.getVideo().getFile_hash());
                    adInfo.setVideo_duration(data.getVideo().getVideo_duration());
                    adInfo.setVideo_url(data.getVideo().getVideo_url());

                    //保存Video
                    String videoPath = FileUtils.saveFileFromInternet(data.getVideo().getVideo_url(), FileType.FILE_TYPE_VIDEO, data.getInteraction_type());
                    adInfo.setVideo_path(videoPath);
                }
                //adInfo
                if (autoSave(data)) {
                    adInfo.setId(data.getId());
                    adInfo.setInteraction_type(data.getInteraction_type());
                    adInfo.setTarget_url(data.getTarget_url());
                    adInfo.setSource(data.getSource());
                    adInfo.setShow_url_list(data.getShow_url_list());
                    adInfo.setClick_url_list(data.getClick_url_list());
                    adInfo.setTitle(data.getTitle());
                    adInfo.setMarket_url(data.getMarket_url());
                    return autoSave(adInfo);
                } else {
                    return false;
                }
            }
        }
        return false;
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
        } catch (ClassNotFoundException e) {
            logger.error("Class Not Found ClassName: " + classPath + ".I" + simpleName + "Service");
            return false;
        }

        //保存至数据库
        boolean flag = service.saveOrUpdate(obj);
        String res = flag ? "success" : "fail";
        logger.info("save2Sql: " + res + " : " + obj.toString());
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
        } catch (ClassNotFoundException e) {
            logger.error("Class Not Found ClassName: " + classPath + ".I" + simpleName + "Service");
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
            logger.info("save2Sql: " + res + " : " + o.toString());
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
                    logger.info("save2Sql: " + res + " : " + option.toString());
                }
                List<String> optionIds = filterWord.getOptions().stream().map(AdFilterWord::getId).collect(Collectors.toList());
                filterWord.setOptions_ids(optionIds.toString());
            }
            flag &= adFilterWordService.saveOrUpdate(filterWord);
            String res = flag ? "success" : "fail";
            logger.info("save2Sql: " + res + " : " + filterWord.toString());
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
            tplInfo.setDiff_data_id(tplInfo.getDiff_data().getDiff_data().getId());
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
        //如果已经存过了
        if (diffDataChildren.getId() != null && adDiffDataChildrenMapper.selectCount(new QueryWrapper<AdDiffDataChildren>().eq("id", diffDataChildren.getId())) > 0) {
            return true;
        }
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
