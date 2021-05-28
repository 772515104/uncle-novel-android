package com.unclezs.novel.app.pipeline;

import com.unclezs.novel.analyzer.model.Chapter;
import com.unclezs.novel.analyzer.spider.pipline.AbstractTextPipeline;
import com.unclezs.novel.app.utils.EbookUtils;

import java.io.File;

import cn.hutool.core.io.FileUtil;
import lombok.Setter;

/**
 * epub下载管道
 *
 * @author blog.unclezs.com
 * @date 2021/5/1 15:59
 */
@Setter
public class EbookPipeline extends AbstractTextPipeline {


    @Override
    public void processChapter(Chapter chapter) {
        // 生成章节
        EbookUtils.generateChapter(chapter, new File(getFilePath()));
    }

    @Override
    public void onComplete() {
        File outDir = new File(getFilePath());
        File tmpFile = EbookUtils.toEbook(getNovel(), outDir, false);
        EbookUtils.toEpub(outDir);
        // 删除临时文件
        FileUtil.del(tmpFile);
    }
}
