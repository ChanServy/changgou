package com.chan.chang.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chan.chang.goods.pojo.Album;

import java.util.List;

public interface AlbumService {
    IPage<Album> findAlbumPageByCondition(Album album, int page, int size);

    IPage<Album> findAlbumPage(int page, int size);

    List<Album> findAlbumByCondition(Album album);

    void deleteById(Long id);

    void updateAlbum(Long id, Album album);

    void addAlbum(Album album);

    List<Album> findAlbum();

    Album findById(Long id);
}
