package com.hadymic.sqlgenerator.service.impl;

import com.hadymic.sqlgenerator.mapper.*;
import com.hadymic.sqlgenerator.model.*;
import com.hadymic.sqlgenerator.service.IAdFilterWordService;
import com.hadymic.sqlgenerator.service.IAdImageService;
import com.hadymic.sqlgenerator.service.IAdJson2SqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdJson2SqlServiceImpl implements IAdJson2SqlService {

    @Autowired
    private AdJsonrootbeanMapper adJsonrootbeanMapper;
    @Autowired
    private AdAdslotMapper adAdslotMapper;
    @Autowired
    private AdAppMapper adAppMapper;
    @Autowired
    private AdClickAreaMapper adClickAreaMapper;
    @Autowired
    private AdDeepLinkMapper adDeepLinkMapper;
    @Autowired
    private AdDownloadConfMapper adDownloadConfMapper;
    @Autowired
    private AdExtMapper adExtMapper;
    @Autowired
    private IAdFilterWordService adFilterWordService;
    @Autowired
    private AdIconMapper adIconMapper;
    @Autowired
    private IAdImageService adImageService;
    @Autowired
    private AdMediaExtMapper adMediaExtMapper;
    @Autowired
    private AdEventMapper adEventMapper;

    @Override
    public boolean save(JsonRootBean root) {

        Ad_Data data = root.getData();
        if (data.getFilter_words()!=null && saveFilterWords(data.getFilter_words())) {
            List<String> filterWords_ids = data.getFilter_words().stream().map(Filter_word::getId).collect(Collectors.toList());
            data.setFilter_words_ids(filterWords_ids.toString());
        }


        boolean flag = true;
        if (root.getEvent() != null && saveEvent(root.getEvent())) {
            root.setEvent_id(root.getEvent().getId());
        }
        boolean save = saveRoot(root);
        return flag && save;
    }

    private boolean saveAdslot(Adslot adslot) {
        int insert = adAdslotMapper.insert(adslot);
        return insert >= 1;
    }

    private boolean saveApp(App app) {
        int insert = adAppMapper.insert(app);
        return insert >= 1;
    }

    private boolean saveClickArea(Click_area click_area) {
        int insert = adClickAreaMapper.insert(click_area);
        return insert >= 1;
    }

    private boolean saveDeepLink(Deep_link deep_link) {
        int insert = adDeepLinkMapper.insert(deep_link);
        return insert >= 1;
    }

    private boolean saveDownloadConf(Download_conf download_conf) {
        int insert = adDownloadConfMapper.insert(download_conf);
        return insert >= 1;
    }

    private boolean saveExt(Ext ext) {
        int insert = adExtMapper.insert(ext);
        return insert >= 1;
    }

    private boolean saveFilterWords(List<Filter_word> filter_words) {
        boolean flag = true;
        for (Filter_word filter_word : filter_words) {
            if (filter_word.getOptions() != null) {
                for (Filter_word option : filter_word.getOptions()) {
                    flag &= adFilterWordService.saveOrUpdate(option);
                }
                List<String> option_ids = filter_word.getOptions().stream().map(Filter_word::getId).collect(Collectors.toList());
                filter_word.setOptions_ids(option_ids.toString());
            }
            flag &= adFilterWordService.saveOrUpdate(filter_word);
        }
        return flag;
    }

    private boolean saveIcon(Icon icon) {
        int insert = adIconMapper.insert(icon);
        return insert >= 1;
    }

    private boolean saveImages(List<Image> images) {
        boolean flag = true;
        for (Image image : images) {
            flag &= adImageService.saveOrUpdate(image);
        }
        return flag;
    }

    private boolean saveMediaExt(Media_ext media_ext) {
        int insert = adMediaExtMapper.insert(media_ext);
        return insert >= 1;
    }

    private boolean saveEvent(Event event) {
        int insert = adEventMapper.insert(event);
        return insert >= 1;
    }

    private boolean saveRoot(JsonRootBean root) {
        int insert = adJsonrootbeanMapper.insert(root);
        return insert >= 1;
    }
}
